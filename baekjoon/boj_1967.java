import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.StringTokenizer;

public class boj_1967 {
	static BufferedReader br;
	static StringTokenizer st;
	
//	12
//	1 2 3
//	1 3 2
//	2 4 5
//	3 5 11
//	3 6 9
//	4 7 1
//	4 8 7
//	5 9 15
//	5 10 4
//	6 11 6
//	6 12 10
	static int n;
	static ArrayList<int[]>[] adjList;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		adjList = new ArrayList[n+1];
		for(int i=1 ; i <= n ; i++) {
			adjList[i] = new ArrayList<int[]>();
		}
		
		for(int i=1 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[p].add(new int[] {c,w});
		}
		
		//리프노드부터 리프노드까지..
		for(int i=1 ; i <= n ; i++ ) {
			if (adjList[i].isEmpty()) 
		}
	}

=======

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
>>>>>>> 48634aac4b29ee02d9f6dd3397067cc11e22b08b
}
