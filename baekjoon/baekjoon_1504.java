package Algorithm.boj.day0901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjoon_1504 {
	private static class Edge implements Comparable<Edge>{
		int no;
		int distance;
		public Edge(int no, int distance) {
			super();
			this.no = no;
			this.distance = distance;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.distance, o.distance);
		}
	}
	
	static List<Edge>[] graph;
	static int[] distances;
	static int N, E;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		for(int i=1; i<=N; i++) graph[i] = new ArrayList<Edge>();
		distances = new int[N+1];
		
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(in.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Edge(to, distance));
			graph[to].add(new Edge(from, distance));
		}
		st = new StringTokenizer(in.readLine());
		
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		printResult(v1, v2);
	}
	
	
	static void dijkstra(int start) {
		Arrays.fill(distances, Integer.MAX_VALUE);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean visit[] = new boolean[N+1];
		distances[start] = 0;
		pq.offer(new Edge(start, 0));
	
		while(!pq.isEmpty()){
			Edge curEdge =  pq.poll();
			int cur = curEdge.no;
			int min = distances[cur];
			
			if(visit[cur])continue;
			visit[cur] = true;
			
			for(Edge next : graph[cur]) {
				int no = next.no;
				int dist = next.distance;
				if(!visit[no] && distances[no] > min + dist) {
					distances[no] = min+dist;
					pq.offer(new Edge(no, distances[no]));
				}
			}
		}
	}
	static void printResult(int v1, int v2) {
		int start = 1;
		int result1 = 0;
		int result2 = 0;
		
		result1 = getDistance(v1, v2);
		result2 = getDistance(v2, v1);
		
		if(result1==0 && result2==0) System.out.println("-1");
		else if(result1!=0 && result2!=0) System.out.println(Math.min(result1, result2));
		else if(result1!=0) System.out.println(result1);
		else if(result2!=0) System.out.println(result2);
	}
	static boolean canArrive(int destination) {
		return distances[destination]!=Integer.MAX_VALUE;
	}
	static int getDistance(int first, int second){
		int sum=0;

		dijkstra(1);
		if(canArrive(first)) sum += distances[first];
		else return 0;

		dijkstra(first);
		if(canArrive(second)) sum += distances[second];
		else return 0;

		dijkstra(second);
		if(canArrive(N)) sum += distances[N];
		else return 0;
		
		return sum;
	}
}
