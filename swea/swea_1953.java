package Algorithm.boj.day0824;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_1953 {
	
	// 상 우 하 좌
	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 };	
	
	static int N, M, R, C, L;
	static int[][] map;
	static boolean[][] visit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(in.readLine());
		for(int tc=1; tc<=T; tc++) {
			out.write("#"+String.valueOf(tc)+" ");
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visit = new boolean[N][M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			// bfs 구현
			
			Queue<int[]> q = new ArrayDeque<>();
			q.offer(new int[] {R, C});
			visit[R][C] = true;
			int result = 0;
			int curTime = 1;
			
			while(!q.isEmpty()) {
				int qSize = q.size();
				
				for(int i=0; i<qSize; i++) {
					int y = q.peek()[0];
					int x = q.poll()[1];
					int block = map[y][x];
					result++;
					
					
					int ny, nx;
					// 현재 블럭 종류와 다음 위치의 블럭 존재 여부에 따라 다음 위치를 큐에 삽입
					
					// 1. 현재 블럭 종류 파악
					// 2. 종류에 따라 다음 위치 계산
					// 3. 다음 위치에 블럭 존재 유무 및 블럭 종류 파악 후 조건이 맞으면 큐에 삽입
					// 4. 출력값인 장소의 개수 증가
					// 5. 2~4번 반복
					
					switch(block) {
					case 1:
						ny = y + dy[0]; nx = x + dx[0];
						if(canGo(ny, nx) && canUp(map[ny][nx])) {
							visit[ny][nx] = true;
							q.offer(new int[] {ny,nx});
						}
						
						ny = y + dy[1]; nx = x + dx[1];
						if(canGo(ny, nx) && canRight(map[ny][nx])) {
							visit[ny][nx] = true;
							q.offer(new int[] {ny,nx});
						}
						
						ny = y + dy[2]; nx = x + dx[2];
						if(canGo(ny, nx) && canDown(map[ny][nx])) {
							visit[ny][nx] = true;
							q.offer(new int[] {ny,nx});
						}
						
						ny = y + dy[3]; nx = x + dx[3];
						if(canGo(ny, nx) && canLeft(map[ny][nx])) {
							visit[ny][nx] = true;
							q.offer(new int[] {ny,nx});
						}
						break;
					case 2:
						ny = y + dy[0]; nx = x + dx[0];
						if(canGo(ny, nx) && canUp(map[ny][nx])) {
							visit[ny][nx] = true;
							q.offer(new int[] {ny,nx});
						}
						ny = y + dy[2]; nx = x + dx[2];
						if(canGo(ny, nx) && canDown(map[ny][nx])) {
							visit[ny][nx] = true;
							q.offer(new int[] {ny,nx});
						}
						break;
					case 3:
						ny = y + dy[1]; nx = x + dx[1];
						if(canGo(ny, nx) && canRight(map[ny][nx])) {
							visit[ny][nx] = true;
							q.offer(new int[] {ny,nx});
						}
						ny = y + dy[3]; nx = x + dx[3];
						if(canGo(ny, nx) && canLeft(map[ny][nx])) {
							visit[ny][nx] = true;
							q.offer(new int[] {ny,nx});
						}
						break;
					case 4:
						ny = y + dy[0]; nx = x + dx[0];
						if(canGo(ny, nx) && canUp(map[ny][nx])) {
							visit[ny][nx] = true;
							q.offer(new int[] {ny,nx});
						}
						
						ny = y + dy[1]; nx = x + dx[1];
						if(canGo(ny, nx) && canRight(map[ny][nx])) {
							visit[ny][nx] = true;
							q.offer(new int[] {ny,nx});
						}
						break;
					case 5:
						ny = y + dy[1]; nx = x + dx[1];
						if(canGo(ny, nx) && canRight(map[ny][nx])) {
							visit[ny][nx] = true;
							q.offer(new int[] {ny,nx});
						}
						
						ny = y + dy[2]; nx = x + dx[2];
						if(canGo(ny, nx) && canDown(map[ny][nx])) {
							visit[ny][nx] = true;
							q.offer(new int[] {ny,nx});
						}
						break;
					case 6:
						ny = y + dy[2]; nx = x + dx[2];
						if(canGo(ny, nx) && canDown(map[ny][nx])) {
							visit[ny][nx] = true;
							q.offer(new int[] {ny,nx});
						}
						
						ny = y + dy[3]; nx = x + dx[3];
						if(canGo(ny, nx) && canLeft(map[ny][nx])) {
							visit[ny][nx] = true;
							q.offer(new int[] {ny,nx});
						}
						break;
					case 7:
						ny = y + dy[0]; nx = x + dx[0];
						if(canGo(ny, nx) && canUp(map[ny][nx])) {
							visit[ny][nx] = true;
							q.offer(new int[] {ny,nx});
						}
						ny = y + dy[3]; nx = x + dx[3];
						if(canGo(ny, nx) && canLeft(map[ny][nx])) {
							visit[ny][nx] = true;
							q.offer(new int[] {ny,nx});
						}
						break;
					}
				}

				if(curTime++ == L) break;
			}
			out.write(String.valueOf(result)+"\n");
		}
		out.close();
	}
	static boolean canGo(int y, int x) {
		return (0<=y && y<N && 0<=x && x<M && !visit[y][x]);
	}
	static boolean canUp(int block) {
		return (   block == 1 
				|| block == 2
				|| block == 5
				|| block == 6);
	}
	static boolean canDown(int block) {
		return (   block == 1 
				|| block == 2
				|| block == 4
				|| block == 7);
	}
	static boolean canLeft(int block) {
		return (   block == 1 
				|| block == 3
				|| block == 4
				|| block == 5);
	}
	static boolean canRight(int block) {
		return (   block == 1 
				|| block == 3
				|| block == 6
				|| block == 7);
	}
}