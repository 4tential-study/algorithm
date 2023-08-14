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
		// 전우후좌 + 상하 
		int[][] del = { {0,-1,0}, {0,0,1}, {0,1,0}, {0,0,-1},{-1,0,0},{1,0,0} }; 
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int unriped = 0;
		
		int[][][] board = new int[H][N][M];
		Queue<int[]> q = new ArrayDeque<int[]>();

		for(int h=0; h<H; h++) {
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j=0; j<M; j++) {
					board[h][i][j] = Integer.parseInt(st.nextToken());
					if(board[h][i][j]==0) unriped++;
					if(board[h][i][j]==1) q.offer(new int[] {h,i,j});
				}
			}
		}
		
		/*
		 * 입력 받을 때 높이 적용 재확인
		 * BFS에 높이 적용
		 */

		int day = 0;
		while(!q.isEmpty()) {
			if(unriped==0) break;
			int qSize = q.size();
			
			for(int i=0; i<qSize; i++) {
				int cz = q.peek()[0];
				int cy = q.peek()[1];
				int cx = q.poll()[2];
				
				for(int d=0; d<del.length; d++) {
					int nz = cz + del[d][0];
					int ny = cy + del[d][1];
					int nx = cx + del[d][2];
					
					if(canGo(nz,ny,nx,H,N,M,board)) {
						board[nz][ny][nx] = 1;
						q.offer(new int[] {nz, ny,nx});
						unriped--;
					}
				}
			}
			day++;			
		}
		
		if(unriped==0) System.out.println(day);
		else System.out.println(-1);
	}
	static boolean canGo(int z, int y, int x, int H, int N, int M, int[][][] board) {
		return (0<=z && z<H && 0<=y && y<N && 0<=x && x<M && board[z][y][x]==0);
	}
}