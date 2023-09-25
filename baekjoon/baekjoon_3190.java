package W0925;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/*
  1. 시간재기
  2. 뱀 이동하기 
  3. 범위를 벗어나거나, 뱀 몸통 만날 때 종료
  4. 사과가 있을 때 없을 때 처리 
  5. 방향을 바꿔주는 시간을 만날 때 방향 변경 
  6. 현재값 업데이트
 */
public class baekjoon_3190 {
	static int N, K, L;
	static int[][] map;
	static HashMap<Integer, String> hash = new HashMap<>();
	static List<int[]> snake = new ArrayList<>();
	
	// 우 하 좌 상
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[a][b] = 1;

		}

		L = Integer.parseInt(br.readLine());

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			String c = st.nextToken();
			hash.put(x, c);
		}

		solve();

	}

	private static void solve() {
		int cx = 0, cy = 0;
		int time = 0;
		int d = 0;
		snake.add(new int[] {0, 0});
		
		while(true) {
			// 1. 시간 재기
			time++;
			
			// 2. 뱀 이동하기
			int nx = cx + dx[d];
			int ny = cy + dy[d];
			
			// 3. 범위를 벗어나거나, 뱀 몸통 만날 때 종료
			if(isFinish(nx, ny)) break;
			
			// 4. 사과가 있을 때 없을 때 처리
			if(map[nx][ny] == 1) {
				map[nx][ny] = 0;
				snake.add(new int[] {nx, ny});
			}else {
				snake.add(new int[] {nx, ny});
				snake.remove(0);
			}
			
			// 5. 방향을 바꿔주는 시간을 만날 때 방향 변경
			if(hash.containsKey(time)) {
				if(hash.get(time).equals("D")) {
					if(++d==4) d=0;					
				}else {
					if(--d==-1) d=3;
				}
			}
			
			// 6. 현재값 업데이트
			cx = nx;
			cy = ny;
		}
		System.out.println(time);
	}

	private static boolean isFinish(int nx, int ny) {
		if(nx < 0 || ny <0 || nx>= N || ny>= N) return true;
		
		for(int i=0; i<snake.size(); i++) {
			int[] t = snake.get(i);
			if(nx==t[0] && ny == t[1]) return true;
		}
		
		return false;
	}
}
