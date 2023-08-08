import java.util.*;
import java.io.*;



public class boj_2468 {
	static BufferedReader br;
	static StringTokenizer st;
	static int n ;
	static int[][] board;
	static boolean[][] visited;
	static int[] dx = new int[] {0, 0, -1, 1};
	static int[] dy = new int[] {-1, 1, 0, 0};
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		visited= new boolean[n][n];
		int min = 999999;
		int max =0;
		for(int i=0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < n ; j++) {
				board[i][j]= Integer.parseInt(st.nextToken());
				if(min > board[i][j]) min = board[i][j];
				if(max < board[i][j]) max = board[i][j];
			}
		}
		int maxA = 0;
		for(int std = 0 ; std < max ; std++) {
			visited = new boolean[n][n];
			int answer = 0;

			for(int i=0 ; i < n ; i++) {
				for(int j=0 ; j < n ; j++) {
					if(board[i][j]> std && !visited[i][j]) {
						dfs(new int[]{i,j}, std);
						answer++;
					}					
				}
			}
			maxA = Math.max(maxA, answer);
		}
		
		System.out.println(maxA);
		
		
	}
	
	public static void dfs(int[] start, int std) {
		Stack<int[]> stack = new Stack<>();
		
		stack.add(start);
		while(!stack.isEmpty()) {
			int[] pop =stack.pop();
			int y = pop[0];
			int x = pop[1];
			visited[y][x] = true;
			for(int i=0 ; i < 4 ; i++) {
				int ay = y + dy[i];
				int ax = x + dx[i];
				if(ax >= 0 && ax < n && ay >=0 && ay < n  && std < board[ay][ax]&& !visited[ay][ax]) {
					stack.add(new int[] {ay,ax});
					visited[ay][ax] = true;
				}
			}
		}	
	}
	
//	public static int bfs(int[] start, int std ) {
//		Queue<int[] > queue = new LinkedList<>();
//		
//		queue.add(start);
//		int ans =0;
//		while(!queue.isEmpty()) {
//			int[] pop = queue.poll();
//			int y = pop[0];
//			int x = pop[1];
//			
//			for(int i=0 ; i < 4 ; i++) {
//				int ay = y + dy[i];
//				int ax = x + dx[i];
//				if(ax >=0 && ax < n && ay >=0 && ay < n && std <= board[ay][ax] && !visited[ay][ax]) {
//					queue.add(new int[] {ay,ax});
//					visited[ay][ax] = true;
//					
//				}
//				
//			}
//		}
//		System.out.println(ans);
//		return ans;
//	}
	
	

}
