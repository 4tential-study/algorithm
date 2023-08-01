import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(in.readLine());
		int LIMITER = 1_000_000_000;
		
		// DP[j][0] : i-1번째의 j의 경우의 수
		// DP[j][1] : i번째의 j의 경우의 수
		long[][] DP = new long[10][2];
		for(int i=0; i<=9; i++) {
			DP[i][0] = 1;
			DP[i][1] = 1;
		}
		
		for(int i=2; i<=n; i++) {
			long cnt=0;
			sb.append("=== "+i+" ===\n");
//			System.out.printf("%d : %d %d\n", 0, DP[0][0], DP[0][1]);
			for(int j=0; j<=9; j++) {
				if(j==0) {
					DP[j][1] = DP[j+1][0];
					sb.append(String.format("%d : %d = %d\n", j, DP[j+1][0], DP[j][1]));
				}
				else if(j!=9) {
//					DP[j][1] = (int)(((long)DP[j-1][0] + (long)DP[j+1][0])%LIMITER);
					DP[j][1] = (DP[j-1][0] + DP[j+1][0])%LIMITER;
					sb.append(String.format("%d : %d+%d = %d\n", j, DP[j-1][0], DP[j+1][0], DP[j][1]));
//					cnt= (int)((long)DP[j][1] + (long)cnt % LIMITER);
					cnt += DP[j][1]%LIMITER;
				}else {
					DP[j][1] = DP[j-1][0];
					sb.append(String.format("%d : %d\n", j, DP[j-1][0]));
//					cnt= (int)((long)DP[j][1] + (long)cnt % LIMITER);
					cnt += DP[j][1]%LIMITER;
				}
			}
			sb.append("=== cnt : "+cnt+" ===\n");
			for(int j=0; j<=9; j++) {
				// DP 갱신
				DP[j][0] = DP[j][1];
			}
		}
		
		long result=0;
		for(int i=1; i<=9; i++) {
//			result= (int)(((long)DP[i][0] + (long)result) % LIMITER);
			result = (DP[i][0] + result) % LIMITER;
		}
		
//		System.out.println(sb);
		System.out.println(result);
	}
}