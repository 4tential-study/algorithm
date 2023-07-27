import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class boj_2225 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[][] dp = new int[K+1][N+1];
        
        for(int i=0 ; i < N ; i++) {
        	dp[1][i] = 1;
        }
	    
        for(int i=1 ; i<=K ; i++ ) {
		   for(int j=0 ; j <= N ; j++ ) {
			   for(int l=0;l<=j;l++) {
		            dp[i][j] += dp[i-1][j-l];
		        }
		   }
	    }
        System.out.println(dp[K][N]);
       
    }
}
