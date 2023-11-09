import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_9177 {
	static BufferedReader in;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{
		in = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(in.readLine());
		Queue<Character> cQ;
		StringBuilder sb =new StringBuilder();
		for(int i=1 ; i <= t ; i++) {
	
			sb.append("Data set "+i+": ");
			String[] str = in.readLine().split(" ");
			String a = str[0];
			char[] aChars = new char[a.length()+1];
			for(int j=0 ; j < aChars.length ; j++) {
				if(j==0) aChars[j] = ' ';
				else aChars[j] = a.charAt(j-1);
			}
			String b = str[1];
			char[] bChars = new char[b.length()+1];
			for(int j=0 ; j < bChars.length ; j++) {
				if(j==0) bChars[j] = ' ';
				else bChars[j] = b.charAt(j-1);
			}
			String c = str[2];
			cQ = new ArrayDeque<>();

			for(int j=0 ; j < c.length() ; j++) {
				cQ.offer(c.charAt(j));
			}
			
			boolean[][] dp = new boolean[a.length()+1][b.length()+1];

			dp[0][0] = true;
			
			for(int j=1 ; j <= b.length() ; j++) {
				if(bChars[j]==c.charAt(j-1)) dp[0][j] = dp[0][j-1];
				else dp[0][j] = false;
			}
			for(int j=1 ; j <= a.length() ; j++) {
				if(aChars[j]==c.charAt(j-1)) dp[j][0] = dp[j-1][0];
				else dp[j][0] = true;
			}
				
				
			for(int z=1 ; z <= a.length() ; z++) {
				for(int j=1 ; j <= b.length() ; j++) {
					
					char aChar = aChars[z];
					char bChar = bChars[j];
					
					char cChar = c.charAt(z + j -1);
					
					if(aChar != bChar && aChar != cChar) {
						dp[z][j] = false;
					}else if(aChar == cChar  && bChar != cChar) {
						dp[z][j] = dp[z-1][j];
					}else if(bChar == cChar && aChar!=cChar) {
						dp[z][j] = dp[z][j-1];
					}else {
						dp[z][j] = dp[z-1][j] || dp[z][j-1];
					}
				}
			}
			
//			for(int z=0 ; z <= a.length() ; z++) {	
//					System.out.println(Arrays.toString(dp[z]));
//			}
			if(dp[a.length()][b.length()]) sb.append("yes");
			else sb.append("no");
			sb.append("\n");
		
		}
		System.out.println(sb.toString());
	}
}
