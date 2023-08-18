package 백준_2206_벽부수고이동하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class 백준_2206_벽부수고이동하기 {
	public static int N, M, x, y, nx, ny;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static int[][] arr;
	public static int[] temp;
	public static boolean[][] zero_visited;
	public static boolean[][] one_visited;

	public static void main(String[] args) throws Exception {
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			String[] line2 = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(line2[j]);
			}
		}
		// bfs실행
		arr[0][0] = 1; // 시작점을 1로 초기화해주어야 한다!
		one_visited = new boolean[N][M];
		zero_visited = new boolean[N][M];
		bfs(0, 0, 0);
		if (arr[N - 1][M - 1] == 0)
			System.out.println(-1); // 불가능한 경우 -1 출력
		else
			System.out.println(arr[N - 1][M - 1]); // 가능한 경우 최단 거리 출력
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
	}

	public static void bfs(int i, int j, int cnt) {
		Deque<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] { i, j, cnt }); // cnt로 벽을 이미 부쉈는지 확인. 0: 아직 안부쉈다 / 1: 부쉈다

		while (!queue.isEmpty()) {
			temp = queue.poll();
			x = temp[0];
			y = temp[1];
			cnt = temp[2];
			zero_visited[x][y] = true;
			one_visited[x][y] = true;

			for (int k = 0; k < 4; k++) {
				nx = x + dx[k];
				ny = y + dy[k];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if (arr[nx][ny] == 0) {// 벽이 없는 공간이면
					queue.offer(new int[] { nx, ny, cnt }); // 그냥 가면 된다.
					arr[nx][ny] = arr[x][y] + 1;
				} else if (cnt == 0 && arr[nx][ny] == 1) { // 벽을 안부쉈고, 벽이 있다면
					queue.offer(new int[] { nx, ny, 1 }); // 부수는 경우를 queue에 offer, 이때 cnt=1이 된다.
					arr[nx][ny] = arr[x][y] + 1;
				}
			}
		}
	}
}