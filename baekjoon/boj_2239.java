import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2239 {
	static BufferedReader br;
	static StringTokenizer st;
	static int[][] board;
	static boolean already = false; //flag역할
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader (new InputStreamReader(System.in));
		board = new int[9][9];
		for(int i=0 ;i < 9 ; i++) {
			String str = br.readLine();
			for(int j = 0 ; j < 9 ;j ++) {
				board[i][j] = str.charAt(j)-'0';
			}
		}
		
		int[] start = findNextBlank();
		if( start[0] == 10 && start[1] == 10) {			
			return;
		}
		for(int i=1 ; i <= 9 ; i++) {
			backtrack(start[0], start[1], i);
		}
		
		
	}
	
	public static void backtrack(int y, int x, int num) {
		// 적합하지 않은 숫자일 경우, 재귀를 멈춘다.
		if(!checkCol(x, num) || !checkRow(y, num) || !checkSquare(y, x, num)) {
			return;
		}
		
		board[y][x] = num;
		int[] target = findNextBlank();		
		if( target[0] == 10 && target[1] == 10) {
			if ( !already ){
				print();
				already = true;
			}
			return;
		}
						
		for(int i=1 ; i <= 9 ; i++) {
			backtrack(target[0],target[1],i);
		}
		board[y][x] = 0;	
	}
	
	private static boolean checkCol(int x, int num) {
		for(int i=0 ; i < 9 ; i++) {
			if( board[i][x] == num ) return false;
		}
		return true;
	}
	
	private static boolean checkRow(int y, int num) {
		for(int i=0 ; i < 9 ; i++) {
			if( board[y][i] == num ) return false;
		}
		return true;
	}
	
	private static boolean checkSquare(int y, int x, int num) {
		int startx = x/3 * 3;
		int starty = y/3 * 3;
		for(int i=starty ; i < starty+3 ; i++) {
			for(int j = startx ; j < startx+3 ; j++) {
				if(board[i][j] == num) return false;
			}
		}
		return true;
	}
	
	private static int[] findNextBlank() {
		for(int i=0 ; i < 9 ; i++) {
			for(int j=0 ; j < 9 ; j++) {
				if(board[i][j] == 0) {
					return new int[] {i,j};
				}
			}
		}
		return new int[] {10,10};
	}
	
	private static void print() {
		for(int i=0 ; i < 9 ; i++) {
			for(int j=0 ; j < 9 ; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
}
