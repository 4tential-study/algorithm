import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, result;
	static int[][] board;
	static boolean[][] visit;
	// 상 우 하 좌
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visit = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			String input = in.readLine();
			for(int j=0; j<M; j++) {
				int num = input.charAt(j) - '0';
				if(num==1) {
					board[i][j] = -1;
				}
			}
		}
		
		result = Integer.MAX_VALUE;
		
//		Queue<int[]> q = new ArrayDeque<>();
		Stack<int[]> stack = new Stack<>();
		stack.push(new int[] {0,0,1,1});
		visit[0][0] = true;
		board[0][0] = 1;
		
		/*while(!stack.isEmpty()) {
			int cy = stack.peek()[0];
			int cx = stack.peek()[1];
			int level = stack.peek()[2];
			int hasPower = stack.pop()[3];
			level++;
			
			for(int d=0; d<dy.length; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				
				if(0<=ny && ny<N && 0<=nx && nx<M && !visit[ny][nx]) {
					if(hasPower==1 && board[ny][nx]==-1) {
						visit[ny][nx] = true;
						stack.push(new int[] {ny,nx, level, 0});
						if(ny==N-1 && nx==M-1) result = Math.min(result, level);
					}
					if(board[ny][nx]!=-1){
						visit[ny][nx] = true;
						stack.push(new int[] {ny,nx, level, hasPower});
//						board[ny][nx] = level;
						if(ny==N-1 && nx==M-1) result = Math.min(result, level);
					}
				}
				
			}
			
		}*/
		dfs(0,0,1,true);
		if(result!= Integer.MAX_VALUE)  
			System.out.println(result);
		else System.out.println(-1);
	}
	
	static void dfs(int cy, int cx, int level, boolean hasPower) {
		if(cy==N-1 && cx==M-1) {
			result = Math.min(result, level);
			return;
		}
		if(level>=result) return;
		
		for(int d=0; d<dy.length; d++) {
			int ny = cy + dy[d];
			int nx = cx + dx[d];
			
			if(0<=ny && ny<N && 0<=nx && nx<M && !visit[ny][nx]) {
				if(hasPower && board[ny][nx]==-1) {
					visit[ny][nx] = true;
					dfs(ny,nx, level+1, false);
					visit[ny][nx] = false;
				}
				if(board[ny][nx]!=-1){
					visit[ny][nx] = true;
					dfs(ny,nx, level+1, hasPower);
					visit[ny][nx] = false;
				}
			}
			
		}
	}
}