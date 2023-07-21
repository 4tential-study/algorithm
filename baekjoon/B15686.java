import java.io.*;
import java.util.*;

public class B15686 {
	static ArrayList<int[]> houses = new ArrayList<>();
	static ArrayList<int[]> chickens = new ArrayList<>();
	static boolean[] selected; // i번째 치킨 집이 선택되었는지 확인.
	static int minDistance = Integer.MAX_VALUE; // 최종적으로 알아낼 값.

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 지도 size
		int M = Integer.parseInt(st.nextToken()); // 최대 치킨집 개수

		int[][] board = new int[N][N]; // 지도
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
				if (board[y][x] == 0)
					continue;
				int[] coord = new int[] { y, x }; // 집, 치킨집 좌표 저장.
				if (board[y][x] == 1)
					houses.add(coord);
				else
					chickens.add(coord);
			}
		}
		selected = new boolean[chickens.size()];
		solve(0, 0, M);

		System.out.println(minDistance);

	}
	/*
	 * M개만큼 치킨집을 선택하여
	 * 각 집마다 치킨 거리를 모두 더한 값을 minDistance와 비교한다.
	 */
	static void solve(int count, int idx, int M) {
		if (count == M) {
			int sum = 0;
			for (int i = 0; i < houses.size(); i++) { // i번째 집.
				int ans = Integer.MAX_VALUE;
				for (int j = 0; j < chickens.size(); j++) {
					if(selected[j]) { // 선택한 치킨집일 경우
						int value = Math.abs(houses.get(i)[0] - chickens.get(j)[0]) + Math.abs(houses.get(i)[1] - chickens.get(j)[1]);
						ans = Math.min(ans, value); // i번째 집의 치킨 거리.
					}
				}
				sum += ans; // 선택된 치킨집 조합의 도시 치킨 거리를 구한다.
			}
			// 현재까지 탐색한 최소 도시 치킨 거리와 비교.
			minDistance = Math.min(minDistance, sum);
			return;
		}

		if (idx == chickens.size())
			return;
		
		// 백트래킹
		for (int i = idx; i < chickens.size(); i++) {
			selected[i] = true;
			solve(count + 1, i + 1, M);
			selected[i] = false;
		}
	}
}