package 백준_2583_영역구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

public class 백준_2583_영역구하기 {
	public static int[] temp;
	public static int M, N, x, y, nx, ny, cnt;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static int[][] arr;
	public static int area;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		M = Integer.parseInt(line[0]);
		N = Integer.parseInt(line[1]);
		int K = Integer.parseInt(line[2]);
		arr = new int[M][N];
		ArrayList<Integer> li = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			String[] line2 = br.readLine().split(" ");
			int left_y = Integer.parseInt(line2[0]);
			int left_x = Integer.parseInt(line2[1]);
			int right_y = Integer.parseInt(line2[2]);
			int right_x = Integer.parseInt(line2[3]);
			for (int y = left_y; y < right_y;y++) {
				for (int x = left_x; x < right_x; x++) {
					arr[x][y] = 1;
				}
			}
		}
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 0) {
					area = 1;
					cnt++;
					arr[i][j]=1;
					dfs(i, j);
					li.add(area);
				}
			}
		}
		System.out.println(cnt);
		Collections.sort(li);
		for (int i = 0; i < li.size(); i++) {
			System.out.print(li.get(i)+" ");
		}
	}

	public static void dfs(int i, int j) {
		Deque<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] { i, j });

		while (!queue.isEmpty()) {
			temp = queue.poll();
			x = temp[0];
			y = temp[1];
			for (int k = 0; k < 4; k++) {
				nx = x + dx[k];
				ny = y + dy[k];
				if (nx < 0 || ny < 0 || nx >= M || ny >= N)
					continue;
				if (arr[nx][ny] == 0) {
					area++;
					arr[nx][ny] = 2;
					queue.offer(new int[] { nx, ny });
				}
			}
		}
	}
}
