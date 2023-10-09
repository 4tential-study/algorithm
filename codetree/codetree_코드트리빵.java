import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static class Person{
		// 현재 위치
		int x, y;
		// 도착 위치
		int ex, ey;
		public Person(int x, int y, int ex, int ey) {
			this.x = x;
			this.y = y;
			this.ex = ex;
			this.ey = ey;
		}
	}

	static final int INF = 9999999;
	
	// 상, 좌, 우, 하 순서
	static final int dx[] = {-1, 0, 0, 1};
	static final int dy[] = {0, -1, 1, 0};
	
	// 맵 크기, 사람의 수
	static int N, M;
	
	// 맵
	static int[][] map;
	
	// 편의점 찾는 사람들
	static Person[] person;
	
	// 방문체크 배열
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		map = new int[N+1][N+1];
		person = new Person[M];
		visited = new boolean[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			str = br.readLine().split(" ");
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(str[j-1]);
			}
		}
		
		// 편의점 위치
		for(int i = 0; i < M; i++) {
			str = br.readLine().split(" " );
			int ex = Integer.parseInt(str[0]);
			int ey = Integer.parseInt(str[1]);
			person[i] = new Person(0, 0, ex, ey);
		}
		
		System.out.println(solve());
	}

	// solve
	private static int solve() {
		int t = 0;
		
		while(true) {
			// t번 미만까지 사람들 이동하기
			if(move(t)) {
				break;
			}
			
			// t번째 사람 배정하기
			if(t < M) findBaseCamp(t);
			
			++t;
		}
		
		// t에 모두 움직여 끝난 경우 +1을 안해주기 때문에 여기서 +1
		return t + 1;
	}


	private static void findBaseCamp(int t) {
		// 방문 배열 초기화
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				visited[i][j] = false;
			}
		}
		
		// bfs 탐색 -> 최단 거리
		// 상, 좌, 우, 하 탐색 -> x, y가 작은 것이 먼저 탐색됨.
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {person[t].ex, person[t].ey});
		visited[person[t].ex][person[t].ey] = true;
		
		int bx = 0, by = 0;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			// 베이스 캠프 찾으면
			if(map[cur[0]][cur[1]] == 1) {
				bx = cur[0];
				by = cur[1];
				break;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				// 경계, 방문, 지나가지 못하는 곳 체크
				if (nx <= 0 || nx > N || ny <= 0 || ny > N) continue;
				if (map[nx][ny] == -1 || visited[nx][ny]) continue;

				visited[nx][ny] = true;
				q.offer(new int[] { nx , ny });
			}
		}

		// 해당 베이스 캠프에 배정하기.
		map[bx][by] = -1;
		person[t].x = bx;
		person[t].y = by;
	}


	private static boolean move(int t) {
		// 도착한 사람 수
		int cnt = 0;
		
		// person 배열의 크기를 초과하지 않도록
		if(t > M) t = M;
		
		for(int i = 0; i < t; i++) {
			// 이미 도착한 경우
			if(map[person[i].ex][person[i].ey] == -1) {
				cnt++;
				continue;
			}
			
			// 최단 거리 및 그 때의 방향
			int minDist = INF, dir = -1;
			
			// 4번의 bfs 탐색
			// 상, 좌, 우, 하로 탐색하기 때문에 같은 거리일 경우 우선순위가 지켜짐
			for(int j = 0; j < 4; j++) {
				// 방문 배열 초기화
				for(int r = 1; r <= N; r++) {
					for(int c = 1; c <= N; c++) {
						visited[r][c] = false;
					}
				}
				
				// 탐색 시작 위치
				int x = person[i].x + dx[j];
				int y = person[i].y + dy[j];
				
				// 경계, 지나가지 못하는 곳 체크
				if (x <= 0 || x > N || y <= 0 || y > N || map[x][y] == -1) continue;

				// bfs 탐색해서 거리 구하기
				// x, y, dist
				Queue<int[]> q = new ArrayDeque<>();
				q.offer(new int[] {x, y, 1});
				visited[x][y] = true;
				
				while (!q.isEmpty()) {
					int[] cur = q.poll();

					// 도착한 경우
					if (cur[0] == person[i].ex && cur[1] == person[i].ey) {
						// 갱신되는 경우
						if (cur[2] < minDist) {
							minDist = cur[2];
							dir = j;
						}
						break;
					}

					for (int k = 0; k < 4; k++) {
						int nx = cur[0] + dx[k];
						int ny = cur[1] + dy[k];

						// 경계, 방문, 지나가지 못하는 곳 체크
						if (nx <= 0 || nx > N || ny <= 0 || ny > N || map[nx][ny] == -1 || visited[nx][ny]) continue;
					
						visited[nx][ny] = true;
						q.offer(new int[] {nx, ny, cur[2] + 1});
					}
				}
			}

			// 바로 도착하는 경우
			if (minDist == 1) {
				map[person[i].ex][person[i].ey] = -1;
				cnt++;
			}
			else {
				// 이동하기
				person[i].x += dx[dir];
				person[i].y += dy[dir];
			}
		}
			
		// M명이 모두 도착한 경우만 true
		if(cnt == M) return true;
		else return false;
	}
}