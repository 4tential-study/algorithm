import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int[][] del = { {-1,0}, {0,1}, {1,0}, {0,-1} }; 
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int unriped = 0;
		
		int[][] board = new int[N][M];
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j]==0) unriped++;
				if(board[i][j]==1) q.offer(new int[] {i,j});
			}
		}

		int day = 0;
		while(!q.isEmpty()) {
			if(unriped==0) break;
			int qSize = q.size();
			
			for(int i=0; i<qSize; i++) {
				int cy = q.peek()[0];
				int cx = q.poll()[1];
				
				for(int d=0; d<del.length; d++) {
					int ny = cy + del[d][0];
					int nx = cx + del[d][1];
					
					if(canGo(ny,nx,N,M,board)) {
						board[ny][nx] = 1;
						q.offer(new int[] {ny,nx});
						unriped--;
					}
				}
			}
			day++;			
		}
		
		if(unriped==0) System.out.println(day);
		else System.out.println(-1);
	}
	static boolean canGo(int y, int x, int N, int M, int[][] board) {
		return (0<=y && y<N && 0<=x && x<M && board[y][x]==0);
	}
}