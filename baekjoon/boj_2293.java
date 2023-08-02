import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj_2293 {
    static StringTokenizer st;
    static int[] dp;
    static int n;
    static int k;
    static HashSet<Integer> set  = new HashSet<>();
    static int[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = br.readLine().split(" ");
        n = Integer.parseInt(strings[0]);
        k = Integer.parseInt(strings[1]);
        coins = new int[n];
        dp = new int[k+1];
        for(int i=0 ; i < n ; i++){
            Integer coin = Integer.parseInt(br.readLine());
            coins[i] = coin;
            dp[coin] += 1;
        }

        for(int i=1 ; i <= k ; i++){
            set.clear();
            for(int j=0 ; j < n ; j++){
                if (set.contains(i-coins[j])) continue;
                if (i > coins[j]) {
//                    if (i % coins[j] == 0 ) continue;
                    dp[i] += dp[i - coins[j]];
                    set.add(coins[j]);
                }
            }
        }

        System.out.println(dp[k]);
    }
}
