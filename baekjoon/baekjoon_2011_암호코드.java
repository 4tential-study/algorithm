import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static String crypto;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		crypto = in.readLine();
		
		int dp[][] = new int[crypto.length()][2];
		
		if(1<=crypto.charAt(0)-'0' && crypto.charAt(0)-'0'<=9) {
			dp[0][0] = 1;
			dp[0][1] = 0;
		}
		
		
		int num = 0;
		for(int i=1; i<crypto.length(); i++) {
			num = Integer.parseInt(crypto.substring(i, i+1));
			if(1<=num && num<=9) {
				dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % 1000000;
			}
			
			num = Integer.parseInt(crypto.substring(i-1, i+1)); 
			if(10<=num && num<=26) {
				dp[i][1] = dp[i-1][0];
			}
		}
		System.out.println((dp[crypto.length()-1][0]+dp[crypto.length()-1][1])% 1000000);
	}
	
}