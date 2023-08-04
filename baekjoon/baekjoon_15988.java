import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1, 2, 3 더하기 3
// https://www.acmicpc.net/problem/15988
public class Main {
	private static int MOD = 1_000_000_009;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		int[] n = new int[T];
		int point;
		int[] dp = new int[1_000_000];
		dp[0] = 1;
		dp[1] = 2;
		dp[2] = 4;
		point=2;
		
		for (int i=0; i<T; i++) {
			n[i] = Integer.parseInt(in.readLine());
			
			if(point < n[i]-1) {
				for(int j=3; j<n[i]; j++) {
					dp[j] = ((dp[j-1] + dp[j-2])% MOD + dp[j-3]) % MOD;
					point=j;
				}
			}

			sb.append(dp[n[i]-1]+"\n");
			
		}
		
		System.out.println(sb);
	}
}