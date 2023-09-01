import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2583 {
	// 상, 우, 하, 좌
	static int[][] dir = {{-1, 0},{0, 1},{1, 0},{0, -1}};
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		int M = Integer.parseInt(st.nextToken()); // 세로
		int N = Integer.parseInt(st.nextToken()); // 가로
		int K = Integer.parseInt(st.nextToken()); // 입력받는 직사각형 개수
		
		boolean[][] visited = new boolean[M][N]; // 직사각형이 해당 좌표를 덮었는가?
		for(int i = 0; i < K; i++) {
			String[] input = in.readLine().split(" ");
			int[] start = {Integer.parseInt(input[1]), Integer.parseInt(input[0])};
			int[] end = {Integer.parseInt(input[3]), Integer.parseInt(input[2])};
			
			
			for(int y = start[0]; y < end[0]; y++) {
				for(int x = start[1]; x < end[1]; x++) {
					visited[y][x] = true;
				}
			}
		}
		
		int count = 0;
		List<Integer> list = new ArrayList<>();
		for(int y = 0; y < M; y++) {
			for(int x = 0; x < N; x++) {
				if(!visited[y][x]) {
					count++;	
					list.add(bfs(y, x, visited));
				}
			}
		}
		
		Collections.sort(list);
		sb.append(count).append("\n");
		for(Integer i : list) sb.append(i).append(" ");
		
		System.out.println(sb);
	}
	
	static int bfs(int y, int x, boolean[][] board) {
		int answer = 0;
		Queue<int[]> queue = new ArrayDeque<>();
		board[y][x] = true;
		queue.offer(new int[] {y, x});
		answer++;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i = 0; i < size; i++) {
				int[] coordinate = queue.poll();
				for(int type = 0; type < 4; type++) {
					int dy = coordinate[0] + dir[type][0];
					int dx = coordinate[1] + dir[type][1];
					if(inBoard(dy, dx, board) && !board[dy][dx]) {
						board[dy][dx] = true;
						queue.offer(new int[] {dy, dx});
						answer++;
					}
				}
			}
		}
		return answer;
	}
	
	static boolean inBoard(int y, int x, boolean[][] board) {
		int col = board.length;
		int row = board[0].length;
		return y >= 0 && x >= 0 && y < col && x < row;
	}
}