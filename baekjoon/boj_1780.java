import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1780 {
	static BufferedReader br;
	static StringTokenizer st;
	static int[][] map;
	static int one = 0;
	static int zero = 0;
	static int minus = 0;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < n ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());				
			}
		}
		recursive(0, 0, n);
		System.out.println(minus + "\n" + zero + "\n" + one);
		
	}
	
	public static void recursive(int y, int x, int n) { // 시작점, n범위
		if(allSame(y, x, n)) {
			if(map[y][x] == -1) { minus++; }
			if(map[y][x] == 0) { zero++; }
			if(map[y][x] == 1) { one++; }
			return;		
		}
		
		recursive(y, x, n/3);
		recursive(y, x + n/3, n/3);
		recursive(y + n/3, x, n/3);
		recursive(y + n/3, x + n/3, n/3);
		recursive(y, x + 2*n/3, n/3);
		recursive(y + 2*n/3, x, n/3);
		recursive(y + 2*n/3, x + 2*n/3, n/3);
		recursive(y + 2*n/3, x + n/3, n/3);
		recursive(y + n/3, x+2 * n/3, n/3);
		
	}
	
	public static boolean allSame(int y, int x, int n) {
		int ele = map[y][x];
		for(int i = y; i < y + n ; i++) {
			for(int j = x ; j < x + n ; j++) {
				if (ele != map[i][j]) return false; 
			}
		}
		return true;
	}
}

