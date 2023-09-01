import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1504 {
	static BufferedReader br;
	static StringTokenizer st;
	static ArrayList<Edge>[] edgeArray;
	static int[] distance;
	static final int INF=99999999;
	static int n;
	
	public static class Edge implements Comparable<Edge>{
		int to;
		int w;
		Edge( int to, int w){
			this.to = to;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge e) {
			return this.w - e.w;
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		int e = Integer.parseInt(str[1]);
		distance = new int[n+1];
		
		edgeArray = new ArrayList[n+1];
		for(int i=1 ; i <= n ; i++) {
			edgeArray[i] = new ArrayList<>();
		}
		
		for(int i=0 ; i < e ; i++) {
			String[] s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			int c = Integer.parseInt(s[2]);
			edgeArray[a].add(new Edge(b,c));
			edgeArray[b].add(new Edge(a,c));
		}
		
		//1)firstDist :  1->v1->v2->n
		//2)secondDist : 1->v2->v1->n
		String[] s1 = br.readLine().split(" ");
		int v1 = Integer.parseInt(s1[0]);
		int v2 =Integer.parseInt(s1[1]);
		
		int firstDist=0;
		int secondDist=0;
		dijkstra(1);
		firstDist += distance[v1];
		secondDist += distance[v2];
		
		dijkstra(v1);
		firstDist+=distance[v2];
		secondDist+=distance[n];
		
		dijkstra(v2);
		firstDist +=distance[n];
		secondDist += distance[v1];
		
		int ans = 0;
		ans = firstDist < secondDist ? firstDist : secondDist; 
		if (ans >= INF) ans = -1;
		System.out.println(ans);
		
	}
	
	public static void dijkstra(int x) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for(int i=0 ; i <= n ; i++) {
			distance[i] = INF;
		}
		
		pq.add(new Edge(x,0));
		distance[x] = 0;
		while(!pq.isEmpty()) {
			Edge poll = pq.poll();
			int edge = poll.w; //0
			int start = poll.to;
			for(Edge each : edgeArray[start]) { //2,3,4
				int dist = each.w + edge; //3+0 , 1+0 , 4+0
				if(dist < distance[each.to]) {
					distance[each.to] = dist;
					pq.add(new Edge(each.to, distance[each.to]));
				}
			}
		}
	}
}
