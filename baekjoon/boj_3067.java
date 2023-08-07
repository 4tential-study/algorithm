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
	static int[][] dp;
	static int m;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		for(int x =0 ; x < t ;x ++) {
			int n = Integer.parseInt(br.readLine());
			coins = new int[n];
			dp = new int[10001][n];
			st = new StringTokenizer(br.readLine());
			for(int i=0 ; i < n ; i++) {
				int xx = Integer.parseInt(st.nextToken());
				coins[i] = xx;
				dp[coins[i]][i] = 1;
			}
			
			
			
			for(int i=coins[0] ; i <= 10000 ; i++) {
				for(int j= 0 ; j < n ;j++) {
					if(i-j < 0) continue; 
					dp[i][j] += dp[i-j][j];
				}
			}
			int m = Integer.parseInt(br.readLine());
			int ans = 0;
			for(int z = 0 ; z < n ; z ++) {
				ans += dp[m][z];
			}
			
			System.out.println(ans);
		}
	}
}
