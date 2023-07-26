import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2225_dp {
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line= br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);

         dp = new long[k+1][n+1];

         dp[0][0] = 1;

        for(int i=1; i <= k; i++){
            for(int j=0; j <= n; j++){
                for(int m=0; m <= j; m++){
                    dp[i][j] += dp[i-1][j-m];
                    dp[i][j] %= 1000000000;
                }
            }
        }
        System.out.println(dp[k][n]);
    }
}
