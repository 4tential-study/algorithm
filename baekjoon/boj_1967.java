import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class boj_1967 {
    static BufferedReader br;
    static ArrayList<int[]>[] adj;
    static int n;
    static int path;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n+1];
        for(int i = 0 ; i <= n ; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < n-1 ; i++){
            String[] str = br.readLine().split(" ");
            int p = Integer.parseInt(str[0]);
            int c = Integer.parseInt(str[1]);
            int w = Integer.parseInt(str[2]);
            adj[p].add(new int[]{c,w});
            adj[c].add(new int[]{p,w});
        }

        for(int i=1 ; i <= n ; i++){
            visited = new boolean[n+1];
            dfs(i,0);
        }
        System.out.println(path);
    }

    public static void dfs(int i, int weight){
        visited[i] = true;
        for(int[] each : adj[i]) {
            int idx = each[0];
            int w = each[1];
            if (!visited[idx]) {
                visited[idx] = true;
                dfs(idx, weight + w);
            }
        }
        path = Math.max(path, weight);
    }
}
