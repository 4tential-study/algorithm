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
	static List<int[]>[] graph;
	static int[] distances;
	static int N, E;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		for(int i=1; i<=N; i++) graph[i] = new ArrayList<>();
		distances = new int[N+1];
		
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(in.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			graph[from].add(new int[] {to, distance});
			graph[to].add(new int[] {from, distance});
		}
		st = new StringTokenizer(in.readLine());
		
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
				
		
		
		
		int start = 1;
		int resultSum1 = 0;
		int resultSum2 = 0;
		
		dijkstra(start);
				
		resultSum1 += distances[v1];
		resultSum2 += distances[v2];
		
		dijkstra(v1);
		resultSum1 += distances[v2];
		resultSum2 += distances[N];
		
		dijkstra(v2);
		resultSum2 += distances[v1];
		resultSum1 += distances[N];
		
		if(resultSum1==0 && resultSum2==0) System.out.println("-1");
		else System.out.println(Math.min(resultSum1, resultSum2));
	}
	static void dijkstra(int start) {
		Arrays.fill(distances, Integer.MAX_VALUE);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		boolean visit[] = new boolean[N+1];
		distances[start] = 0;
		pq.offer(start);
	
		while(!pq.isEmpty()){
			int cur =  pq.poll();
			int min = distances[cur];
			
			if(visit[cur])continue;
			visit[cur] = true;
			
			for(int[] next : graph[cur]) {
				int no = next[0];
				int dist = next[1];
				if(!visit[no] && distances[no] > min + dist) {
					distances[no] = min+dist;
					pq.offer(no);
				}
			}
		}
	}
}
