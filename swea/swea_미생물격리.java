package swea_미생물격리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class swea_미생물격리 {
	public static int N, M, K, x, y, num, dir, nx, ny, time, result;
	public static int[] temp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append('#').append(test_case).append(' ');
			result=0; // test_case마다 result 초기화
			// 입력받기
			String[] line = br.readLine().split(" ");
			N = Integer.parseInt(line[0]); // 한 변의 셀의 개수
			M = Integer.parseInt(line[1]); // 격리 시간
			K = Integer.parseInt(line[2]); // 미생물 군집의 개수
			int[][] num_arr = new int[N][N]; // 미생물 수를 표시할 배열
			int[][] dir_arr = new int[N][N]; // 이동 방향을 표시할 배열
			// 두 개의 배열에 입력받기 & 덱에 x,y쌍 입력하기
			Deque<int[]> queue = new ArrayDeque<int[]>();
			for (int i = 0; i < K; i++) {
				String[] line2 = br.readLine().split(" ");
				x = Integer.parseInt(line2[0]);
				y = Integer.parseInt(line2[1]);
				num = Integer.parseInt(line2[2]);
				dir = Integer.parseInt(line2[3])-1; // 실제 인덱스는 0,1,2,3이지만 주어지는 것은 1,2,3,4 이므로 1을 빼준다.
				num_arr[x][y] = num;
				dir_arr[x][y] = dir;
				queue.offer(new int[] { x, y, 0 }); // 현재 미생물 군집의 위치를 나타내는 x, y와 시간 경과를 나타낼 t(시작값은 0) 표시
			}
			// M 시간동안의 변화
			int[] dx = { -1, 1, 0, 0 }; // 이동을 위한 dx, dy 배열 선언
			int[] dy = { 0, 0, -1, 1 };
			int t=0;
			while (time <= M && !queue.isEmpty()) {
				System.out.println(time);
				System.out.println("=======");

				for (int p = 0;p<N;p++) System.out.println(Arrays.toString(num_arr[p]));
//				for (int q=0;q<N;q++) System.out.println(Arrays.toString(dir_arr[q]));
//				System.out.println("=======");
				temp = queue.poll();
				x = temp[0];
				y = temp[1];
				t = temp[2]; // 경과 시간을 나타낼 time
				System.out.println(temp[0]+" "+temp[1]+" "+num_arr[temp[0]][temp[1]]);
				// 1. 각 군집들은 1시간마다 이동방향에 있는 다음 셀로 이동한다.
				dir = dir_arr[x][y];
				nx = x + dx[dir];
				ny = y + dy[dir];
				if (nx<0||nx>=N||ny<0||ny>=N) continue; // 범위를 벗어나면 continues
				// 2. 미생물 군집이 약품이 칠해진 셀에 도착하면 군집 내 미생물 절반이 죽고
				if (nx == 0 || nx == N - 1 || ny == 0 || ny == N - 1) {
					// 이동할 칸이 비어있는 경우
					if (num_arr[nx][ny] == 0) {
						num_arr[nx][ny] = num_arr[x][y] / 2;
						num_arr[x][y] = 0;
						// 이동방향이 반대로 바뀐다.
						if (dir == 0)
							dir_arr[nx][ny] = 1;
						else if (dir == 1)
							dir_arr[nx][ny] = 0;
						else if (dir == 2)
							dir_arr[nx][ny] = 3;
						else
							dir_arr[nx][ny] = 2;
					} else { // 이동할 칸에 군집이 있는 경우
						if (num_arr[nx][ny] < num_arr[x][y]) { // 새로 들어올 군집의 미생물 수가 더 많으면 들어올 군집의 이동방향으로 바뀐다.
							dir_arr[nx][ny] = dir_arr[x][y];
						}
						num_arr[nx][ny] += num_arr[x][y] / 2;
						num_arr[x][y]=0;
					}
				} else { // 약품이 칠해지지 않은 셀에 도착하면
					if (num_arr[nx][ny] == 0) { // 빈 칸으로 이동할 경우 그냥 이동
						num_arr[nx][ny] = num_arr[x][y];
						num_arr[x][y] = 0;
						dir_arr[nx][ny]=dir_arr[x][y];
					} else {
						// 새로 들어올 미생물의 수가 더 많을 경우, 그 미생물 군집의 이동 방향으로 갱신
						if (num_arr[nx][ny] < num_arr[x][y])
							dir_arr[nx][ny] = dir_arr[x][y];
						// 미생물 수가 합쳐짐
						num_arr[nx][ny]+=num_arr[x][y];
						num_arr[x][y]=0;
					}
				}
				if (num_arr[nx][ny]!=0) queue.offer(new int[] {nx,ny,t+1});
				time=Math.max(t+1, time);
			}
			for (int i=0;i<N;i++) {
				for (int j=0;j<N;j++) {
					result+=num_arr[i][j];
				}
			}
			sb.append(result).append('\n');
		}
		System.out.println(sb);
	}

}