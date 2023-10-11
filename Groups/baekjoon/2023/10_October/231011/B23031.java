import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class B23031 {
	// 하 좌 상 우 (아래에서부터 시작해서 오른쪽으로 회전하였을 때를 기준으로 정렬함)
	private static final int[][] dir = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
	private static final char EMPTY = 'O', SWITCH = 'S', ZOMBIE = 'Z';
	private static final char SandZ = 'T'; // 스위치의 위치와 좀비의 위치가 겹친 것을 의미함.
	private static boolean[][] islight; // 해당 칸에 불이 들어왔는지에 대한 boolean 배열
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		char[] A = in.readLine().toCharArray();
		int time = A.length;
		
		char[][] board = new char[N][N];
		islight = new boolean[N][N]; 
		List<int[]> zombieList = new ArrayList<>(); // 학생 좀비들의 좌표들을 저장할 list
		for(int y = 0; y < N; y++) {
			board[y] = in.readLine().toCharArray();
			for(int x = 0; x < N; x++) {
				if(board[y][x] == ZOMBIE) zombieList.add(new int[] {y, x, 0});
			}
		}
		
		int[] ari = new int[] {0, 0};	int ari_dir = 0;
		for(int minute = 0; minute < time; minute++) {
			char cmd = A[minute];
			switch(cmd) {
			// 바라보는 방향을 기준으로 왼쪽으로 90도 방향 전환.
			case 'L':	ari_dir = (ari_dir + 3) % 4; 	break; // 0 -> 3 -> 2 -> 1 -> 0 -> ...
			
			// 바라보는 방향을 기준으로 오른쪽으로 90도 방향 전환.
			case 'R':	ari_dir = (ari_dir + 1) % 4;	break; // 0 -> 1 -> 2 -> 3 -> 0 -> ...
			
			// 앞으로 전진
			case 'F':
				int dy = ari[0] + dir[ari_dir][0];
				int dx = ari[1] + dir[ari_dir][1];
				
				// 벽에 부딪혔다면 제자리에 머문다.
				if(!(dy >= 0 && dx >= 0 && dy < N && dx < N))	break;
				
				ari[0] = dy; ari[1] = dx;
				if(board[dy][dx] == SWITCH || board[dy][dx] == SandZ) {
					TurnON(new int[] {dy, dx}, N);
					if(board[dy][dx] == SWITCH) board[dy][dx] = EMPTY;
					else board[dy][dx] = ZOMBIE;
				}
				
        // 아리가 이동하였을 때 좀비를 만났는 데 빛이 없다면 기절
				if(board[dy][dx] == ZOMBIE && !islight[dy][dx]) {
					System.out.println("Aaaaaah!"); return;
				}
				break;
			}
			
			for(int i = 0; i < zombieList.size(); i++) {
				int y = zombieList.get(i)[0], x = zombieList.get(i)[1];
				int z_dir = zombieList.get(i)[2];
				
				int zy = y + dir[z_dir][0], zx = x + dir[z_dir][1];
				if(!(zy >= 0 && zx >= 0 && zy < N && zx < N)) {
					z_dir = (z_dir + 2) % 4;
					zombieList.set(i, new int[] {y, x, z_dir});
					continue;
				}
				
				// 좀비 이동
				if(board[y][x] == SandZ) board[y][x] = SWITCH;
				else board[y][x] = EMPTY;
				
				
				if(board[zy][zx] == SWITCH)	board[zy][zx] = SandZ;
				else board[zy][zx] = ZOMBIE;
				
        // 좀비가 이동하여 아리를 만났을 때 빛이 들어와있지 않다면 아리는 기절
				if(zy == ari[0] && zx == ari[1] && !islight[zy][zx]) {
					System.out.println("Aaaaaah!"); return;
				}
				
				zombieList.set(i, new int[] {zy, zx, z_dir});
			}
		}
		System.out.println("Phew...");
	}
	
	static void TurnON(int[] coord, int N) {
		for(int y = coord[0] - 1; y <= coord[0] + 1; y++) {
			for(int x = coord[1] - 1; x <= coord[1] + 1; x++) {
				if(y >= 0 && x >= 0 && y < N && x < N) islight[y][x] = true;
			}
		}
	}
}
