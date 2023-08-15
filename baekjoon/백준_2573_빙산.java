package 백준_2573_빙산;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class 백준_2573_빙산 {
	public static int[][] arr;
	public static int N, M, x, y, nx, ny, cnt, years;
	public static int[] dx, dy, temp;
	public static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		cnt = 1;
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		arr = new int[N][M];
		dx = new int[] { -1, 1, 0, 0 };
		dy = new int[] { 0, 0, -1, 1 };
		for (int i = 0; i < N; i++) {
			String[] line2 = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(line2[j]);
			}
		}
		cnt = 0;
		while (cnt <=1) {
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(arr[i]));
//			}
//			System.out.println("========");
			years++;
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] != 0 && visited[i][j] == false)
						cnt++;
					if (arr[i][j]!=0)
						bfs(i, j);
				}
			}
//			System.out.println(cnt);
//			System.out.println("repeated");
		}

		System.out.println(years);
	}

	public static void bfs(int i, int j) {
		Deque<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] { i, j });

		while (!queue.isEmpty()) {
			temp = queue.poll();
			x = temp[0];
			y = temp[1];
//			for (int a = 0; a < N; a++) {
//				System.out.println(Arrays.toString(arr[a]));
//			}
//			System.out.println("========");
			if (!visited[x][y]) {
				visited[x][y] = true;
				for (int k = 0; k < 4; k++) {
					nx = x + dx[k];
					ny = y + dy[k];
					if (nx < 0 || ny < 0 || nx >= N || ny >= M)
						continue;
					if (arr[nx][ny] == 0 && arr[x][y] != 0 && visited[nx][ny] == false) {
						arr[x][y]--;
					} else if (arr[nx][ny] != 0 && visited[nx][ny] == false) {
						queue.offer(new int[] { nx, ny });
					}
				}
			}
		}
	}
}
