import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		char[][] board;
		// 1 ~ 8 
		int[][] delta = { {-1,0}, {-1,1}, {0,1}, {1,1},{1,0},{1,-1}, {0,-1}, {-1,-1} };
		
		int w, h;
		
		while(true) {
			st = new StringTokenizer(in.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if(w==0 && h==0) break;
			
			int result=0;
			board = new char[h][w];
			Stack<int[]> stack = new Stack<>();
			
			for(int i=0; i<h; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=0; j<w; j++) {
					board[i][j] = st.nextToken().charAt(0);
				}
			}
			
			
			for(int i=0; i<h; i++) {				
				for(int j=0; j<w; j++) {					
					if(board[i][j]=='1') {
						stack.push(new int[]{i,j});
						result++;
						
						while(!stack.isEmpty()) {
							int[] curPos = stack.pop();
							board[curPos[0]][curPos[1]] = '2';
							
							for(int d=0; d<delta.length; d++) {
								int nxY = curPos[0] + delta[d][0];
								int nxX = curPos[1] + delta[d][1];
								
								if(0<=nxY && nxY<h
									&& 0<=nxX && nxX<w) {
									if(board[nxY][nxX]=='1') {
										stack.push(new int[] {nxY, nxX});
									}
								}
							}
						}				
					}					
				}
			}
			
			sb.append(result+"\n");
			
		}
		
		System.out.println(sb);
		
	}
}