package Algorithm.boj.day0815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon_2583 {
	
	static boolean[][] board;
	// 상 우 하 좌
	static int[][] delta = { {-1,0}, {0,1}, {1,0}, {0,-1} };
	static List<Integer> result = new ArrayList<Integer>();
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		board = new boolean[M][N];
		
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(in.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			for(int i=sy; i<ey; i++) {
				for(int j=sx; j<ex; j++) {
					board[i][j] = true;
				}
			}
		}
		
		
		bfs(M,N,K);
		
		result.sort(Comparator.naturalOrder());
		
		sb.append(result.size()+"\n");
		for(int size : result) {
			sb.append(size+" ");
		}
		System.out.println(sb.toString());
		
	}
	
	static void bfs(int M, int N, int K) {
		
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(!board[i][j]) {
					
					int sizeCount = 1;
					Queue<int[]> q = new ArrayDeque<int[]>();
					q.offer(new int[] {i,j});
					board[i][j] = true;
					
					while(!q.isEmpty()) {
						int cy = q.peek()[0];
						int cx = q.poll()[1];
						
						for(int d=0; d<delta.length; d++) {
							int ny = cy + delta[d][0];
							int nx = cx + delta[d][1];
							if(canGo(ny, nx, M, N)) {
								q.offer(new int[] {ny,nx});
								board[ny][nx] = true;
								sizeCount++;
							}
						}
					}
					result.add(sizeCount);					
				}
			}
		}
	}
	
	static boolean canGo(int y, int x, int M, int N) {
		return (0<=y && y<M && 0<=x && x<N && !board[y][x]);
	}
}
