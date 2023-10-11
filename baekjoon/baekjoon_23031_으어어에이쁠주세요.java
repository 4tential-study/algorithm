package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class baekjoon_23031_으어어에이쁠주세요 {
	private static char[][] board, z_board;
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		char[] moving = br.readLine().toCharArray();
		board = new char[N][N];
		z_board = new char[N][N];
		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				z_board[i][j] = 'O';
			}
		}
		// 좀비의 좌표와 방향을 담은 z_loc 리스트
		List<int[]> z_loc = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 'Z') {
					z_loc.add(new int[] { i, j, 0 });
					z_board[i][j] = 'Z';
					// 좀비의 좌표는 오직 z_loc 리스트를 통해서만 확인할 것이므로, board에서의 값은 O로 바꿔준다.
					board[i][j] = 'O';
				}
			}
		}
		// 아리의 x좌표, y좌표를 나타내는 변수 x,y
		int x = 0, y = 0, dir = 0, nx, ny;
		// 아리가 좀비를 만났는지의 여부를 판단하는 변수 failure
		boolean failure = false;
		// 좀비의 x좌표, y좌표, 방향을 나타내는 변수 a,b,z_dir
		int a, b, z_dir, na, nb;
		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, -1, 0, 1 };
		// 모든 턴에 대하여
		for (int i = 0; i < moving.length; i++) {
			// 1. 아리의 턴
			// 아리의 방향 조정
			if (moving[i] == 'R') {
				dir = (dir + 1) % 4;
			} else if (moving[i] == 'L') {
				dir = (4 + dir - 1) % 4;
			} else if (moving[i] == 'F') {
				// 아리의 이동
				nx = x + dx[dir];
				ny = y + dy[dir];
				// 벽에 부딪히지 않는 위치라면 이동한다.
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					// 스위치가 있는 위치이면 불을 켜준다.
					if (board[nx][ny] == 'S')
						switch_on(nx, ny);
					x = nx;
					y = ny;
				}
			}
			// 아리가 이동한 곳에서 좀비와 만나는지 확인
			if (z_board[x][y] == 'Z' && board[x][y] == 'O') {
				failure = true;
				break;
			}

			// 2. 좀비의 턴
			for (int j = 0; j < z_loc.size(); j++) {
				int[] temp = z_loc.get(j);
				a = temp[0];
				b = temp[1];
				z_dir = temp[2];
				na = a + dx[z_dir];
				nb = b + dy[z_dir];
				
				// 만약 좀비가 범위 외로 나가면 방향을 바꾸어준다.
				if (na < 0 || nb < 0 || na >= N || nb >= N) {
					if (z_dir == 0) {
						z_loc.set(j, new int[] { a, b, 2 });
					} else if (z_dir == 2) {
						z_loc.set(j, new int[] { a, b, 0 });
					}
				}
				// 만약 좀비가 범위 내에 있다면
				else {
					// z_board에 대하여 좀비가 있던 위치를 초기화시켜준다.
					z_board[a][b] = 'O';
					// 리스트에 담긴 좀비의 위치를 갱신한다.
					z_loc.set(j, new int[] { na, nb, z_dir });
				}
			}
			// 리스트에서 갱신된 좀비의 위치에 따라 z_board를 갱신한다. (한 칸에 좀비가 여럿 스치는 경우에 대비하여)
			for (int j = 0; j < z_loc.size(); j++) {
				int[] temp = z_loc.get(j);
				a = temp[0];
				b = temp[1];
				z_board[a][b] = 'Z';
				// z_board에 따라 현재 아리가 있는 위치에 좀비가 배치될 경우 처리해준다.
				if (a == x && b == y && board[x][y] == 'O') {
					failure = true;
					break;
				}
			}
		}
		if (failure == true) {
			System.out.println("Aaaaaah!");
		} else {
			System.out.println("Phew...");
		}
	}

	// 불 켜주는 함수
	private static void switch_on(int x, int y) {
		int[] dx = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };
		int[] dy = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
		int nx, ny;
		for (int i = 0; i < 9; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;
			if (board[nx][ny]=='O')
				board[nx][ny] = 'L';
		}
	}
}