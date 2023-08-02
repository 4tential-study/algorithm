
import java.io.*;


public class boj_15988 {
    static long[] dp  = new long[1_000_001];
    static final long mod = 1_000_000_009;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for(int i=4 ; i <= 1000000 ;i++){
            //1+3 , 2+2 , 3+1 의 경우의 수를 더한다. ==> dp[3] + dp[2] + dp[1] 의 경우의 수를 더한다.
            dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % mod;
        }

        for(int i=0 ; i < t ; i++){
            n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }

    }
}
