package week1016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjoon_10282 {
	static class Edge implements Comparable<Edge>{
		int end;
		int time;
		public Edge(int end, int time) {
			super();
			this.end = end;
			this.time = time;
		}
		@Override
		public String toString() {
			return "Edge [end=" + end + ", time=" + time + "]";
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.time, o.time);
		}
		
	}
	
	static int N, D, C;
	static List<Edge>[] adjList;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			// 입력 받기
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			adjList = new ArrayList[N+1];
			
			for(int i=0; i<D; i++) {
				// 간선리스트 생성
				st = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				if(adjList[b] == null) {
					adjList[b] = new ArrayList<>();
				}
				adjList[b].add(new Edge(a, s));
			}
			
			int infectedCount = 0;
			int infectedTime = 0;
			boolean[] infected = new boolean[N+1];
			
			// BFS(PQ)
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			pq.offer(new Edge(C, 0));
			
			while(!pq.isEmpty()) {
				Edge cur = pq.poll();

				if(infected[cur.end]) continue;
				
				infected[cur.end] = true;
				infectedCount++;
				infectedTime = cur.time;
								
				if(adjList[cur.end] != null) {
					for(Edge edge : adjList[cur.end]) {
						if(!infected[edge.end]) {
							pq.offer(new Edge(edge.end, cur.time+edge.time));	
						}
					}
				}
			}
			
			sb.append(infectedCount+" "+infectedTime+"\n");
		}
		System.out.println(sb.toString());
	}
}
