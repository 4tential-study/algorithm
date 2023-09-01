import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B11726 {
    static int MOD = 10_007;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] DP = new int[N+1];
        DP[0] = DP[1] = 1;
        for(int i = 2; i <= N; i++)
            DP[i] = (DP[i-2] + DP[i-1]) % MOD;

        System.out.println(DP[N]);
    }
}