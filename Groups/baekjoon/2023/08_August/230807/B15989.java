import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B15989 {
  static int MAX_LENGLTH = 10_000;
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int T = Integer.parseInt(in.readLine());

    // 입력받은 최대값이 10000으로 생각보다 작은 것 같아 미리 계산하기로 하였다.
    int[] DP = new int[MAX_LENGLTH+1];
    Arrays.fill(DP, 1);

    for(int i = 2; i <= 3; i++){
      for(int j = i; j <= MAX_LENGLTH; j++){
        DP[j] += DP[j-i];
      }
    } // 계산 끝


    for(int test_case = 0; test_case < T; test_case++){
      int target = Integer.parseInt(in.readLine());
      sb.append(DP[target]).append("\n");
    }

    System.out.println(sb);
  }
}
