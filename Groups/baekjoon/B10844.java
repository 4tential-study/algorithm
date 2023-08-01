import java.util.Scanner;

public class B10844 {
  static int MOD = 1_000_000_000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num_length = sc.nextInt();
        
        int[][] DP = new int[num_length+2][10];
        for(int i = 1; i < 10; i++){
          DP[1][i] = 1;
        }

        // DP[n][k] = n자리 계단 수 중 끝 자리 수가 k인 경우의 수.
        for(int n = 2; n <= num_length; n++){
          // 끝자리 수가 0, 9가 되려면 이전 단계의 끝자리수가 1, 8일 때 외에는 방법이 없다.
          DP[n][0] = DP[n-1][1];  DP[n][9] = DP[n-1][8]; 
          for(int end = 1; end < 9; end++)
              DP[n][end] = ((DP[n-1][end-1] % MOD) + (DP[n-1][end+1] % MOD)) % MOD;
        }
        int result = 0;
        for(int i = 0; i < 10; i++){
          result = ((result % MOD) + (DP[num_length][i] % MOD)) % MOD;
        }
        System.out.println(result);
        sc.close();
    }
}