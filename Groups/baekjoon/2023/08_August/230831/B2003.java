import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B2003 {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String[] input = in.readLine().split(" ");

    int N = Integer.parseInt(input[0]); // 수의 개수
    int target = Integer.parseInt(input[1]); // 원하는 합

    input = in.readLine().split(" ");
    int[] DP = new int[N+1];
    for(int i = 0; i < N; i++)
      DP[i+1] = DP[i] + Integer.parseInt(input[i]);
    
    int start = 0, end = 1;
    int count = 0; // 경우의 수

    while(end <= N){
      int sum = DP[end] - DP[start];
      if(sum == target) count++;

      if(sum <= target) end++;
      else start++;
    }

    System.out.println(count);
  }
}
