package week1016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon_25208 {
	static class Node{
		int y;
		int x;
		int step;
		// 감옥의 뚫린 면이 위치한 곳
		// 0번부터 5번까지 "하 상 전 우 후 좌"
		int passing;
		public Node(int y, int x, int step, int passing) {
			super();
			this.y = y;
			this.x = x;
			this.step = step;
			this.passing = passing;
		}
		@Override
		public String toString() {
			return "Node [y=" + y + ", x=" + x + ", step=" + step + ", passing=" + passing + "]";
		}
		
		
	}
	
	static int N, M;
	static char[][] map;
	static boolean[][][] visited;
	static int detY, detX;
	static int robY, robX;
	static int result=Integer.MAX_VALUE;
	
	// 상 하 좌 우
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int dh[][] = { {5, 4, 2, 3, 0, 1}, {4, 5, 2, 3, 1, 0},
			{3, 2, 0, 1, 4, 5}, {2, 3, 1, 0, 4, 5} };
	

	static final char WALL = '#', FLOOR='.', DET='D', ROB='R';
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][6];
		
		for(int i=0; i<N; i++) {
			String line = in.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == DET) {
					detY = i;
					detX = j;
				}
				else if(map[i][j] == ROB) {
					robY = i;
					robX = j;
				}
			}
		}
		
		bfs();
		
		if(result==Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(result);
		
	}
	static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		
		q.offer(new Node(detY, detX, 0, 0));
		visited[detY][detX][0] = true;
		
		while(!q.isEmpty()) {
			Node c = q.poll();
			
			if(c.y==robY && c.x==robX) {
				if(c.passing==0) {
					result = c.step;
					return;
				}
				continue;
			}
			
			for(int d=0; d<dy.length; d++) {
				int ny = c.y + dy[d];
				int nx = c.x + dx[d];
				int nPassing = dh[d][c.passing];
				
				if(canGo(ny, nx, nPassing)) {
					q.offer(new Node(ny, nx, c.step+1, nPassing));
					visited[ny][nx][nPassing] = true;
				}
			}
		}
		
	}
	
	static boolean inRange(int y, int x) {
		return 0<=y && y<N && 0<=x && x<M;
	}
	static boolean canGo(int y, int x, int passing) {
		return inRange(y, x) && !visited[y][x][passing] && map[y][x]!=WALL;
	}
	static int nextPassing(int passing, int dir) {
		
		
		
		return 0;
	}
}
