package Algorithm.boj.day0815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon_2573 {
	
	static int N, M;
	static byte[][] board, nextBoard;
	static List<int[]> icePos;
	static int[][] delta = {{-1,0},{0,1},{1,0},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		
		/*
		 * board에 각 값을 저장 ( 단, 메모리 제한 때문에 byte 배열 사용 )
		 * 각 빙산의 좌표를 어레이리스트에 저장
		 * bfs로 각 좌표를 순회하며 현재 좌표의 높이값 감소시킴
		 * 높이값이 0이 되었다면 현재 좌표를 어레이리스트에서 remove
		 * 모든 좌표를 돌았다면 bfs로 빙산 너비 확인
		 * 한 덩어리의 너비가 어레이리스트의 size보다 부족하다면 다른 곳에 덩어리가 있으므로
		 * 결과 출력
		 */
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nextBoard = new byte[N][M];
		board = new byte[N][M];
		icePos = new ArrayList<int[]>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<M; j++) {
				nextBoard[i][j] = Byte.parseByte(st.nextToken());
				board[i][j] = nextBoard[i][j]; 
				if(nextBoard[i][j] != 0) {
					icePos.add(new int[] {i, j});
				}
			}
		}
		
		int pastYears=0;
		// 빙산이 한 덩어리인지 체크
		do {
			// 각 빙산을 순회하며 높이값 감소
			for(int ice=0; ice<icePos.size(); ice++) {
				int cy = icePos.get(ice)[0];
				int cx = icePos.get(ice)[1];
				
				byte seaNearCount = 0;
				for(int d=0; d<delta.length; d++) {
					int ny = cy + delta[d][0];
					int nx = cx + delta[d][1];
					
					if(isInner(ny, nx) && board[ny][nx]==0) seaNearCount++;
				}
				nextBoard[cy][cx] = (byte)Math.max(0, board[cy][cx]-seaNearCount);
				if(nextBoard[cy][cx]==0) {
					icePos.remove(ice);
					ice--;
				}
			}
			
			if(icePos.size()==0) {
				pastYears=0;
				break;
			}
			
			pastYears++;
			board = nextBoard;
			nextBoard = new byte[N][M];
		}while(isOnePiece());
		
		System.out.println(pastYears);
	}
	
	static boolean isOnePiece() {
		int visitCount=1;
		boolean[][] visit = new boolean[N][M];
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(icePos.get(0));
		visit[icePos.get(0)[0]][icePos.get(0)[1]] = true;
		
		while(!q.isEmpty()) {
			int cy = q.peek()[0];
			int cx = q.poll()[1];
			for(int d=0; d<delta.length; d++) {
				int ny = cy + delta[d][0];
				int nx = cx + delta[d][1];
				if(isInner(ny,nx) && board[ny][nx] != 0 && !visit[ny][nx]) {
					q.offer(new int[] {ny, nx});
					visit[ny][nx]=true;
					visitCount++;
				}
			}
		}
		if(visitCount < icePos.size()) return false;
		return true;
		
	}
	
	static boolean isInner(int y, int x) {
		return ( 0<=y && y<N && 0<=x && x<M );
	}
}
