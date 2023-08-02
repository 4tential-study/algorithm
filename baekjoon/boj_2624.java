import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_2624 {
	static BufferedReader br;
	static StringTokenizer st;
	static int[][] dp;
	static Coin[] coins;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine()); //동전가짓수
		coins = new Coin[m];
		dp = new int[m+1][n+1];
		for(int i=1 ; i <= n  ; i++) {
			dp[1][i] = 1;
			
		}
		
		
		for(int i=0 ; i < m ; i++) {
			st = new StringTokenizer(br.readLine());
			
			int price = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			Coin coin  = new Coin(price , cnt);
			coins[i]=coin;
		}
		
	}
	
	
	public static class Coin{
		int price;
		int cnt;
		Coin(int price, int cnt){
			this.price = price;
			this.cnt = cnt;
		}
	}
}
