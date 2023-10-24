import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_11559 {
	static BufferedReader in;
	static StringTokenizer st;
	static char[][] map = new char[12][6];
	static boolean[][] visited = new boolean[12][6];
	static int[][] delta = {{-1,0},{0,-1},{1,0},{0,1}};
	
	public static void main(String[] args) throws IOException{
		in = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0 ; i < 12 ; i++) {
			String str = in.readLine();
			for(int j=0 ; j < 6 ; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		boolean isPop = true;
		int ans = 0;
		while(isPop) {
			isPop = false;
			visited = new boolean[12][6];
			for(int i=0 ; i < 12 ; i++) {
				for(int j=0 ; j < 6 ; j++) {
					if(map[i][j] !='.' && !visited[i][j]) {	
						int pop = bfs(i,j, map[i][j]);
						if(pop >= 4) {
							pop();
							isPop = true;
						} else visited = new boolean[12][6];
					}
				}
			}
			if(isPop) ans++;
			arrange();
		}
		
		System.out.println(ans);
		
		
		
	}
	
	public static int bfs(int y, int x, char color) {
		Queue<int[]> q= new ArrayDeque<>();
		q.offer(new int[] {y,x});
		visited[y][x] = true;
		int cnt = 1;
		while(!q.isEmpty()) {
			int[] poll = q.poll();
			for(int f=0 ; f < 4; f++) {
				int ay = poll[0] + delta[f][0];
				int ax= poll[1] + delta[f][1];
				if(inRange(ay,ax) && !visited[ay][ax]) {
					if(map[ay][ax] == color) {
						cnt++;	
						visited[ay][ax] = true;
						q.offer(new int[] {ay,ax});
					}
				}
			}
		}
		return cnt;
	}
	
	public static boolean inRange(int y, int x) {
		return y >=0 && x>=0 && y<12 && x<6;
	}
	
	public static void arrange() {
		int cnt =0;
		for(int j=0 ; j < 6 ; j++) {
			cnt = 0;
			for(int i=11 ; i >= 0 ; i--) {
				if(map[i][j] == '.') {
					cnt++; //2
				}else {
					char temp = map[i][j];
					map[i][j] = map[i+cnt][j];
					map[i+cnt][j] = temp;
				}
			}
		}
	}
	
	public static void pop() {
		for(int i=0 ; i< 12 ; i++) {
			for(int j=0 ; j < 6; j++) {
				if(map[i][j] != '.' && visited[i][j]) {
					map[i][j]= '.';
				}
			}
		}
	}
	

}