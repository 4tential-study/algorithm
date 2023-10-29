import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Main {

	// 백신 수, 선행관계 수
	static int N, M;
	
	// 선행 백신 수(진입차수)
	static int[] inDegree;
	
	// 백신 접종 시간
	static int[] vaccineTime;
	
	// 선행관계
	static List<int[]>[] lists;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/********** INIT & INPUT **********/
		
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		inDegree = new int[N+1];
		vaccineTime = new int[N+1];
		
		lists = new ArrayList[N + 1];
		for(int i = 1; i <= N; ++i) {
			lists[i] = new ArrayList<>();
		}
		
		int s, e, w;
		for(int i = 0; i < M; ++i) {
			str = br.readLine().split(" " );

			s = Integer.parseInt(str[0]);
			e = Integer.parseInt(str[1]);
			w = Integer.parseInt(str[2]);
			
			// S백신 접종 후 W일 후에 E 백신 접종
			lists[s].add(new int[] {e, w});
			
			// E백신의 선행 백신 +1
			++inDegree[e];
		}
		
		/********** SOLVE **********/
		
		// 선행 백신이 없는 백신 체크 후 queue 삽입
		Queue<Integer> q = new ArrayDeque<>(); // <백신 번호>
		
		for(int i = 1; i <= N; ++i) {
			if(inDegree[i] == 0) {
				q.offer(i);
				vaccineTime[i] = 1;	// 1일차에 접종
			}
		}
		
		// bfs 탐색
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			// cur 백신을 접종한 후에 접종 가능한 백신들 체크
			for(int i = 0; i < lists[cur].size(); ++i) {
				int nx = lists[cur].get(i)[0];	// 다음 백신
				int nc = vaccineTime[cur] + lists[cur].get(i)[1];	// 다음 백신을 맞을 수 있는 시간
				
				// 백신의 대기시간이 7일 이상이어서 재접종 해야 하는 경우 1일 추가
				if(lists[cur].get(i)[1] >= 7) ++nc;
				
				// 이미 처리한 백신이면 PASS
				if(inDegree[nx] == 0) continue;
				
				// nx 백신의 접종일 갱신(가장 늦은 값으로)
				if(vaccineTime[nx] < nc) vaccineTime[nx] = nc;
				
				// 선행 백신을 모두 접종한 경우 q에 삽입
				if(--inDegree[nx] == 0) q.offer(nx);
			}
		}

		// 가장 늦게 접종한 백신 찾기
		int maxDate = Arrays.stream(vaccineTime).max().getAsInt();
		
		/********** OUTPUT **********/
		
		System.out.println(maxDate);
	}
}