// https://www.acmicpc.net/problem/1309

import java.util.Scanner;
/*
 * 	모듈러(modular) 연산 공식
 *  (a+b) mod n = ((a mod n) + (b mod n)) mod n
 *  (a*b) mod n = ((a mod n) * (b mod n)) mod n
 */
public class B1309 {
	static int MOD = 9901;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[][] dp = new int[N+1][3];
		
        // N번째 칸에서 사자를 안 넣거나, 왼쪽에 넣었을 때, 오른쪽에 넣었을 때의 경우의 수
		dp[N][0] = dp[N][1] = dp[N][2] = 1;
		for(int i = N-1; i >= 1; i--) {
			dp[i][0] = (dp[i+1][0] + dp[i+1][1] + dp[i+1][2]) % MOD;
			dp[i][1] = (dp[i+1][0] + dp[i+1][2]) % MOD; // 이전에 왼쪽에 넣었다면, 또 다시 왼쪽에 넣을 수 없다.
			dp[i][2] = (dp[i+1][0] + dp[i+1][1]) % MOD; // 위와 동일
		}
		
		int result = (dp[1][0] + dp[1][1] + dp[1][2]) % MOD;
		System.out.println(result);
        sc.close();
	}
}