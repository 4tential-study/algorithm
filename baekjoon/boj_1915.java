import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1915 {
	static BufferedReader in;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{
		in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = in.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int m = Integer.parseInt(str[1]);
		int[][] dp = new int[n][m];
		int[][] map = new int[n][m];
		for(int i=0 ; i < n ; i++) {
			String ins = in.readLine();//0100,  1의 위치를 찾으려면..?????		
			for(int j=0 ; j < m ; j++) {
				map[i][j] = ins.charAt(j)-'0';			
			}
		}
		int max = 0;
		
		//init
		for(int i=0 ; i < n ; i ++) {
			dp[i][0] = map[i][0];
		
		}
		
		for(int i=0 ; i < m ; i++) {
			dp[0][i] = map[0][i];
		}
		
		
		
		for(int i=1 ; i < n ; i++) {
			for(int j=1 ; j < m ; j++) {
				if(map[i][j] == 1) {
					dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
				} else dp[i][j] = 0;
				
			
			}
		}
		
		for(int i=0 ; i < n ; i++) {
			for(int j=0 ; j < m ; j++) {
				max = Math.max(max, dp[i][j]);
			}
		}
		System.out.println(max * max);
		
	}
}