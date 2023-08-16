import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static BufferedReader br;
    static ArrayList<int[]>[] adj;
    static int n;
    static int path;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n+1];
        for(int i = 0 ; i < n+1 ; i++){
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
        int longest = 0;
        for(int i=1 ; i < n ; i++){
            if(i*2 > n){
                path = 0;
                visited = new boolean[n+1];
                int each = dfs(i,true);
                if (longest < each) {
                    longest = each;
                }
            }
        }
        System.out.println(longest);
    }

    public static int dfs(int i, boolean wasLeaf){
        visited[i] = true;
        if( i * 2 > n && !wasLeaf){
            return path;
        }

        int[] max = new int[]{0,0};
        for(int[] each : adj[i]){
            if(!visited[each[0]]) {
                if (each[1] > max[1]) {
                    max[1] = each[1];
                    max[0] = each[0];
                }
            }
        }
        dfs(max[0], i*2 > n );
        visited[max[0]] = true;
        path += max[1];
        return path;
    }
}
