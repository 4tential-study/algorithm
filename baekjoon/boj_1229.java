import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static List<Integer> sixNum;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        sixNum = new ArrayList<>();
        dp = new int[N + 1];

        // N보다 작은 육각수 미리 구하기, 등차수열(+1, +5, +9, +13)
        int dif = 1;
        for(int cur = 1; cur <= N; cur += (dif += 4)) sixNum.add(cur);

        // dp 배열 초기화
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 0; i < sixNum.size(); ++i){
            int cur = sixNum.get(i);

            // DP
            for(int idx = cur; idx <= N; ++idx){
                dp[idx] = Math.min(dp[idx], dp[idx - cur] + 1);
            }
        }

        System.out.println(dp[N]);
    }
}