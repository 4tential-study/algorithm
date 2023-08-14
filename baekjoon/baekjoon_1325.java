package Algorithm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Notepad {
	static int N, M;
	static boolean[] visit;
//	static boolean[][] edges;
	static List<Integer>[] edges;
	static int[] nodesCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
//		edges = new boolean[N+1][N+1];
		edges = new ArrayList[N+1];
		List<Integer> result = new ArrayList<>();
		nodesCount = new int[N+1];
//		List<Integer>[] graph = new ArrayList[N+1];
		
		Queue<Integer> q = new ArrayDeque<>();
		Stack<Integer> stack = new Stack<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
//			edges[B][A] = true;
			if(edges[B] == null) {
				edges[B] = new ArrayList<>();
			}
			edges[B].add(A);
		}
		
		
		int topCnt = 0;

		for(int i=1; i<=N; i++) {
			int cnt=0;
			visit = new boolean[N+1];
			// 각 노드에서 출발해보기 : 가장 많은 노드에 접근할 수 있었던 노드
			
			// bfs
//			q.offer(i);
//			visit[i] = true;
//			while(!q.isEmpty()) {
//				int curNode = q.poll();
//				
//				for(int j=1; j<=N; j++) {
//					if(edges[curNode][j] && !visit[j]) {
//						q.offer(j);
//						cnt++;
//						visit[j] = true;
//					}
//				}
//			}
			
			// dfs
			stack.push(i);
			visit[i] = true;
			while(!stack.isEmpty()) {
				int curNode = stack.pop();
				
				if(edges[curNode]==null) continue;
				for(int j=0; j<edges[curNode].size(); j++) {
					if(edges[curNode].get(j) < i) {
						cnt += nodesCount[edges[curNode].get(j)];
						q.clear();
						break;
					}else if(!visit[edges[curNode].get(j)]) {
						stack.push(edges[curNode].get(j));
						cnt++;
						visit[edges[curNode].get(j)] = true;
					}
				}
			}
			nodesCount[i] = cnt;
//			if(topCnt == cnt) {
//				result.add(i);
//			}
//			else if(topCnt < cnt) {
//				topCnt = cnt;
//				result = new ArrayList<>();
//				result.add(i);
//			}
		}
		
		int max = 0;
		for(int i=1; i<=N; i++) {
			if(max == nodesCount[i]) {
				result.add(i);
			}
			if(max < nodesCount[i]) {
				max = nodesCount[i];
				result.add(i);
			}
		}
		result.sort(Comparator.naturalOrder());
		for(int i:result) {
			System.out.print(i+" ");
		}
		
	}
}

