import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_2583 {
	static BufferedReader br;
	static StringTokenizer st;
	static int n;
	static int m;
	static int[][] map;
	static boolean[][] visited;
	static int[][] deltas = {{-1,0}, {0,-1}, {1,0}, {0,1}};
	static ArrayList<Integer> answers = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		br= new BufferedReader(new InputStreamReader(System.in));
		String[] nmk = br.readLine().split(" ");
		m = Integer.parseInt(nmk[0]);
		n = Integer.parseInt(nmk[1]);
		map = new int[m][n];
		int k = Integer.parseInt(nmk[2]);
		visited = new boolean[m][n];
		for(int i=0 ; i < k ; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			for(int y = m-y2 ; y < m-y1 ; y++ ) {
				for(int x = x1 ; x < x2 ; x++) {
					map[y][x] = 1;
				}
			}
		}
		
		int cnt = 0;
		for(int i=0 ; i < m ; i++) {
			for(int j =0 ; j < n ; j++) {
				if(map[i][j] == 0 && !visited[i][j]) {
					answers.add(dfs(i,j));
					cnt++;					
				}
			}
		}
		
		
		Collections.sort(answers);
		System.out.println(cnt);
		for(Integer each : answers) {
			System.out.print(each + " ");
		}
		System.out.println();
		
		
		
	}
	
	public static int dfs(int y, int x) {
		Stack<int[]> stack = new Stack<>();
		stack.add(new int[] {y,x});
		visited[y][x] = true;
		int width = 1;
		while(!stack.isEmpty()) {
			int[] pop = stack.pop();
			for(int i=0 ; i < 4 ; i++) {
				int ay = pop[0]+deltas[i][0];
				int ax = pop[1]+deltas[i][1];
				if(ay >= 0 && ay < m && ax >= 0 && ax < n ) {
					if( map[ay][ax] == 0 && !visited[ay][ax]) {
						stack.add(new int[] {ay,ax});
						visited[ay][ax] = true;
						width++;
					}
				}
			}
		}
		return width;
	}
}


