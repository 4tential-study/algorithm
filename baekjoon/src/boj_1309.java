import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [점화식 설명]
// o: 사자배치, x: 사자배치안함
// dp[i][0] : 직전이 ox인 경우
// dp[i][1] : 직전이 xo인 경우
// dp[i][2] : 직전이 xx인 경우

public class boj_1309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[N][3];

        dp[0][0] = dp[0][1] = dp[0][2] = 1;

        for(int i = 1; i < N ; i++){
            dp[i][0] = (dp[i-1][1] + dp[i-1][2]) % 9901;
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % 9901;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
        }

        System.out.println((dp[N-1][0] + dp[N-1][1] + dp[N-1][2]) % 9901);
    }
}
