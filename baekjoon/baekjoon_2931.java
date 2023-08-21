package Algorithm.boj.day0821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_2931 {
	
	// 그래프 탐색 시 쉬운 조회를 위한 static 선언
	static int R, C;
	static int[] start, end;
	// 사방 탐색을 위한 델타값
	// 상 우 하 좌
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	// 그래프 탐색을 위한 맵
	static boolean[][] visit; 
	static char[][] map;
	// 결과 출력을 위한 좌표값과 블록
	static int resultY, resultX;
	static char resultBlock;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
			
			String[] str = in.readLine().split(" ");
			R = Integer.parseInt(str[0]);
			C = Integer.parseInt(str[1]);
			
			start = new int[2];
			end = new int[2];
			
			map = new char[R][C];
			visit = new boolean[R][C];
			
			for(int r=0; r<R; r++) {
				String str2 = in.readLine();
				for(int c=0; c<C; c++) {
					map[r][c] = str2.charAt(c);
					
					if(map[r][c]=='M') {
						start[0] = r;
						start[1] = c;

						visit[r][c] = true;
					}
				}
			}
			
			// 맵에서의 현재 지점 초기화
			int[] temp = search(start[0], start[1], 0);
			int cy = temp[0];
			int cx = temp[1];
			int cd = temp[2];
			
			while(map[cy][cx]!='.') {
				// +라면 두번 방문해야함
				// 한번 방문했으면 그 자리에 |나 -를 설치하는 것으로 구현
				if(map[cy][cx]!='+') visit[cy][cx] = true;
				
				
				// 현재 탐색 위치에 따른 다음 탐색 위치 지정
				switch(map[cy][cx]) {
				case '+':
					if(cd==0 || cd==2) map[cy][cx] = '-';
					else if(cd==1 || cd==3) map[cy][cx] = '|';
				case '|':
				case '-':
					// 진행 방향 유지
					break;
				case '1':
					// 현재 진행방향이 좌향이라면
					if(cd==3) {
						// 아래로 전진
						cd = 2;
						break;
					}
					// 현재 진행방향이 상향이라면
					if(cd==0) {
						// 오른쪽으로 전진
						cd = 1;
						break;
					}
				case '2':
					// 진행 : 하향
					if(cd==2) {
						// 오른쪽으로 전진
						cd = 1;
						break;
					}
					// 진행 : 좌향
					if(cd==3) {
						// 위로 전진
						cd = 0;
						break;
					}
				case '3':
					// 진행 : 하향
					if(cd==2) {
						// 왼쪽으로 전진
						cd = 3;
						break;
					}
					// 진행 : 우향
					if(cd==1) {
						// 위로 전진
						cd = 0;
						break;
					}
				case '4':
					// 진행 : 상향
					if(cd==0) {
						// 왼쪽으로 전진
						cd = 3;
						break;
					}
					// 진행 : 우향
					if(cd==1) {
						// 아래로 전진
						cd = 2;
						break;
					}
				}
				cy += dy[cd];
				cx += dx[cd];
			}
			
			// 만약 현재 탐색하고 있는 지점이 빈칸이라면
			// 다음 길을 찾지 못했다는 것이며 길이 끊겨 있다는 것
			// 그렇다면 현재 진행 방향과 현재 탐색 위치의 사방에 위치한 블록에 따라
			// 이곳에 위치해야할 블록이 무엇인지를 알 수 있다
			
			int blockCnt = 0; // 해당 탐색 지점 주변에 블록이 몇개 위치해있는지 세기
			int ld = 0;		// 마지막 탐색 시의 방향
			for(int d=0; d<dy.length; d++) {
				int ny = cy + dy[d];
				int nx = cx + dx[d];
				// 탐색 좌표가 맵 내부이며 지나온 길이 아니고 공백이 아니라면
				if(0<=ny && ny<R && 0<=nx && nx<C && !visit[ny][nx] && map[ny][nx] != '.' && map[ny][nx] != 'Z') {
					char c = map[ny][nx];
					if(
						   (d==0 && (c=='|' || c=='+' || c=='1' || c=='4'))
						|| (d==1 && (c=='-' || c=='+' || c=='3' || c=='4'))
						|| (d==2 && (c=='|' || c=='+' || c=='2' || c=='3'))
						|| (d==3 && (c=='-' || c=='+' || c=='1' || c=='2'))
							) {
						blockCnt++;
						ld = d;
					}
				}
			}
			
			// 결과 입력
			resultY = cy;
			resultX = cx;
			if(blockCnt==3) resultBlock = '+';	// 빈칸 주변의 블록이 3개라면 있어야할 블록은 +
			else {	// 그렇지 않다면 다음 블록의 위치에 따라 있어야할 블록 지정
				// | 블록
				if(cd==ld && (cd==1 || cd==3)) {
					resultBlock = '-';
				}
				else if(cd==ld && (cd==0 || cd==2)) resultBlock = '|';
				else if((cd==0 && ld==1)||(cd==3 && ld==2)) resultBlock = '1';
				else if((cd==2 && ld==1)||(cd==3 && ld==0)) resultBlock = '2';
				else if((cd==1 && ld==0)||(cd==2&&ld==3)) resultBlock = '3';
				else if((cd==1 && ld==2)||(cd==0&&ld==3)) resultBlock = '4';
			}
			
			System.out.println((resultY+1)+" "+(resultX+1)+" "+resultBlock+"\n");
	}
	static int[] search(int cy, int cx, int cd) {
		for(int d=0; d<dy.length; d++) {
			int ny = cy + dy[d];
			int nx = cx + dx[d];
			// 탐색 좌표가 맵 내부이며 지나온 길이 아니고 공백이 아니라면
			if(0<=ny && ny<R && 0<=nx && nx<C && !visit[ny][nx] && map[ny][nx] != '.' && map[ny][nx] != 'Z') {
				return new int[] {ny, nx, d};
			}
		}
		
		// 만약 길이 없다면 
		return new int[]{cy,cx,cd};
	}
}