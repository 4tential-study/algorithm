package tmpdelete;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class BOJ_14938 {

	// 
	static final int INF = 1987654321;
	
	// .
	static int N, M, R;
	
	// 아이템 개수
	static int[] item;
	
	// 간신 리스트
	static List<int[]> list[];
	
	// 최대값
	static int maxItems;

	// 방문 배열
	static int[] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" " );
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		R = Integer.parseInt(str[2]);
		
		item = new int[N+1];
		dist = new int[N+1];
		
		str = br.readLine().split(" " );
		for(int i = 1; i <= N; i++) {
			item[i] = Integer.parseInt(str[i-1]);
		}
		
		list = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		// a와 b 연결, 수색 거리는 c
		for(int i = 0; i < R; i++) {
			str = br.readLine().split(" " );
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			int c = Integer.parseInt(str[2]);
			
			list[a].add(new int[] {b, c});
			list[b].add(new int[] {a, c});			
		}
		
		// 다익스트라 n번
		for(int i = 1; i <= N; i++) {
			dijkstra(i);
		}
		
		// 결과값 출력
		System.out.println(maxItems);
	}

	// x번 위치에서의 다익스트라
	private static void dijkstra(int x) {
		// 거리 배열 초기화
		Arrays.fill(dist, INF);
		
		// 현재 위치
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(x);
		dist[x] = 0;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i = 0; i < list[cur].size(); i++) {
				int next = list[cur].get(i)[0];
				int cost = list[cur].get(i)[1];
				
				// 수색 범위를 만족 하느냐? -> 만족 못 하면 더 탐색할 이유가 없음.
				if(dist[cur] + cost > M) continue;

				// 갱신이 되느냐?
				if(dist[cur] + cost >= dist[next]) continue;
				
				// 탐색
				dist[next] = dist[cur] + cost;
				q.offer(next);
			}
		}
		
		// 얻을 수 있는 아이템의 합
		int sum = 0;

		// 방문한 경우 체크하기
		for(int i = 1; i <= N; i++) {
			// M보다 큰 경우는 탐색하지 않았으므로 INF만 확인
			if(dist[i] != INF) {
				sum += item[i];
			}
		}

		// 최대값 갱신
		if(sum > maxItems) maxItems = sum;
	}
}


