import java.util.*;
/*
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
*/
class Solution {

    static final int INF = 2100000000;
    
    static int[] sToPoint;
    static int[] aToPoint;
    static int[] bToPoint;
    
    static List<int[]>[] adjList;
    
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INF;
        
        sToPoint = new int[n + 1];
        aToPoint = new int[n + 1];
        bToPoint = new int[n + 1];
        
        adjList = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; ++i){
            adjList[i] = new ArrayList<>();
        
            // 다익스트라 배열 최대값으로 초기화
            sToPoint[i] = INF;
            aToPoint[i] = INF;
            bToPoint[i] = INF;
        }
        
        // 간선 adjList에 넣어주기
        for(int i = 0; i < fares.length; ++i){
            adjList[fares[i][0]].add(new int[] {fares[i][1], fares[i][2]});
            adjList[fares[i][1]].add(new int[] {fares[i][0], fares[i][2]});
        }
        
        // (S -> X 비용) + (X -> A 비용) + (X -> B 비용)
        // 의 최소값을 찾는다. (X는 임의의 점)
        
        // X -> S
        dijkstra(s, sToPoint);
        // B -> X
        dijkstra(b, bToPoint);
        // A -> X
        dijkstra(a, aToPoint);
        
        for(int i = 1; i <= n; ++i) {
        	// if(i == s || i == a || i == b) continue;
        	// 임의의 점이 곂칠수도 있잖아..
            
        	int tmp = sToPoint[i] + aToPoint[i] + bToPoint[i];
        	if(tmp < answer) answer = tmp;
        }
        
        return answer;
    }

    // 다익스트라, 3번 돌리기 위해 start, 와 dist를 받음.
    public static void dijkstra (int start, int[] dist){
        Queue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        
        pq.offer(new int[] {0, start});
        dist[start] = 0;
        
        while(!pq.isEmpty()){
        	int now = pq.poll()[1];
        	
        	for(int i = 0; i < adjList[now].size(); ++i) {
        		int nx = adjList[now].get(i)[0];
        		int nc = adjList[now].get(i)[1] + dist[now];
        		
        		if(dist[nx] > nc) {
        			dist[nx] = nc;
        			pq.offer(new int[] {nc, nx});
        		}
        	}
        }
    }
}