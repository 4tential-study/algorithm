import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_11066 {

    static StringBuilder sb = new StringBuilder();

    // dp[a][b] : a ~ b 파일을 합치는데 드는 비용
    static int[][] dp = new int[501][501];

    // 누적합
    static int[] prifixSum = new int[501];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; ++tc) {
            int K = Integer.parseInt(br.readLine());

            String[] str = br.readLine().split(" ");

            // 누적합
            for(int i = 0; i < str.length; ++i){
                prifixSum[i + 1] = prifixSum[i] + Integer.parseInt(str[i]);
            }

            // DP

            // i : 구간의 크기
            for(int i = 1; i <= K; ++i){
                // j : 시작 위치
                for(int j = 1; j <= K - i; ++j){
                    dp[j][i+j] = Integer.MAX_VALUE;

                    // 나누는 지점(구간에서 왼쪽과 오른쪽을 나누는 모든 경우)
                    for(int k = j; k < i + j; ++k){
                        dp[j][i+j] = Math.min(dp[j][i+j], dp[j][k] + dp[k+1][i+j] + prifixSum[i+j] - prifixSum[j-1]);
                    }
                }
            }

            sb.append(dp[1][K] + "\n");
        }
        System.out.println(sb.toString());
    }
}
