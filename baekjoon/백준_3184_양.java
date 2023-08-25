package 백준_3184_양;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class 백준_3184_양 {
	public static int[] temp;
	public static int R, C, x, y, nx, ny;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static String[][] arr;
	public static int wolf_cnt, sheep_cnt;
	public static boolean[][] visited;
	public static int wolf_result, sheep_result;

	public static void main(String[] args) throws Exception {
		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		arr = new String[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String[] line2 = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				arr[i][j] = line2[j];
			}
		}
		// 시작 (할 때마다 wolf_cnt, sheep_cnt 초기화 해 주어야 함)
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!visited[i][j]) {
					wolf_cnt = 0;
					sheep_cnt = 0;
					bfs(i, j);
					if (sheep_cnt > wolf_cnt)
						sheep_result += sheep_cnt;
					else
						wolf_result += wolf_cnt; // 수가 같아도 늑대가 이김!
				}
			}
		}
		System.out.print(sheep_result + " " + wolf_result);
	}

	public static void bfs(int i, int j) {
		Deque<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] { i, j });

		while (!queue.isEmpty()) {
			temp = queue.poll();
			x = temp[0];
			y = temp[1];
			if (!visited[x][y]) {
				visited[x][y]=true;
				if (arr[x][y].equals("v")) wolf_cnt++;
				else if (arr[x][y].equals("o")) sheep_cnt++;
				for (int k = 0; k < 4; k++) {
					nx = x + dx[k];
					ny = y + dy[k];
					if (nx < 0 || ny < 0 || nx >= R || ny >= C)
						continue;
					if (arr[nx][ny].equals(".")) {
						queue.offer(new int[] { nx, ny });
					} else if (arr[nx][ny].equals("v")) {
						queue.offer(new int[] { nx, ny });
					} else if (arr[nx][ny].equals("o")) {
						queue.offer(new int[] { nx, ny });
					}
				}
			}
		}

	}
}
