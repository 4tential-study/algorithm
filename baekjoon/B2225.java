// https://www.acmicpc.net/problem/2225
/*
 * 	모듈러(modular) 연산 공식
 *  (a+b) mod n = ((a mod n) + (b mod n)) mod n
 *  (a*b) mod n = ((a mod n) * (b mod n)) mod n
 */
import java.util.Scanner;

public class B2225 {
    static int MOD = 1_000_000_000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        // 정수 K개를 모두 합하여 정수 N이 나오는 경우의 수
        int[][] DP = new int[N+1][K+1]; 

        // 정수 1개로는 정수 i이 나오는 것은 그 1개가 자기 자신일 경우이므로 경우의 수는 1가지
        for(int i = 1; i <= N; i++) DP[i][1] = 1;
        
        // 정수 i개를 합하여 정수 0이 나오는 경우의 수는 모두 1가지
        for(int i = 1; i <= K; i++) DP[0][i] = 1;


        /*
         * 먼저 점화식을 생각해보았다.
         * DP[N][K] = DP[0][K-1] + DP[1][K-1] + ... + DP[N-1][K-1] + DP[N][K-1]
         * 
         * 여기서 위의 점화식을 다시 이용하면
         * (DP[0][K-1] + DP[1][K-1] + ... + DP[N-1][K-1]) 은 DP[N-1][K]임을 알 수 있다.
         * 
         * 그렇다면 다시 정리하여서
         * DP[N][K] = DP[N-1][K] + DP[N][K-1] 으로 쉽게 정리할 수 있었다.
         */
        for(int n = 1; n <= N; n++){
            for(int k = 2; k <= K; k++){
                DP[n][k] = (DP[n-1][k] + DP[n][k-1]) % MOD;
            }
        }
        System.out.println(DP[N][K]);
        sc.close();
    }
}
