import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int N, M, V, printCount;
	static List<Integer>[] edges;
	static boolean[] v;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
//		int[][] edges = new int[N+1][N]; // 노드의 번호가 1 이상이라고 전제함
		edges = new ArrayList[N+1];
		for(int i=0; i<=N; i++) edges[i] = new ArrayList<>();
		
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine());
			int cNode = Integer.parseInt(st.nextToken());
			int nNode = Integer.parseInt(st.nextToken());
			
			edges[cNode].add(nNode);
			edges[nNode].add(cNode);
			
			edges[cNode].sort(Comparator.naturalOrder());
			edges[nNode].sort(Comparator.naturalOrder());;
		}
		
		v = new boolean[N+1];
		printCount = 0;
		DFS(V);
		sb.append("\n");
		
		v = new boolean[N+1];
		printCount = 0;
		BFS(V);
		
		System.out.println(sb.toString());
		
	}
	
	static void DFS(int idx) {
		sb.append(idx+" ");
		printCount++;
		v[idx] = true;
		
		for(int i=0; i<edges[idx].size(); i++) {
			// 기저
			// 인덱스가 단말 노드에 왔다면 재귀 탈출
			if(printCount==N || edges[idx].size()==0) return;
			// 유도
			if(!v[edges[idx].get(i)]) DFS(edges[idx].get(i));
		}
		
	}
	static void BFS(int V) {
		Queue<Integer> q = new ArrayDeque<>();
		
		q.offer(V);
		v[V] = true;
		sb.append(V+" ");
		if(++printCount == N) return;
		
		while(!q.isEmpty()) {
			int cNode = q.poll();
			
			for(int i=0; i<edges[cNode].size(); i++) {
				int nNode = edges[cNode].get(i);
				if(!v[nNode]) {
					q.offer(nNode);
					v[nNode] = true;
					sb.append(nNode+" ");
					if(++printCount == N) return;
				}
			}
			
		}
		
	}
}