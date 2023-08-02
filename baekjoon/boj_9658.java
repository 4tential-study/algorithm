

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_9658 {
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new int[1001];
		
		dp[1] = 0; //패배
		dp[2] = 1; //승리
		dp[3] = 0;
		dp[4] = 1;
		for(int i=5 ; i <= n ; i++) {
			dp[i] = checkWin(i);
		}
		
		
		if(dp[n] == 1) System.out.println("SK");
		else System.out.println("CY");
		
	}
	
	public static int checkWin(int i) {
		if (dp[i-1] == 1  &&dp[i-3] == 1 &&dp[i-4] ==1) {
			return 0;
		}
		else return 1;
	}
	

}
