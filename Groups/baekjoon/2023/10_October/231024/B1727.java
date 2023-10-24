import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B1727 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");
		int m = Integer.parseInt(input[0]);
		int n = Integer.parseInt(input[1]);
		
		int[] men = new int[m+1];
		input = in.readLine().split(" ");
		for(int i = 1; i <= m; i++)	men[i] = Integer.parseInt(input[i-1]);
		Arrays.sort(men);
		
		int[] women = new int[n+1];
		input = in.readLine().split(" ");
		for(int i = 1; i <= n; i++)	women[i] = Integer.parseInt(input[i-1]);
		Arrays.sort(women);
		
		int[][] DP = new int[m+1][n+1];
		for(int i = 1; i <= m; i++) {
			for(int j = 1; j <= n; j++) {
				if(i == j) {
					DP[i][j] = DP[i-1][j-1] + Math.abs(men[i] - women[j]);
				}
				else if(i > j) {
					DP[i][j] = Math.min(DP[i-1][j], DP[i-1][j-1] + Math.abs(men[i] - women[j]));
				}
				else {
					DP[i][j] = Math.min(DP[i][j-1], DP[i-1][j-1] + Math.abs(men[i] - women[j]));
				}
			}
		}
		
		System.out.println(DP[m][n]);
	}
}
