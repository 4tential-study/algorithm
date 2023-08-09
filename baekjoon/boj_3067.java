import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_3067 {
	static BufferedReader br;
	static StringTokenizer st;
	static int t ;
	static int n;
	static int[] coins;
	static int[] dp;
	static int m;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		for(int x =0 ; x < t ;x ++) {
			int n = Integer.parseInt(br.readLine());
			coins = new int[n+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1 ; i <= n ; i++) {
				int xx = Integer.parseInt(st.nextToken());
				coins[i] = xx;
//				dp[coins[i]] = 1;
			}
			dp = new int[10001];
			dp[0] = 1;
			
			
			int m = Integer.parseInt(br.readLine());

			
			
			for(int j=1 ; j <= n ; j++) {
				int coin = coins[j];
				for(int i=coin ; i <= m ; i++) {
					dp[i] += dp[i-coins[j]];
				}
			}
			
			System.out.println(dp[m]);
		}
	}
}
