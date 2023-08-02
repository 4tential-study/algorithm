package Algorithm.boj.day0731;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_10844 {
	
	static StringBuilder sb = new StringBuilder();
		
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
//		if(N%7==1 || N%7==3) {
//			System.out.println("CY");
//		}else {
//			System.out.println("SK");
//		}
		int dp[] = new int[N+5];
		
		dp[1] = dp[3] = 0;
		dp[2] = dp[4] = dp[5] = 1;

		for (int i = 6; i <= N; i++) {
			if (dp[i - 1] == 1 && dp[i - 3] == 1 && dp[i - 4] == 1) dp[i] = 0;
			else dp[i] = 1;
		}
		if(dp[N]==0) System.out.println("CY");
		else System.out.println("SK");
		/*
		 * ※ 문제 분석해보기
		 * 한 번에 돌 1개, 3개, 4개를 가져올 수 있다
		 * 1개와 3개를 가져오는 것은 같은 결과를 가져온다.
		 * 4개는 다른 결과를 가져온다.
		 * 4개를 가져올 수 없었다면 N이 홀수일 때 CY 승, 짝수일 때 SK 승이 고정이다.
		 * 둘은 서로 이기기 위해 4개를 가져오는 것으로 현재의 "홀짝 상황"을 바꾸려고 한다.
		 * 
		 */
	}
}
