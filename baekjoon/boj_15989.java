import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_15989 {
	static BufferedReader br;
	static int[][] dp;
	//dp[금액][식의 마지막에 오는 수]
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		dp = new int[10001][3];
		
		int n = Integer.parseInt(br.readLine());
		dp[0][0]=dp[0][1]=dp[0][2]=0;
		dp[1][0]=1;
		dp[1][1]=dp[1][2]=0;
		dp[2][0]=dp[2][1]=1;
		dp[2][2]=0;
		dp[3][0]=dp[3][1]=dp[3][2]=1;
		
		for(int i=4 ; i < 10001 ; i++) {
			dp[i][0] = dp[i-1][0];
			dp[i][1] = dp[i-2][0]+ dp[i-2][1];
			dp[i][2] = dp[i-3][0] + dp[i-3][1] + dp[i-3][2];
		}
		
		
		for(int i=0 ; i <n ; i++) {
			int x = Integer.parseInt(br.readLine());
			System.out.println(dp[x][0]+dp[x][1]+dp[x][2]);
			
		}
	}
}
