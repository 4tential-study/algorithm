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
		int n = Integer.parseInt(br.readLine());
		coins = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0 ; i < n ; i++) {
			int x = Integer.parseInt(st.nextToken());
			coins[i] = x;
		}
		dp = new int[10001];
		dp[0] = 1;
		
		for(int i=1 ; i <= 10000 ; i++) {
			for(int j= 0 ; j < n ;j++) {
				if(i-coins[j] >=0) dp[i] += dp[i-coins[j]];
			}
		}
		int m = Integer.parseInt(br.readLine());
		System.out.println(dp[m]);
		
		
	}
}
