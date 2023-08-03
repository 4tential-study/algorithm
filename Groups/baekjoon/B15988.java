import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B15988 {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(in.readLine());
    int[] Inputs = new int[T];

    int max = Integer.MIN_VALUE; // DP 크기.
    for(int i = 0; i < T; i++) {
      Inputs[i] = Integer.parseInt(in.readLine());
      max = Math.max(max, Inputs[i]);
    }

    int[] DP = new int[max+1];
    DP[0] = 1;
    // DP의 크기만큼 미리 계산함.
    for(int i = 1; i < DP.length; i++){ // DP index
      for(int j = 1; i >= j && j <= 3; j++)  // 1, 2, 3
        DP[i] = (DP[i] + DP[i-j]) % 1_000_000_009;
    }

    for(int input : Inputs){
      sb.append(DP[input]).append("\n");
    }

    System.out.println(sb);
  }
}