package 백준_1967_트리의지름;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 백준_1967_트리의지름 {
	public static int p, c, w, ans;
	public static boolean[] visited;
	public static ArrayList<int[]>[] li;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		li = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) {
			li[i] = new ArrayList<int[]>();
		}
		for (int i = 0; i < (n - 1); i++) {
			String[] line = br.readLine().split(" ");
			p = Integer.parseInt(line[0]);
			c = Integer.parseInt(line[1]);
			w = Integer.parseInt(line[2]);
			li[p].add(new int[] { c, w });
			li[c].add(new int[] { p, w });
		}
		for (int i=1;i<=n;i++) {
			visited = new boolean[n+1];
			visited[i]=true;
			dfs(i,0);
		}
		System.out.println(ans);
	}

	public static void dfs(int now,int weight) {
		for (int[] temp : li[now]) {
			int i = temp[0];
			int w = temp[1];
			if (!visited[i]) {
				visited[i] = true;
				dfs(i,weight+w);
			}
		}
		ans = Math.max(ans,weight);
	}
}
