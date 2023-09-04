import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static class Edge implements Comparable<Edge>{
		int no;
		int next;
		int weight;
		Edge(int no, int next, int weight){
			this.no = no;
			this.next = next;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	static int parents[];
	static void make(int N) {
		parents = new int[N+1];
		for(int i=0; i<N; i++) {
			parents[i] = i;
		}
	}
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char gender[] = new char[N+1];
		st = new StringTokenizer(in.readLine());
		for(int i=1; i<=N; i++) gender[i] = st.nextToken().charAt(0);
		
//		System.out.println(kruskal(N, M, gender));
		System.out.println(prim(N, M, gender));
		
	}
	static int kruskal(int N, int M, char[] gender) throws IOException {
		Edge[] edgeList = new Edge[M];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine());
			int no = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(no, next, weight);
		}
		
		Arrays.sort(edgeList);
		make(N);
		
		int cnt=0;
		int result=0;
		for(int i=0; i<M; i++) {
			if(gender[edgeList[i].no]==gender[edgeList[i].next]) continue;
			if(union(edgeList[i].no, edgeList[i].next)) {
				cnt++;
				result += edgeList[i].weight;
			}
			if(cnt==N-1) break;
		}
		
		if(cnt==N-1) return result;
		else return -1;
	}
	static int prim(int N, int M, char[] gender) throws IOException{
		
		List<Edge>[] graph = new ArrayList[N+1];
		for(int i=1; i<=N; i++) graph[i] = new ArrayList<>();
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean visited[] = new boolean[N+1];
		
		int result=0;
		int cnt=0;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine());
			int no = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[no].add(new Edge(no, next, weight));
			graph[next].add(new Edge(next, no, weight));
		}

		visited[1] = true;
		cnt++;
		
		for(Edge e : graph[1]) {
			if(gender[e.no]!=gender[e.next])
				pq.offer(e);
		}
		
		while(!pq.isEmpty()) {
			Edge minEdge = pq.poll();
			
			if(visited[minEdge.next]) continue;
			visited[minEdge.next] = true;
			cnt++;
			
			int v = minEdge.next;
			
			result += minEdge.weight;
			
			for(Edge e : graph[v]) {
				if(!visited[e.next] && gender[e.no]!=gender[e.next]) {
					pq.offer(e);
				}
			}
			
		}
		
		if(cnt==N) return result;
		else return -1;
	}
}