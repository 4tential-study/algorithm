package 백준_2468_안전영역;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon_2468_안전영역 {
	// cnt : 물이 차오른 높이별 안전 영역 카운트 / result : cnt와 대조하여 가장 큰 값을 반환하기 위한 변수
	public static int cnt;
	public static int result = 0;
	public static int x;
	public static int y;

	public static void main(String[] args) throws Exception {
		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		int max = 0;
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(line[j]);
				max = Math.max(arr[i][j], max);
			}
		}
		// x,y 좌표값 갱신을 위한 배열
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { 1, 0, -1, 0 };

		for (int height = 0; height <= max; height++) {
			// 안전 영역 개수인 cnt 초기화
			cnt = 0;

			// boolean 배열인 drown에 해당 높이(height)에서 잠긴 곳을 true로 표시
			boolean[][] drown = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] <= height)
						drown[i][j] = true;
				}
			}
			// 큐 선언, (0,0)에서 시작
			Queue<int[]> queue = new LinkedList<>();

			for (int a = 0; a < N; a++) {
				for (int b = 0; b < N; b++) {

					// 해당 좌표(x,y)가 가라앉은 곳이 아니라면
					if (drown[a][b] == false) {
						// 가라앉지 않은 안전 영역을 세는 변수 cnt에 +1
						cnt += 1;
						// 해당 좌표 방문했으므로 true로 표시
						drown[a][b] = true;
						// queue 시작
						queue.add(new int[] { a, b });

						while (!queue.isEmpty()) {
							int now[] = queue.poll();
							x = now[0];
							y = now[1];
							for (int i = 0; i < 4; i++) {
								int nx = x + dx[i];
								int ny = y + dy[i];
								// nx나 ny가 배열 범위를 벗어나는 경우 continue
								if (nx < 0 || ny < 0 || nx >= N || ny >= N)
									continue;
								// 해당 좌표가 이미 잠긴 곳이면 continue
								if (drown[nx][ny] == true)
									continue;
								drown[nx][ny] = true;
								queue.add(new int[] { nx, ny });
							}
						}
					}
				}
			}
			result = Math.max(cnt, result);
		}
		System.out.println(result);
	}
}
