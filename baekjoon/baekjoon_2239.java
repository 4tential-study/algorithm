import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main  {
	static int[][] board = new int[9][9];
	static boolean[][] isShowedRow = new boolean[9][10], isShowedCol = new boolean[9][10];
	static boolean[][][] isShowedBox = new boolean[3][3][10];
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int countZero=0;
		
		for(int i=0; i<9; i++) {
			String input = in.readLine();
			for(int j=0; j<9; j++) {
				board[i][j] = input.charAt(j) - '0';
				if(board[i][j]==0) countZero++;
				
				isShowedRow[i][board[i][j]] = true;
				isShowedCol[j][board[i][j]] = true;
				isShowedBox[i/3][j/3][board[i][j]] = true;
			}
		}
		
		solve(0,0, countZero);
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				sb.append(board[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static boolean solve(int sy, int sx, int countZero) {

		for(int i=sy; i<9; i++) {
			int init=0;
			if(i==sy) init=sx;
			for(int j = init; j<9; j++) {
				if(board[i][j]!=0) continue;
				for(int k=1; k<=9; k++) {
					if(!isShowedRow[i][k] && 
							!isShowedCol[j][k] && 
								!isShowedBox[i/3][j/3][k] ) {
						board[i][j] = k;
						isShowedRow[i][k] = true;
						isShowedCol[j][k] = true;
						isShowedBox[i/3][j/3][k] = true;
						
						if(solve(i,j+1, countZero-1)) 
							return true;
						board[i][j] = 0;
						isShowedRow[i][k] = false;
						isShowedCol[j][k] = false;
						isShowedBox[i/3][j/3][k] = false;
					}					
				}
				return false;
			}
		}
		return countZero==0;
		
	}	
}