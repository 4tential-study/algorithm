import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B3067 {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int T = Integer.parseInt(in.readLine()); // testcase 개수
    for(int test_case = 0; test_case < T; test_case++){
      int coin_count = Integer.parseInt(in.readLine());
      int[] coins = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
      int target = Integer.parseInt(in.readLine());
      
      int[] DP = new int[target+1]; DP[0] = 1;

      for(int i = 0; i < coin_count; i++){
        int coin_value = coins[i];
        for(int j = coin_value; j <= target; j++){
          DP[j] += DP[j-coin_value];
        }
      }

      sb.append(DP[target]).append("\n");
    }

    System.out.println(sb);
  }
}