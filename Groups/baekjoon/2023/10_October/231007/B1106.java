import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1106 {
	private static final int INF = 999_999_999;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(in.readLine());
		int C = Integer.parseInt(st.nextToken());		// 목표 인원 수
		int city = Integer.parseInt(st.nextToken());	// 도시 개수
		
		int[][] citys = new int[city][2];
		
		for(int i = 0; i < city; i++) {
			st = new StringTokenizer(in.readLine());
			citys[i][0] = Integer.parseInt(st.nextToken()); // 비용
			citys[i][1] = Integer.parseInt(st.nextToken()); // 얻는 고객 수
		}
		
		int[] DP = new int[C+101];
		Arrays.fill(DP, INF);
		DP[0] = 0;
		
		for(int i = 0; i < city; i++) {
			int cost = citys[i][0];	int p = citys[i][1];
			for(int people = p; people < C+101; people++) {
				DP[people] = Math.min(DP[people], DP[people - p] + cost);
			}
		}
		
		int result = Integer.MAX_VALUE;
		for(int i = C; i < C+101; i++) {
			result = Math.min(DP[i], result);
		}
		
		System.out.println(result);		
	}
}
