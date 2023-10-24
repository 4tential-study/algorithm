import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_25208 {
	static BufferedReader in;
	static StringTokenizer st;
	
	static int n;
	static int m;
	static char[][] map;
	static int[] start = new int[2];
	static boolean[][] visited;
	static int[][] delta = {{-1,0},{0,-1},{1,0},{0,1}};//상좌하우
	public static void main(String[] args) throws IOException{
		in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] ins = in.readLine().split(" ");
		n = Integer.parseInt(ins[0]);
		m = Integer.parseInt(ins[1]);
		map = new char[n][m];
		visited = new boolean[n][m];
		for(int i=0 ; i < n ; i++) {
			String str = in.readLine();
			for(int j=0 ; j < m ; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'D') {
					start = new int[] {i,j};
				}
			}
		}
		
		System.out.println(bfs(start[0], start[1]));
		
	}
	
	public static int bfs(int y, int x) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {y,x,0,0,0});
		visited[y][x] = true;
		while(!q.isEmpty()) {
			int[] poll = q.poll();
			int cur = poll[2];//감옥현황-지금
			int dist = poll[3]+1;
			int prevDir = poll[4];//이전 오는 방향
			if(map[poll[0]][poll[1]]=='R' && cur != 0) {
				visited = new boolean[n][m];
				continue;
			}
			if(map[poll[0]][poll[1]] == 'R' && cur == 0) {
				return poll[3];
			}
			for(int f = 0 ;f < 4 ;f++) {
				int ay = poll[0] + delta[f][0];
				int ax = poll[1] + delta[f][1];
				int dir = f;
				if(inRange(ay,ax) && !visited[ay][ax] && map[ay][ax] != '#') {
					visited[ay][ax] = true;
					if(cur == 0) {//0인경우 어디로 가든 1
						if( map[ay][ax] != 'R') q.offer(new int[] {ay,ax,1,dist,f});
					}else if(cur == 1) {
						if(prevDir == 0) { //상
							if(dir == 1 || dir == 3) {
								if( map[ay][ax] != 'R') q.offer(new int[] {ay,ax,1,dist,f});
							}else if(dir == 0) {
								if( map[ay][ax] != 'R') q.offer(new int[] {ay,ax,2,dist,f});
							}else q.offer(new int[] {ay,ax,0,dist,f});
						}else if(prevDir == 1) { //좌
							if(dir == 0 || dir == 2) {
								if( map[ay][ax] != 'R') q.offer(new int[] {ay,ax,1,dist,f});
							}else if(dir == 3) {
								if( map[ay][ax] != 'R') q.offer(new int[] {ay,ax,2,dist,f});
							}else q.offer(new int[] {ay,ax,0,dist,f});
						}else if(prevDir == 2) {//하
							if(dir == 1 || dir == 3) {
								if( map[ay][ax] != 'R') q.offer(new int[] {ay,ax,1,dist,f});
							}else if(dir == 2) { //하
								if( map[ay][ax] != 'R') q.offer(new int[] {ay,ax,2,dist,f});
							}else q.offer(new int[] {ay,ax,0,dist,f});
						}else { // prevDir == 3
							if(dir == 0 || dir == 2) {
								if( map[ay][ax] != 'R') q.offer(new int[] {ay,ax,1,dist,f});
							}else if(dir == 3) {
								if( map[ay][ax] != 'R') q.offer(new int[] {ay,ax,2,dist,f});
							}else q.offer(new int[] {ay,ax,0,dist,f});
						}
					}else if(cur == 2) { //2인 경우 어디로 가든 1
						if( map[ay][ax] != 'R') q.offer(new int[] {ay,ax,1,dist,f});
					}
				}
			}
		}
		return -1;
	}
	
	public static boolean inRange(int y, int x) {
		return y >= 0 && x >= 0 && y < n && x < m;
	}
	
//	if(f == 3) {//우으로 갈때는 2이다.
//		q.offer(new int[] {ay, ax, 2, dist});
//	}else if(f == 0) {
//		
//  }else if( prev && f == 2) {//상,하로 갈때는 1이고
//		q.offer(new int[] {ay, ax, 1, dist});
//	}else if(f == 1 && )q.offer(new int[] {ay, ax, 0, dist});//좌로 갈때는 0이다.
}
