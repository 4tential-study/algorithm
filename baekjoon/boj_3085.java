import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class boj_3085 {
	static BufferedReader br;
	static StringTokenizer st;
	static char[][] board;
	static int[] ay = {-1,0,1,0};
	static int[] ax = {0,-1,0,1};
	
	public static void main(String[] args) throws IOException{
		int n = Integer.parseInt(br.readLine());
		board = new char[n][n];
		
		for(int i=0 ; i < n ; i++) {
			String st = br.readLine();
			for(int j=0 ; j < n ;j++) {
				board[i][j] = st.charAt(j);
			}
		}
		
		
		
	
	//dfs로 돌면서 다른알파벳을 만나면
	//swap하고 최장부분길이수열의 길이를 구하기
	//해당 길이가 최대인 경우, 갱신
	}
}
