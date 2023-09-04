import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1462 {
	static BufferedReader br;
	static StringTokenizer st;
	static int n;
	static int m;
	static int[] parents;
	static String[] univ;
	static PriorityQueue<Edge> pq ;
	
	public static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int v;
		Edge(int from, int to, int v){
			this.from = from;
			this.to= to;
			this.v = v;
		}
		@Override
		public int compareTo(Edge o) {
			return this.v-o.v;
		}
	}
	//오름차순정렬
	//for순회 , 가장 짧은 애 사용
	
	public static void main(String[] args) throws IOException{
		pq = new PriorityQueue<>();
		br = new BufferedReader(new InputStreamReader(System.in));                                              
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		parents = new int[n+1];
		univ = new String[n+1];
		st = new StringTokenizer(br.readLine());

		for(int i=1 ; i <= n ; i++) {
			if(!st.hasMoreTokens()) {
				return;
			}
			univ[i] = st.nextToken();
		}
		
		for(int i=0 ; i < m ; i++) {
			String input= "";
			if((input = br.readLine()) != null) {
				String[] splits = input.split(" ");
				 int u = Integer.parseInt(splits[0]);
				 int v = Integer.parseInt(splits[1]);
				 int d = Integer.parseInt(splits[2]);
				if(univ[u].equals(univ[v])) continue;
				pq.offer(new Edge(u, v, d));
			}
		}
		
		if (pq.size() == 0) {
			System.out.println(-1);
			return;
		}
		
		
		for(int i=0 ; i <= n ; i++) {
			parents[i] = i;
		}
		
		int result = 0;
		int item = 0;
		for(int i=0 ; i < m ; i++) {
			if(pq.peek()==null) {
				System.out.println(-1);
				return;
			}
			
			Edge edge = pq.poll();
			if(union(edge.from, edge.to)) {
					result += edge.v;
					item++;
			}
			
			if(item == n-1) {
				System.out.println(result);
				return;
			}
				
		}
		
		
		System.out.println(result);
	}
	
	public static boolean union(int a , int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static int find(int x) {
		if(parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}
	
	

}
