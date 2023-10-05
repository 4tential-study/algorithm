package W0925;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_2156 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int dp[] = new int[N+1];
		int arr[] = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		
		dp[1] = arr[1];
		
		if(N>1)
			dp[2] = dp[1] + arr[2];
		
		for(int i=3; i<=N; i++) {
			// 현재것까지 두개를 연속으로 마신 경우와 
			// 한번 끊고 현재것을 마신 경우,
			// 그리고 현재 것을 마시지 않는 경우를 확인
			dp[i] = Math.max(dp[i-3] + arr[i-1] + arr[i], dp[i-2] + arr[i]);
			dp[i] = Math.max(dp[i], dp[i-1]);
		}
		
		System.out.println(dp[N]);
	}
}
