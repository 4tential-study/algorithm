import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
/*
 * 로직 자체는 옳았으나, 시간 초과 (34%)가 계속 발생하여
 * 이 부분을 해결하는 데 시간을 할애하였다.
 * 이전에는 빙산이 있는 위치와 높이를 LinkedList에 add하였고,
 * 내년으로 갔을 때 빙산을 녹여 LinkedList에 녹은 빙산을 
 * remove 하는 과정이 시간 복잡도가 높다고 생각하여 이를 개선하였다.
 */
public class B2573 {
	// 상, 우, 하, 좌
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static boolean[][] visited;
	static int height, width;
	static int Iceberg_count, melt;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");

		height = Integer.parseInt(input[0]);
		width = Integer.parseInt(input[1]);
		int[][] board = new int[height][width];
		for(int y = 0; y < height; y++) { // 배열 입력받기
			input = in.readLine().split(" ");
			for(int x = 0; x < width; x++)	{
				board[y][x] = Integer.parseInt(input[x]);
				if(board[y][x] > 0)	Iceberg_count++; // 전체 빙산 개수 추가
			}
		}
		int year = 0;
		while(true){
			if(Iceberg_count == 0){ year = 0; break; } // 모두 녹았으면 0 반환.
			
			int size = 0; melt = 0;
			visited = new boolean[height][width];
			
			Search:
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					if(board[i][j] > 0 && !visited[i][j]) {
						// 처음으로 만난 빙산의 위치에서부터 BFS. 이후 연산 종료
						size = BFS(i, j, board);	break Search;
					}
				}
			}
			
			if(size < Iceberg_count)	break;
			else  { // 빙산의 개수를 내년 상태로 전환.
				Iceberg_count -= melt; // 전체 빙산의 개수에 녹아내린 빙산의 개수를 뺀다.
				year++;
			}
		}
		System.out.println(year);
	}
	
	static int BFS(int y, int x, int[][] board) {
		int count = 1;
		Queue<int[]> queue = new ArrayDeque<>();
		visited[y][x] = true;
		queue.offer(new int[] {y, x});
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i = 0; i < size; i++) {
				int[] coord = queue.poll();
				int cy = coord[0], cx = coord[1];
				for(int type = 0; type < 4; type++) {
					int dy = cy + dir[type][0];
					int dx = cx + dir[type][1];
          /*
           * 이미 탐색한 빙산의 경우 if문에서 걸러짐.
           * 문제 조건을 생각하였을 때, 탐색 범위가 board를 넘어갈 일이 없으므로 이를 판단하는 식은 구현하지 않았다.
           * "배열의 첫 번째 행과 열, 마지막 행과 열에는 항상 0으로 채워진다."
           */
					if(!visited[dy][dx]) { 
						if(board[dy][dx] > 0) {
							visited[dy][dx] = true; // 빙산인 곳만 visited = true;
							queue.offer(new int[] {dy, dx});
							count++;
						}
						// 빙산 녹이기.
						else board[cy][cx] = (board[cy][cx] <= 0) ? 0 : board[cy][cx]-1;
					}
				}
				if(board[cy][cx] <= 0)	melt++; // 녹아내린 빙산의 개수 추가
			}
		}
		return count;
	}
}