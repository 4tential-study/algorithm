import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));		
		
		int N = Integer.parseInt(in.readLine());
		int[][] map = new int[N][3];
		int[][] minDP = new int[N][3];
		int[][] maxDP = new int[N][3];
		
		StringTokenizer st =new StringTokenizer(in.readLine());
		for(int j=0; j<3; j++) {
			map[0][j] = Integer.parseInt(st.nextToken());
			minDP[0][j] = map[0][j];
			maxDP[0][j] = map[0][j];
		}
		for(int i=1; i<N; i++) {
			st =new StringTokenizer(in.readLine());
			for(int j=0; j<3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1; i<N; i++) {
			minDP[i][0] = Math.min(minDP[i-1][0], minDP[i-1][1])+map[i][0];
			maxDP[i][0] = Math.max(maxDP[i-1][0], maxDP[i-1][1])+map[i][0];
			
			minDP[i][1] = Math.min(minDP[i-1][0], Math.min(minDP[i-1][1], minDP[i-1][2]))+map[i][1];
			maxDP[i][1] = Math.max(maxDP[i-1][0], Math.max(maxDP[i-1][1], maxDP[i-1][2]))+map[i][1];
			
			minDP[i][2] = Math.min(minDP[i-1][1], minDP[i-1][2])+map[i][2];
			maxDP[i][2] = Math.max(maxDP[i-1][1], maxDP[i-1][2])+map[i][2];
		}
		
		System.out.print(Math.max(maxDP[N-1][0], Math.max(maxDP[N-1][1], maxDP[N-1][2])));
		System.out.print(" "+Math.min(minDP[N-1][0], Math.min(minDP[N-1][1], minDP[N-1][2]))+"\n");
	}
}