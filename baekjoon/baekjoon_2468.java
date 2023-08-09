import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		int min = 100;
		int max = 0;
		int[][] board = new int[N][N];
		// 상 우 하 좌
		int[] dy = {-1, 0, 1, 0};
		int[] dx = {0, 1, 0, -1};
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				min = Math.min(board[i][j], min);
				max = Math.max(board[i][j], max);
			}
		}
		
		
		int resultMax = 1;
		for(int flooded=min; flooded<max; flooded++) {
			int safezoneCount=0;
			boolean[][] visited = new boolean[N][N];
			
			for(int y=0; y<N; y++) {
				for(int x=0; x<N; x++) {
					
					if(board[y][x]>flooded && !visited[y][x]) {
						safezoneCount++;
						
						Queue<int[]> q = new ArrayDeque<int[]>();
						q.offer(new int[] {y,x});
						visited[y][x] = true;
						while(!q.isEmpty()) {
							int[] curPos = q.poll();
//							if(visited[curPos[0]][curPos[1]]) continue;
//							visited[curPos[0]][curPos[1]] = true;
							
							for(int d=0; d<dy.length; d++) {
								int[] nextPos = {curPos[0] + dy[d], curPos[1] + dx[d]};
								
//								if(isInner(nextPos[0], nextPos[1]) 
								if(0<=nextPos[0] && nextPos[0]<N && 0<=nextPos[1] && nextPos[1]<N
									&& board[nextPos[0]][nextPos[1]]>flooded
									&& !visited[nextPos[0]][nextPos[1]]) {
									
									q.offer(nextPos);
									visited[nextPos[0]][nextPos[1]] = true;
									
								}
							}
						}
					}
				}
			}			
			// 1차 for문 하단
			resultMax = Math.max(resultMax, safezoneCount);
		}		
		// main 하단
		System.out.println(resultMax);
	}
	static boolean isInner(int y, int x) {
		if(0<=y && y<N
			&& 0<=x && x<N) {
			return true;
		}
		
		return false;
	}
}