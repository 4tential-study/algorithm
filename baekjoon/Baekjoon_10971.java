import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 다음 도시 a로 갈 때의 비용과 현재까지의 비용의 합산이 result보다 크다면 백트래킹.
	// int visited[]로 이미 방문한 도시는 재방문 하지 않는다. 단, 이와 상관 없이 모든 도시를 돌았다면 마지막 도시를 재방문한다.
	
	static int[][] map;
	static int N;
	static int result=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		boolean[] v = new boolean[N];
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			recursion(i,i,0,v);
		}
		
		System.out.println(result);
	}
	
	static void recursion(int startPoint, int nowPoint, int costSum, boolean[] v) {
		v[nowPoint] = true;
		boolean isFinal=true;
		
		for(int next=0; next<N; next++) {
			if(!v[next]){
				if(map[nowPoint][next]!=0) {	// 만약 다음 경로로 가는 비용이 0이라면 고려하지 않음
					int cost = costSum + map[nowPoint][next];
					// 다음 포인트로 가는 비용까지 합산한 값이 결과값보다 작을 때 선택 (백트래킹 구현?)
					if(cost < result) recursion(startPoint, next, cost, v);
				}
				isFinal = false;
			}
		}
		// 모든 도시를 방문했다면
		if(isFinal) {
			// 귀환할 때도 비용이 0이라면 고려X
			if(map[nowPoint][startPoint]!=0) result = Math.min(result, costSum+map[nowPoint][startPoint]);
		}
		
		v[nowPoint] = false;
	}
}