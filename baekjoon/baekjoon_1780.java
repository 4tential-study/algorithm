import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int a, b, c; 	// 각 종이의 개수
	static int N;
	static int[][] board;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		board = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve(0,0,N);
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		
	}
	
	static void solve(int sy, int sx, int size) {
		
		int first = board[sy][sx];
		boolean flag = true;
		for(int i=sy; i<sy+size && flag; i++) {
			for(int j=sx; j<sx+size && flag; j++) {
				if(board[i][j]!=first) {
					flag = false;
					break;
				}
			}
		}
		
		if(flag) {
			switch(board[sy][sx]) {
			case -1: a++; return;
			case 0: b++; return;
			case 1: c++; return;
			}
		}else {
			int nextSize = size/3;
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					solve(sy+nextSize*i, sx+nextSize*j, nextSize);
				}
			}
		}
		
	}
	
}