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
		
		dp = new int[N][10];
		//골라야하는 개수
		
		for(int i=0 ; i < 10; i++) {
			dp[1][i]= 1;
		}
		
		for(int i=1 ; i < N  ; i++) {
			for(int j = 0 ; i <= 9 ; j++) {
				if (i-2 > 0 ) {
					dp[i][j] = 2*(dp[i-1][j]) - 1;
				} else if(i+2 < 9) {
					
				}
				
			}
			
		}
		
		
	}
	
	
}
