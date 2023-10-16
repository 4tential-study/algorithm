import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, R;
	static int[][] adjMatrix;
	static int[] itemsCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
//		N = 100;
//		M = 15;
//		R = 100;
		adjMatrix = new int[N][N];
		itemsCount = new int[N];
		
		st = new StringTokenizer(in.readLine());
		for(int i=0; i<N; i++) {
			itemsCount[i] = Integer.parseInt(st.nextToken());
//			itemsCount[i] = i%30;
		}
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(in.readLine());
			
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int l = Integer.parseInt(st.nextToken());
			
//			int a = i;
//			int b = (i+50)%N;
//			int l = (i+10)%16;
			
			adjMatrix[a][b] = l;
			adjMatrix[b][a] = l;
		}
		
		// 플로이드워샬
		// 각 정점에서 다른 정점으로의 최단거리를 저장 (M의 크기와 관계 없이 저장)
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(i==j) continue;
					if(adjMatrix[i][k]!=0
						&& adjMatrix[k][j]!=0
						&& (adjMatrix[i][j]==0
								|| adjMatrix[i][j]>adjMatrix[i][k]+adjMatrix[k][j])) {
						adjMatrix[i][j] = adjMatrix[i][k]+adjMatrix[k][j];
					}
				}
			}
		}
		
		int result = 0;
		
		for(int i=0; i<N; i++) {
			int sum = itemsCount[i];
			for(int j=0; j<N; j++) {
				if(i==j || adjMatrix[i][j]==0) continue;
				if(adjMatrix[i][j]<=M) {
					sum+=itemsCount[j];
				}
			}
			result = Math.max(result, sum);
		}
		
		System.out.println(result);
	}
}