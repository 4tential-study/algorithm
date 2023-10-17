package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// 잘 생각해서 접근해야 하는 문제였습니다.
// 전체 국가에 대하여, (0,0) 좌표에 있는 국가에서부터 (n-1,n-1) 좌표에 있는 국가까지 국민 수를 저장할 board 배열과 연합에 포함된(방문한) 국가를 저장할 boolean 배열을 생성합니다.
// (0,0) 국가에서부터 방문하여 방문처리 한 후 인근 국가의 연합 여부를 체크해줍니다.
// 연합인 국가에 대하여 방문여부를 확인하고 queue에 추가해줌으로써 효율을 높입니다.
// check, days 변수를 사용해 이동이 발생하였는지를 확인하고 며칠이 걸리는지 갱신해 줍니다.

public class baekjoon_16234_인구이동 {
	private static int N, L, R, check;
	private static int[][] board;
	private static boolean[][] visited;
	private static int[] dx= {-1,1,0,0};
	private static int[] dy= {0,0,-1,1};

	public static void main(String[] args) throws Exception {
		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		L = Integer.parseInt(line[1]);
		R = Integer.parseInt(line[2]);
		int days=0;
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] line2 = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(line2[j]);
			}
		}

		check=1; // 이동이 발생하는지 여부를 확인하기 위한 변수.
		while (check!=0) {
			check=0; // 아직 이동이 발생하지 않았으므로 0으로 초기화. 이동 발생시 transfer_population에서 갱신해준다.
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						transfer_population(i,j); // 아직 방문하지 않은 국가에 대해서 방문하여 연합 처리
					}
				}
			}
			if (check==1) { // 만약 이동이 발생하였다면, 하루 더 지난 것으로 세어준다.
				days++;
			}
		}
		System.out.println(days); // 총 몇일간 이동이 발생하였는지 출력.
	}
	
	private static void transfer_population(int i, int j) {
		int sum=0;
		int cnt=0;
		int nx,ny;
		Deque<int[]> queue = new ArrayDeque<>();
		List<int[]> li = new ArrayList<>(); // 연합인 국가의 좌표를 저장할 리스트
		queue.offer(new int[] {i,j});
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int x = temp[0];
			int y = temp[1];
			if (!visited[x][y]) {
				li.add(new int[] {x,y}); // 연합인 국가의 좌표를 리스트에 더해준다.
				visited[x][y]=true; // 방문 처리
				sum+=board[x][y]; // 총합을 구하기 위해 sum에 현재 국가의 국민 수를 더해줌
				cnt++; // 연합에 포함된 국가 개수를 세기 위한 변수 cnt
				for (int k=0;k<4;k++) {
					nx=x+dx[k];
					ny=y+dy[k];
					if (nx<0||ny<0||nx>=N||ny>=N) continue;
					// 연합에 해당하는 국가인지 확인
					if (Math.abs(board[x][y]-board[nx][ny])<=R && Math.abs(board[x][y]-board[nx][ny])>=L) {
						//연합에 해당한다면, 방문하지 않은 국가인 경우 queue에 추가
						if (!visited[nx][ny]) {
							queue.offer(new int[] {nx,ny});
						}
					}
				}
			}
		}
		// 만약 둘 이상의 국가가 연합에 포함되었다면, 국민 수를 갱신해줍니다.
		if (cnt>1) {
			// 이동이 일어났음을 알려주기 위해 check 변수 값을 갱신
			check=1;
			int val=(int) sum/cnt;
			// li에 저장된 연합 소속 국가에 대하여 국민 수 갱신
			for (int k=0;k<li.size();k++) {
				int[] temp = li.get(k);
				int x2=temp[0];
				int y2=temp[1];
				board[x2][y2]=val;
			}
		}
	}

}
