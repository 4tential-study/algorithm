import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_11726 {
	static BufferedReader br;
	static int[][] dp;
	static final int MOD = 10007;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		dp = new int[1001][2];
		dp[1][0] = dp[2][0] = dp[2][1] = 1;
		for(int i=3 ; i <= n ; i++) {
			dp[i][0] = (dp[i-1][0] + dp[i-2][0]) % MOD;
			dp[i][1] = (dp[i-1][1] + dp[i-2][1]) % MOD;
		}
		
		System.out.println((dp[n][0] + dp[n][1])% MOD);
	}

}
