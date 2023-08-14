package 백준_1325_효율적인해킹;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter; //sb대신 얘를 써봄
import java.util.ArrayList;
import java.util.StringTokenizer;//시간초과나서 사용해봄(근데 상관없는듯)


//11번 출력초과나며 제 코드를 정답 코드대로 조금씩 뜯어고친 끝에, 마지막으로 Exception까지 IOException으로 고치고 통과함
//원형은 거의 남아있지 않다...
//할말이없네요 ㄹㅇ
//출제자는 효율적인 해킹이 아니라 효율적인 코딩을 시키고 싶었던 것 같음...

public class 백준_1325_효율적인해킹 {
	public static int max;
	public static int[] count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] li = new ArrayList[N+1];
		count = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			li[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			li[A].add(B); // 왜지 (1)
		}
		for (int i = 1; i <= N; i++) {
			boolean[] visited = new boolean[N + 1];
			dfs(i, visited,li);
		}
		for (int i = 1; i <= N; i++) {
			max = Math.max(count[i], max);
		}
		for (int i = 1; i <= N; i++) {
			if (count[i] == max)
				bw.write(i + " ");
		}
		bw.flush();
		bw.close();
	}

	public static void dfs(int now, boolean[] visited, ArrayList<Integer>[] li) {
		visited[now] = true;
		for (int i : li[now]) {
			if (!visited[i]) {
				count[i]++; // 왜지 (2)
				dfs(i,visited,li);
			}
		}
	}
}