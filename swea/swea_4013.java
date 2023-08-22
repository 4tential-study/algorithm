package unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_4013 {
	static BufferedReader br;
	static StringTokenizer st;
	static int t;
	static int k;
	static int[][] map;
	static int score;
	static boolean[] visited;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		for(int testcase = 1 ;  testcase <= t ; testcase++) {
			score = 0;
			k = Integer.parseInt(br.readLine());
			sb.append("#"+ testcase+ " ");
			map = new int[4][8];
		
			for(int i=0 ; i < 4 ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j =0 ; j < 8 ;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0 ; i < k ; i++) {
				String[] s = br.readLine().split(" ");
				int num = Integer.parseInt(s[0])-1;
				int dir = Integer.parseInt(s[1]); 
				visited = new boolean[4];
				circle(num, dir);	
			}

			calcul();
			sb.append(score+"\n");
		}
		System.out.println(sb.toString());
	}
	
	private static void circle(int y, int dir) {
		visited[y] = true;
		//탈출부분
		if (y >= 4 || y < 0) return;
		//실행부분
		int[] line = Arrays.copyOf(map[y], map[y].length);
					
		if( y+1 <= 3) {
			if(map[y][2] != map[y+1][6] && !visited[y+1]) {
				circle(y+1, dir*-1);
			}
		}
		if (y-1 >= 0) {
			if( map[y][6] != map[y-1][2] && !visited[y-1]) {
				circle(y-1, dir*-1);
			}
		}
		
		if (dir == 1) { //시계방향 + 1
			//시계 회전 실행
			for(int i=0 ; i < 8 ; i++) {
				if(i+1 >= 8) map[y][i+1-8] = line[i];
				else map[y][i+1] = line[i];
			}			
		} else { //반시계방향 - 1
			//빈시계 회전 실행
			for(int i = 0 ; i < 8 ; i++) {
				if(i-1 < 0) map[y][i-1+8] = line[i];
				else map[y][i-1] = line[i];
			}
		}
	}
	
	private static void calcul() {
		for(int i=0 ; i < 4 ; i++) {
			if(map[i][0] == 1) {
				score += Math.pow(2, i);
			}
		}
	}
}
