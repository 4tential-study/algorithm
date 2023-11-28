
import java.util.Arrays;
import java.util.PriorityQueue;
class Solution {
    class Edge implements Comparable<Edge>{
		int start;
		int end;
		int weight;
		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		@Override
		public String toString() {
			return "Edge [start=" + start + ", end=" + end + ", weight=" + weight + "]";
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	static int[][] adjMatrix;
		
	public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        
        adjMatrix = new int[n][n];        
        for(int i=0; i<n; i++) {
        	Arrays.fill(adjMatrix[i], Integer.MAX_VALUE);
        	adjMatrix[i][i] = 0;
        }
        
        for(int i=0; i<fares.length; i++) {
        	int start = fares[i][0]-1;
        	int end = fares[i][1]-1;
        	int weight = fares[i][2];
        	adjMatrix[start][end] = weight;
        	adjMatrix[end][start] = weight;
        }
        
        int distanceAB[] = dijkstra(s-1, n);
        
        for(int i=0; i<n; i++) {
//        	if(i==s) continue;
        	int distance[] = dijkstra(i, n);
        	
        	answer = Math.min(answer, distanceAB[i]+distance[a-1]+distance[b-1]);
        }
        
        return answer;
    }
	
	int[] dijkstra(int start, int n) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[n];
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		pq.offer(new Edge(start, start, 0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int curDist = dist[cur.end];
			if(visited[cur.end]) continue;
			
			visited[cur.end] = true;
			if(dist[cur.end]<curDist) continue;
			for(int i=0; i<n; i++) {
				if(adjMatrix[cur.end][i]==Integer.MAX_VALUE) continue;
				
				int next = i;
				int nextDist = curDist + adjMatrix[cur.end][next];
				if(dist[next]>nextDist) {
					dist[next] = nextDist;
					pq.offer(new Edge(cur.end, next, nextDist));
				}
			}
		}
		
		
		return dist;
	}
}