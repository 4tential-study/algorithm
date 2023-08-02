import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_2624 {
	static BufferedReader br;
	static StringTokenizer st;
	static int[][] dp;
	static ArrayList<int[]> coins = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine()); //동전가짓수
	
		dp = new int[m+1][n+1];
		for(int i=1 ; i <= n  ; i++) {
			dp[1][i] = 1;
			for(int[] each : coins) {
				if(i % each[0] == 0) dp[1][i] += 1;
			}
		}
		
		
		for(int i=0 ; i < m ; i++) {
			st = new StringTokenizer(br.readLine());
			int[] coin = new int[2];
			
			coin[0] = Integer.parseInt(st.nextToken());
			coin[1] = Integer.parseInt(st.nextToken());
			coins.add(coin);
		}
		
//		for(int i = 2 ; i <= m ; i++) {
//			for(int j = 1; j <= n ;j++) {
//				
//				dp[i][j] = dp[i-1][j];
//			}
//		}
	}	
}
