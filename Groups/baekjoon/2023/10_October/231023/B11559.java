import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class B11559 {
	private static final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	private static final char EMPTY = '.';
	private static char[][] board;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		board = new char[12][6];
		
		for(int y = 0; y < 12; y++) 
			board[y] = in.readLine().toCharArray();
		
		int count = 0;
		while(true) {
			boolean bang = false;
			boolean[][] visited = new boolean[12][6];
			for(int y = 0; y < 12; y++) {
				for(int x = 0; x < 6; x++) {
					if(board[y][x] == EMPTY) continue;
					if(!visited[y][x]) {
						visited[y][x] = true;
						if(bfs(visited, y, x) && !bang)	bang = true;
					}
				}
			}
			
			if(bang)	{
				count++;
				afterBoard();
			}
			else break;
		}
		
		System.out.println(count);
	}
	
	static void afterBoard() {
		for(int x = 0; x < 6; x++) {
			int blank_y = -1;
			for(int y = 11; y >= 0; y--) {
 				if(board[y][x] == EMPTY && blank_y == -1) {
					blank_y = y;
				}
				else if(board[y][x] != EMPTY && blank_y != -1) {
					board[blank_y--][x] = board[y][x];
					board[y][x] = EMPTY;
				}
			}
		}
	}
	
	static boolean bfs(boolean[][] visited, int start_y, int start_x) {
		char current = board[start_y][start_x];
		
		List<int[]> list = new ArrayList<>();
		Queue<int[]> queue = new ArrayDeque<>();
		list.add(new int[] {start_y, start_x});
		queue.offer(new int[] {start_y, start_x});
		
		while(!queue.isEmpty()) {
			int y = queue.peek()[0];
			int x = queue.poll()[1];
						
			for(int type = 0; type < 4; type++) {
				int dy = y + dir[type][0];
				int dx = x + dir[type][1];
				if(dy >= 0 && dx >= 0 && dy < 12 && dx < 6
						&& !visited[dy][dx]) {
					if(current == board[dy][dx]) {
						visited[dy][dx] = true;
						list.add(new int[] {dy, dx});
						queue.offer(new int[] {dy, dx});
					}
				}
			}
		}
		
		if(list.size() < 4)	return false;
		else {
			for(int[] coord : list) {
				int y = coord[0], x = coord[1];
				board[y][x] = EMPTY;
			}
			return true;
		}
	}
}