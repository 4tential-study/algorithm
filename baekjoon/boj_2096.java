import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2096 {
	static BufferedReader br;
	static StringTokenizer st;
	static int[][] dp;
	static int[][] mindp;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n+1][3];
		dp = new int[n+1][3];
		mindp = new int[n+1][3];
		
		for(int i=0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0 ; j < 3 ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());		
			}
		}
		
		mindp[0][0] = dp[0][0] = map[0][0];
		mindp[0][1] = dp[0][1] = map[0][1];
		mindp[0][2] = dp[0][2] = map[0][2];
		
		for(int i=1 ; i <= n ; i++) {
			for(int j=0; j < 3 ; j++) {
				dp[i][0] = Math.max(dp[i-1][0]+map[i][0], dp[i-1][1]+map[i][0]);
				dp[i][1] = Math.max(Math.max(dp[i-1][0]+map[i][1], dp[i-1][1]+map[i][1]), dp[i-1][2]+map[i][1] );
				dp[i][2] = Math.max(dp[i-1][2]+map[i][2], dp[i-1][1]+map[i][2]);
			}
		}
		
		System.out.print(Math.max(Math.max(dp[n][0], dp[n][1]), dp[n][2])+" ");
		
		for(int i=1 ; i <= n ; i++) {
			for(int j=0; j < 3 ; j++) {
				mindp[i][0] = Math.min(mindp[i-1][0]+map[i][0], mindp[i-1][1]+map[i][0]);
				mindp[i][1] = Math.min(Math.min(mindp[i-1][0]+map[i][1], mindp[i-1][1]+map[i][1]), mindp[i-1][2]+map[i][1] );
				mindp[i][2] = Math.min(mindp[i-1][2]+map[i][2], mindp[i-1][1]+map[i][2]);
			}
		}
		System.out.println(Math.min(Math.min(mindp[n][0], mindp[n][1]), mindp[n][2]));
		
	}
	
	
}
