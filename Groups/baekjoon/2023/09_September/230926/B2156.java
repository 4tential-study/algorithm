import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B2156 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int wine_count = Integer.parseInt(in.readLine());
		int[] wines = new int[wine_count+1];
		
		for(int i = 1; i <= wine_count; i++)
			wines[i] = Integer.parseInt(in.readLine());
		
		// 포도주
		int[][] DP = new int[wine_count+1][3];
		
		// DP[i][n] = i번째 포도주일 때 연속으로 마신 n잔. (i >= 1)
		DP[0][0] = DP[0][1] = DP[0][2] = 0;
		
		for(int i = 1; i <= wine_count; i++) {
			DP[i][0] = Math.max(DP[i-1][0], Math.max(DP[i-1][1], DP[i-1][2]));
			DP[i][1] = DP[i-1][0] + wines[i];
			if(i >= 2)	DP[i][2] = DP[i-1][1] + wines[i];
		}
		
		int answer = Math.max(DP[wine_count][0], Math.max(DP[wine_count][1], DP[wine_count][2]));
		
		System.out.println(answer);
	}
}