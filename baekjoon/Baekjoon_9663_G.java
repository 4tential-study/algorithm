package Algorithm.boj.day0730;

import java.util.Scanner;

public class Baekjoon_9663_G {
	
	static int N;
	static int result=0;
	static int[] board;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		board = new int[N];
		sc.close();
		
		N_QUEEN(0);
		
		System.out.println(result);
	}
	
	static void N_QUEEN(int y) {
		if(y==N) {
			result++;
			return;
		}
		
		for(int i=0; i<N; i++) {
			board[y] = i;
			if(possible(y)){
				N_QUEEN(y+1);
			}
		}
		
	}
	
	static boolean possible(int row) {
		for(int i=0; i<row; i++) {
			if( board[i]==board[row] || (row-i) == Math.abs(board[row]-board[i]) ) {
				return false;
			}
		}
		return true;
	}
}
