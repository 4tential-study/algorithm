package week1002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_2112_보호필름 {
	
	static int D, W, K;
	static int result;
	static int[] board;
	static int[] injected;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for(int tc=1; tc<=T; tc++) {
			sb.append("#"+tc+" ");
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			result = Integer.MAX_VALUE;
			board = new int[D];
			injected = new int[D];
			
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=0; j<W; j++) {
					if(st.nextToken().charAt(0) == '1')
						board[i] = board[i] | 1 << j;
				}
			}
			
			injection(0, 0);
			
			sb.append(result+"\n");
		}
		
		System.out.println(sb.toString());
	}
	static void injection(int start, int injectCount) {
		if(result<injectCount) return;
		if(start==D) {
			int injectedBoard[] = Arrays.copyOf(board, board.length);
			
			for(int i=0; i<D; i++) {
				if(injected[i] == 1) {
					injectedBoard[i] &= 0;
				}
				else if(injected[i] == 2) {
					// 약품 B를 주입
					injectedBoard[i] |= (int)(Math.pow(2, W)-1);
				}
			}
			
			if(checkBoard(injectedBoard)) {
				result = Math.min(injectCount, result);
			}
			
			return;
		}
		
		injection(start+1, injectCount);
		injected[start] = 1;
		injection(start+1, injectCount+1);
		injected[start] = 2;
		injection(start+1, injectCount+1);
		injected[start] = 0;
	}
	static boolean checkBoard(int injectedBoard[]) {
		for(int j=0; j<W; j++) {
			int cnt = 0;
			boolean isA=true;
			for(int i=0; i<D; i++) {
				if((injectedBoard[i] & 1<<j) == 0) {
					if(isA) cnt++;
					else {
						isA = true;
						cnt = 1;
					}
				}else {
					if(!isA) cnt++;
					else {
						isA = false;
						cnt = 1;
					}
				}
				
				if(cnt>=K) break;
			}
			
			if(cnt<K) return false;
		}
		return true;
	}
}