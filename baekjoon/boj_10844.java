import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_10844 {
	static int N ;
	static int[][] dp;
	static boolean[] visited;
	static int count ;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N+1][10];
		//골라야하는 개수
		
		for(int i=0 ; i < 10; i++) {
			dp[0][i] = 0;
			dp[1][i]= 1;
		}
		
		for(int i=2 ; i <= N  ; i++) {
			for(int j = 0 ; j < 10 ; j++) {
				if (j-i < 0 || j+i-1 > 9 ) {
					System.out.println("if"+j);
					dp[i][j] = 2* (dp[i-1][j]) - 1;
					System.out.println(dp[i][j]);
				} else {
					System.out.println("else");
					dp[i][j] = 2* (dp[i-1][j]);
					System.out.println(dp[i][j]);
				}
				
			}
			
		}
		int sum = 0;
		for(int i=0 ; i < 10 ; i++) {
			System.out.println(dp[N][i]);
			sum += dp[N][i];
		}
		System.out.println(sum);
		
		
	}
	
	
}
