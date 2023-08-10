import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/*
 * 이틀의 DFS,BFS 문제 풀이로 배운 것 정리
 * - BFS는 DFS보다 메모리를 많이 차지한다.
 * - 배열을 다음 함수나 반복문으로 넘겨줄 때는 배열 변수보다는 객체(new 타입[])으로 넘겨주는 것이 메모리 효율적이다.
 * - 값을 넘겨주는 것을 최소화하면 메모리 효율적이다
 */
public class Main {
	
	static int N;
//	static int result;
	static boolean[][] visited;
	// 10 ~ 21
	static int[][] d = { {-1,-2},{-2,-1},{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2} }; 
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(in.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(in.readLine());
			visited = new boolean[N][N];
//			result=Integer.MAX_VALUE;
			
			input = in.readLine().split(" ");
			int sy = Integer.parseInt(input[0]);
			int sx = Integer.parseInt(input[1]);
			
			input = in.readLine().split(" ");
			int ty = Integer.parseInt(input[0]);
			int tx = Integer.parseInt(input[1]);
			
			
			sb.append(bfs(sy,sx,ty,tx)+"\n");
		}
		System.out.println(sb.toString());
	}
	static int bfs(int sy, int sx, int ty, int tx) {
				
		Queue<int[]> q = new ArrayDeque<>();
//		q.offer(new int[] {sy,sx,0});
		q.offer(new int[] {sy,sx});
		visited[sy][sx] = true;
		
		int depth = 0;
		
		while(!q.isEmpty()) {
			int qSize = q.size();
			for(int e=0; e<qSize; e++) {
				int[] pos = q.poll();
				int cy = pos[0];
				int cx = pos[1];
	//			int depth = pos[2];
				
				if(cy==ty && cx==tx) {
//					result = Math.min(result, depth);
					return depth;
				}
				
				// 방향체크
				for(int i=0; i<d.length; i++) {
					int ny = cy + d[i][0];
					int nx = cx + d[i][1];
					
					if(canGo(ny,nx)) {
						visited[ny][nx] = true;
						q.offer(new int[] {ny, nx});
					}
				}
			}
			depth++;
		}
		return depth;
		
	}
	static boolean canGo(int y, int x) {
		return (0<=x && x<N && 0<=y && y<N) && (!visited[y][x]);
	}
}