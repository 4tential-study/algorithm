package week1023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class baekjoon_11559_PuyoPuyo {
	
	static int N = 12, M = 6;
	static char[][] map = new char[N][M];
	static boolean[][] visited, isExploded;
	
	// 상하좌우
	static int dy[] = {-1, 1, 0, 0};
	static int dx[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		init();
		
		System.out.println(solve());
		
	}
	
	static void init() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=N-1; i>=0; i--) {
			String input = in.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
			}
		}
	}
	
	static int solve() {
		int result = 0;
		
		while(true) {
			visited = new boolean[N][M];
			isExploded = new boolean[N][M];
			
			boolean explodeFlag = false;
			// 블록 연쇄 폭발 조건 탐색
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(!visited[i][j] && map[i][j]!='.') {
						if(explode(i, j)) explodeFlag = true;
					}
				}
			}
			
			// 블록들 떨어뜨리기
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(isExploded[i][j]) {
						getDown(i, j);
					}
				}
			}
			
			if(!explodeFlag) break; 
			
			result++;
		}
		
		return result;
	}
	
	static boolean explode(int y, int x) {
		
		Queue<int[]> q = new ArrayDeque<>();
		List<int[]> list = new ArrayList<>();
		
		q.offer(new int[] {y, x});
		visited[y][x] = true;
		list.add(new int[] {y,x});
		
		int count = 1;
		
		while(!q.isEmpty()) {
			int cy = q.peek()[0];
			int cx = q.poll()[1];
			
			for(int d=0; d<dy.length; d++) {
				int ny = cy+dy[d];
				int nx = cx+dx[d];
				
				if(inRange(ny, nx) && // 맵 내부인지 확인
						!visited[ny][nx] && // 이미 체크했던 곳인지 확인
						map[y][x]==map[ny][nx]) {	// 초기 위치와 같은 색인지 확인
					q.offer(new int[] {ny,nx});
					visited[ny][nx] = true;
					list.add(new int[] {ny,nx});
					count++;
				}
			}
		}
		
		if(count>=4) {
			for(int i=0; i<list.size(); i++) {
				int cy = list.get(i)[0];
				int cx = list.get(i)[1];
				
				isExploded[cy][cx] = true;
			}
			
			return true;
		}
		return false;
	}
	
	static void getDown(int y, int x) {
		Queue<Character> q = new ArrayDeque<>();
		
		int firstY = -1;
		
		for(int i=y+1; i<N; i++) {
			if(visited[i][x] && !isExploded[i][x]) {
				q.offer(map[i][x]);
			}
		}
		
		int qSize = q.size();
		for(int i=y; i<N; i++) {
			if(i-y<qSize) {
				map[i][x] = q.poll();
			}else {
				map[i][x] = '.';
			}
			isExploded[i][x] = false;
		}
	}
	
	static boolean inRange(int y, int x) {
		return 0<=y && y<N && 0<=x && x<M;
	}
	
}
