import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int M[][] = new int[K][N];
		
		for(int i=0; i<N; i++) {
			M[0][i] = 1;
		}
		for(int i=0; i<K; i++) {
			M[i][0] = i+1;
		}
		for(int i=1; i<K; i++) {
			for(int j=1; j<N; j++) {
				M[i][j] = (M[i-1][j] + M[i][j-1])%1_000_000_000;
			}
		}
		
		System.out.println(M[K-1][N-1]);
	}
}