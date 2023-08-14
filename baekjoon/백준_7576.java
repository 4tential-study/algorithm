//시간초과

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
	public static int[][] arr;
	public static int N, M, x, y, nx, ny, count;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static int result = 0;
	public static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		// arr에 토마토 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		M = Integer.parseInt(line[0]); // M은 열 개수
		N = Integer.parseInt(line[1]); // N은 행 개수
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			String[] line2 = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(line2[j]);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1) {
					visited = new boolean[N][M];
					bfs(i, j,2);
				}
			}
		}
		for (int i = 0; i < N; i++) {
			if (result == -1)
				break;
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0) {
					result = -1;
					break;
				} else if (arr[i][j] > 0) {
					result = Math.max(arr[i][j], result);
				}
			}
		}
		if (result!=-1)	System.out.println(result-1);
		else System.out.println(result);
	}

	public static void bfs(int i, int j, int days) {
		Deque<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] { i, j, days });
		visited[i][j] = true;

		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			x = temp[0];
			y = temp[1];
			days = temp[2];
			for (int k = 0; k < 4; k++) {
				nx = x + dx[k];
				ny = y + dy[k];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if (arr[nx][ny] == 1 || arr[nx][ny] == -1)
					continue;
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					if (arr[nx][ny]>1)	days = Math.min(arr[nx][ny], days);
					arr[nx][ny] = days;
					queue.offer(new int[] { nx, ny, days + 1 });
				}
			}
		}
	}
}