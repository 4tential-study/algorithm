package Algorithm.boj.day0921;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon_16236_아기상어 {
	
	static class SearchNode{
		int y;
		int x;
		int searchStage;
		public SearchNode(int y, int x, int searchStage) {
			super();
			this.y = y;
			this.x = x;
			this.searchStage = searchStage;
		}
		
		
	}
	
	static int N, map[][] = new int[20][20];
	static int sharkY, sharkX, sharkSize = 2;

	// 하 상 우 좌
	// 하 -> 상, 우 -> 좌 를 위함
	static int dy[] = {1, -1, 0, 0};
	static int dx[] = {0, 0, 1, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		int fishCount = 0;
		int passTime = 0;
		int eatCount = 0;
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]>0 && map[i][j]!=9) fishCount++;
				else if(map[i][j] == 9) {
					sharkY = i;
					sharkX = j;
				}
			}
		}
		
		for(int i=0; i<fishCount; i++) {
			int result = searchFish(sharkY, sharkX);
			if(result == Integer.MAX_VALUE) break;
			passTime += result;
			if(++eatCount==sharkSize) {
				sharkSize++;
				eatCount = 0;
			}
		}
		
		System.out.println(passTime);
	}
	
	static int searchFish(int y, int x) {
		int minSearchStage = Integer.MAX_VALUE;
		int fishY = -1;
		int fishX = -1;

		boolean[][] visited = new boolean[20][20];
		Queue<SearchNode> q = new ArrayDeque<>();
		q.offer(new SearchNode(y, x, 0));
		visited[y][x] = true;
		
		while(!q.isEmpty()) {
			SearchNode node = q.poll();
			
			for(int d=0; d<dy.length; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
					
				if(canGo(ny, nx, visited) && map[ny][nx] <= sharkSize) {
					if(map[ny][nx] < sharkSize && minSearchStage >= node.searchStage+1 && map[ny][nx]!=0) {
						if(minSearchStage != node.searchStage+1
								|| fishY > ny
								|| (fishY == ny && fishX > nx)) {							
							minSearchStage = node.searchStage+1;
							fishY = ny;
							fishX = nx;
						}
					}				
					if(minSearchStage==Integer.MAX_VALUE) {
						q.offer(new SearchNode(ny, nx, node.searchStage+1));
						visited[ny][nx] = true;
					}
				}
			}
		}
		
		if(minSearchStage!=Integer.MAX_VALUE) {
			map[y][x] = 0;
			map[fishY][fishX] = 0;
			sharkY = fishY;
			sharkX = fishX;
		}
		
		return minSearchStage;
	}
	static boolean canGo(int y, int x, boolean[][] visited) {
		return 0<=y && y<N && 0<=x && x<N && !visited[y][x];
	}
}