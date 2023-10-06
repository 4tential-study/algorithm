import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2112 {
	private static int[][] board, copy;
	private static int thick, width, K;
	private static int min;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			StringTokenizer st = new StringTokenizer(in.readLine());
			thick = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken()); // 동일한 특성이 K개 이상 있어야 함
			
			board = new int[thick][width];
			for(int y = 0; y < thick; y++) {
				String[] input = in.readLine().split(" ");
				for(int x = 0; x < width; x++) {
					board[y][x] = Integer.parseInt(input[x]);
				}
			}
			if(K <= 1 || CountBoard(board, K)) { // K가 1 이하이거나 이미 만족한 경우 약품을 투입할 필요가 없으므로 0 반환.
				sb.append(0).append("\n");
				continue;
			}

			min = K;
			copy = new int[thick][width];
			for(int y = 0; y < thick; y++)
				System.arraycopy(board[y], 0, copy[y], 0, width);
			
			DFS(0, 0);
			
			sb.append(min).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void DFS(int count, int idx) {
		if(count >= K)	return;
		
		if(idx == thick) {
			if(CountBoard(copy, K))	min = Math.min(min, count);
			return;
		}
		
		DFS(count, idx+1); // 해당 idx에 약품 사용 X
		
		changeRow(copy, idx, 0);
		DFS(count+1, idx+1);
		
		changeRow(copy, idx, 1);
		DFS(count+1, idx+1);
		
		for(int x = 0; x < width; x++)
			copy[idx][x] = board[idx][x];
		
	}
	
	static void changeRow(int[][] board, int num, int value) {
		int length = board[0].length;
		for(int x = 0; x < length; x++) {
			board[num][x] = value;
		}
	}

	static boolean CountBoard(int[][] board, int K) {
		int width = board[0].length;
		int thick = board.length;
		for(int x = 0; x < width; x++) {
			int max_count = 1;
			int value = board[0][x];
			int count = 1;
			for(int y = 1; y < thick; y++) {
				if(value != board[y][x]) {
					max_count = Math.max(max_count, count);
					count = 1;
					value = board[y][x];
				}
				else count++;
			}
			max_count = Math.max(max_count, count);
			if(max_count < K) return false;
		}
		return true;
	}
}
