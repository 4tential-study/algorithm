import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M;
	// 상 우 하 좌
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static char[][] board;
	static boolean[][] visit;
	static int sheepTotalCount, wolfTotalCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		visit = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			String input = in.readLine();
			for(int j=0; j<M; j++) {
				board[i][j] = input.charAt(j);
				if(board[i][j]=='#') visit[i][j] = true;
				if(board[i][j]=='o') sheepTotalCount++; 
				if(board[i][j]=='v') wolfTotalCount++;
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				bfs(i,j);
			}
		}
		
		System.out.println(sheepTotalCount+" "+wolfTotalCount);
	}
	
	static void bfs(int y, int x) {
		
		int sheepCount = 0;
		int wolfCount = 0;
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {y,x});
		visit[y][x] = true;
		
		while(!q.isEmpty()) {
			int cy = q.peek()[0];
			int cx = q.poll()[1];
			
			for(int d=0; d<dy.length; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				if(0<=ny && ny<N & 0<=nx && nx<M && !visit[ny][nx]) {
					q.offer(new int[] {ny, nx});
					visit[ny][nx] = true;
					if(board[ny][nx]=='o') sheepCount++;
					if(board[ny][nx]=='v') wolfCount++;
				}
			}
		}
		
		if(sheepCount > wolfCount) wolfTotalCount -= wolfCount;
		else sheepTotalCount -= sheepCount;
		
	}
}