import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B1915 {
	public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");
		int N = Integer.parseInt(input[0]); // 세로
		int M = Integer.parseInt(input[1]); // 가로

		int[][] board = new int[N][M];
		int[][] dp = new int[N][M];
		int max = 0;

		for(int y = 0; y < N; y++) {
			String temp = in.readLine();
			for(int x = 0; x < M; x++) {
				board[y][x] = temp.charAt(x) - '0';
				if(y == 0 || x == 0) dp[y][x] = board[y][x];
			}
		}

		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				if(!(y==0 || x == 0)){
					if(board[y][x] != 0)
					dp[y][x] = Math.min(dp[y-1][x-1], Math.min(dp[y][x-1], dp[y-1][x])) + board[y][x];
				}
				max = Math.max(max, dp[y][x]);
			}
		}

		System.out.println(max*max);
	}
}