//boolean배열 진작 만들어줬으면 되었을 문제. arr의 좌표에 값 넣어서 갱신하는 것 자체가 어려움이 있는 아이디어였음.
package 백준_7562_나이트의이동;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class 백준_7562_나이트의이동 {
	public static int now_x;
	public static int now_y;
	public static int cnt;
	public static int result;
	public static int nx;
	public static int ny;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] dx = { -1, -2, -2, -1, 1, 2, 2, 1 };
		int[] dy = { -2, -1, 1, 2, -2, -1, 1, 2 };
		for (int test_case = 1; test_case <= T; test_case++) {
			result = 0;
			int l = Integer.parseInt(br.readLine());
			String[] line1 = br.readLine().split(" ");
			String[] line2 = br.readLine().split(" ");
			// 출발점의 좌표를 start_x,start_y로 / 도착점의 좌표를 target_x, target_y로
			int start_x = Integer.parseInt(line1[0]);
			int start_y = Integer.parseInt(line1[1]);
			int target_x = Integer.parseInt(line2[0]);
			int target_y = Integer.parseInt(line2[1]);
			int[][] arr = new int[l][l];
			boolean[][] visited = new boolean[l][l];
			Deque<int[]> queue = new ArrayDeque<int[]>();
			queue.push(new int[] { start_x, start_y, 0 });
			while (!queue.isEmpty()) {
				int[] now = queue.poll();
				now_x = now[0];
				now_y = now[1];
				cnt = now[2];
				// 방문했으면 cnt 출력하고 종료
				if (target_x == now_x && target_y == now_y) {
					System.out.println(cnt);
					break;
				}
				// 이동할 수 있는 8개의 지점(nx,ny)에 대하여
				for (int i = 0; i < 8; i++) {
					nx = now_x + dx[i];
					ny = now_y + dy[i];
					if (nx < 0 || ny < 0 || nx >= l || ny >= l)
						continue;
					if (!visited[nx][ny]) {
						visited[nx][ny] = true;
						queue.offer(new int[] { nx, ny, cnt + 1 });
					}
				}
			}
		}
	}
}
