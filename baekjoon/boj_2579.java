import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2579 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] scores = new int[N+1];
        int[][] dp = new int[N+1][2];

        for(int i = 1;  i <= N ; i++){
            int score = Integer.parseInt(br.readLine());
            scores[i] = score;
        }
        // 초기화
        dp[0][0] = dp[0][1] = 0;
        dp[1][1] = dp[1][0] = scores[1];

        for(int i = 2; i <= N ; i++){
            dp[i][0] = scores[i] + dp[i-1][1];
            dp[i][1] = scores[i] + Math.max(dp[i-2][0], dp[i-2][1]);
        }

        System.out.println(Math.max(dp[N][0], dp[N][1]));
    }
}
