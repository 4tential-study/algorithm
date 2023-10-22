package week1016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon_16234 {
	static class Pos{
		int y;
		int x;
		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		@Override
		public String toString() {
			return "Pos [y=" + y + ", x=" + x + "]";
		}
		
		
	}
	
	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static int day;
	
	// 상 우 하 좌
	static int dy[] = {-1, 0, 1, 0};
	static int dx[] = {0, 1, 0, -1};
	
	static List<Pos> unitePopulationList;
	
	public static void main(String[] args) throws IOException {
		
		init();
		
		int uniteNumber = 0;
		 
		boolean isUniting = true;
		while(isUniting) {
			visited = new boolean[N][N];
			isUniting = false;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j]) {
						int num = bfs(i, j);
						if(unitePopulationList.size() > 1) {
							distribute(num);
							isUniting = true;
						}
					}
				}
			}
			if(!isUniting) break;
			day++;
		}
		
		System.out.println(day);
	}
	static void init() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	static boolean inRange(int y, int x) {
		return 0<=y && y<N && 0<=x && x<N;
	}
	static boolean canUnite(int a, int b) {
		int diff = Math.abs(a-b);
		return L<=diff && diff<=R;
	}
	static int bfs(int y, int x) {
		Queue<Pos> q = new ArrayDeque<>();
		unitePopulationList = new ArrayList<>();
		
		int populationSum = map[y][x];
		q.offer(new Pos(y, x));
		unitePopulationList.add(new Pos(y,x));
		visited[y][x] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			int cy = cur.y;
			int cx = cur.x;
			
			for(int d=0; d<dy.length; d++) {
				int ny = cy+dy[d];
				int nx = cx+dx[d];
				if(inRange(ny, nx) && canUnite(map[cy][cx], map[ny][nx]) && !visited[ny][nx]) {
					visited[ny][nx] = true;
					q.offer(new Pos(ny, nx));
					unitePopulationList.add(new Pos(ny, nx));
					populationSum += map[ny][nx];
				}
			}
		}
		
		return populationSum;
	}
	private static void distribute(int num) {
		int avg = num / unitePopulationList.size();
		for(Pos cur : unitePopulationList) {
			map[cur.y][cur.x] = avg;
		}
	}
}