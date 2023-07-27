// https://www.acmicpc.net/problem/10971
import java.io.*;
import java.util.StringTokenizer;

public class B10971 {
	static int[][] cost;
	static int city, count, result = Integer.MAX_VALUE;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		city = Integer.parseInt(br.readLine());
		StringTokenizer st;
		cost = new int[city][city];
		visited = new boolean[city];
		for (int i = 0; i < city; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < city; j++)
				cost[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < city; i++) {
			count = 1;
			visited[i] = true;
			solve(i, i, 0);
			visited[i] = false;
		}
		System.out.println(result);
	}

	static void solve(int first, int index, int sum) {
		if (result <= sum) return; // 이미 최소값보다 크면 탐색 종료
		if (count == city && cost[index][first] != 0) { // 모두 도시를 돌았고, 다시 출발지까지 갈 수 있다면
			sum += cost[index][first];
			result = Math.min(result, sum);
			return;

		}

		for (int i = 0; i < city; i++) {
			// 이미 다녀온 도시거나, i번째 도시로 가는 길이 없다면 다른 도시 선택.
			if (visited[i] || cost[index][i] == 0) continue;
			visited[i] = true;	count++;
			solve(first, i, sum + cost[index][i]);
			visited[i] = false;	count--;
		}
	}
}
