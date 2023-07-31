import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_15486 {
	static int[] T;
	static int[] P;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		T  = new int[N+1];
		P = new int[N+1];
		dp = new int[N+1];
		System.out.println(N);
		for(int i = 0 ; i < N ;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = N-1; i >= 0 ;i--) {
			
			if( i+T[i] > N ) {
				dp[i] = dp[i+1];
				continue;
			}
			
			if (T[i] == 1) {
				dp[i] = P[i]+dp[i+1];
			}
			else if(dp[i+1] < dp[i+T[i]]+P[i] ) {
				dp[i] = dp[i+T[i]] + P[i];
			} else {
				dp[i] += dp[i+1];
			}
		}
		
		System.out.println(dp[0]);
		
		
	}
}
