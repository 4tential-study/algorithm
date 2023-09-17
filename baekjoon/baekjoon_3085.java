import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, result;
	static char[][] map;
	// 우, 하
	static int[] dy = { 0, 1 };
	static int[] dx = { 1, 0 };
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		map = new char[N][];
		
		for(int i=0; i<N; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int td = 0;
				int ty = i + dy[td];
				int tx = j + dx[td];

				if(canGo(ty, tx)) {
					swap(i,j, ty,tx);
					
					result = Math.max(result, checkSame(0, i, 0));
					result = Math.max(result, checkSame(1, 0, j));
					result = Math.max(result, checkSame(1, 0, j+1));
					
					swap(i,j, ty,tx);
				}
				
				
				td = 1;
				ty = i + dy[td];
				tx = j + dx[td];

				if(canGo(ty, tx)) {
					swap(i,j, ty,tx);
					
					result = Math.max(result, checkSame(0, i, 0));
					result = Math.max(result, checkSame(1, 0, j));
					result = Math.max(result, checkSame(0, i+1, 0));
					
					swap(i,j, ty,tx);
				}
			}
		}
		
		System.out.println(result);
	}
	static boolean isCorner(int d, int s) {
		
		switch(d) {
		case 0:
		case 2: if(s==1 || s==3) return true; break;
		case 1: 
		case 3: if(s==0 || s==2) return true; break;
		}
		
		return false;
	}
	static boolean canGo(int y, int x) {
		return 0<=y && y<N && 0<=x && x<N;
	}
	static int checkSame(int d, int y, int x) {
		int lineCnt = 1;
		int localCnt = lineCnt;
		
		int ny = y + dy[d];
		int nx = x + dx[d];
		
		while(canGo(ny,nx)) {
			if(map[y][x] == map[ny][nx]) {
				localCnt++;
			}else {
				lineCnt = Math.max(localCnt, lineCnt);
				localCnt = 1;
			}
			
			y = ny;
			x = nx;
			ny = y + dy[d];
			nx = x + dx[d];
		}
		
		lineCnt = Math.max(localCnt, lineCnt);
		
		return lineCnt;
	}
	static void swap(int y, int x, int ty, int tx) {
		char temp = map[y][x];
		map[y][x] = map[ty][tx];
		map[ty][tx] = temp;
	}
}