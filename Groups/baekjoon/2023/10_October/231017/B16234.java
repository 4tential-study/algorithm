// BFS 사용.
// 294200KB, 496ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class B16234 {
	private static final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	private static int[][] board;
	private static int N, L, R;
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");
		
		N = Integer.parseInt(input[0]); // 땅의 크기
		// 두 나라 인구 차 L ~ R
		L = Integer.parseInt(input[1]);
		R = Integer.parseInt(input[2]);
		
		
		board = new int[N][N];

		// board 입력
		for(int y = 0; y < N; y++) {
			input = in.readLine().split(" ");
			for(int x = 0; x < N; x++)
				board[y][x] = Integer.parseInt(input[x]);
		}
		
		int days = 0;
		while(true) {
			boolean isChanged = false;
			boolean[][] visited = new boolean[N][N];
			for(int y = 0; y < N; y++) {
				for(int x = 0; x < N; x++) {
					if(!visited[y][x]) {
						visited[y][x] = true;
						if(BFS(visited, y, x) && !isChanged) isChanged = true;
					}
				}
			}
			// 인구 이동이 진행되었다면 한 번 더 탐색함
			if(isChanged) days++; 
			else break;
		}
		
		System.out.println(days);
	}
	
	static boolean BFS(boolean[][] visited, int y, int x) {
		int sum = 0; // 인구 이동이 진행될 총 인구 수
		List<int[]> list = new ArrayList<>(); // 인구 이동이 진행될 나라의 좌표
		Queue<int[]> queue = new ArrayDeque<>(); // BFS 탐색을 위한 Queue
		queue.offer(new int[] {y, x});
		list.add(new int[] {y, x});
		
		while(!queue.isEmpty()) {
			int cy = queue.peek()[0], cx = queue.poll()[1];
			int current = board[cy][cx]; sum += current;
			for(int type = 0; type < 4; type++) {
				int dy = cy + dir[type][0];
				int dx = cx + dir[type][1];
				if(dy >= 0 && dx >= 0 && dy < N && dx < N && !visited[dy][dx]) {
					int value = Math.abs(current - board[dy][dx]);
					// 인접한 두 나라의 인구 차가 조건에 맞다면 queue와 list에 추가함.
					if(value >= L && value <= R) { 
						visited[dy][dx] = true;
						queue.offer(new int[] {dy, dx});
						list.add(new int[] {dy, dx});
					}
				}
			}
		}
		// list의 크기가 2 이상이라면 인구 이동이 진행되었음을 의미. 인구 이동 진행 후 true를 반환.
		if(list.size() >= 2) {
			calcBoard(list, sum); // 인구 이동
			return true;
		}
		// 인구 이동이 진행되지 않을 경우, false 반환.
		else return false;
	}
	
	static void calcBoard(List<int[]> list, int sum) {
		int size = list.size(), avg = sum / size;
		for(int i = 0; i < size; i++) {
			int y = list.get(i)[0], x = list.get(i)[1];
			board[y][x] = avg;
		}
	}
}
