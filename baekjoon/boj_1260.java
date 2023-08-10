import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1260 {
    static int N;
    static int M;
    static int start;

    static boolean[] visited1;
    static boolean[] visited2;

    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        start = Integer.parseInt(s[2]);
        graph = new ArrayList[N+1];
        visited1 = new boolean[N+1];
        Arrays.fill(visited1, false);
        visited2 = new boolean[N+1];
        Arrays.fill(visited2 , false);

        for(int i=0; i <= N ; i++){
            graph[i] = new ArrayList<>();
        }

        for (int i = 1 ; i <= M ; i ++ ){
            String[] s1 = br.readLine().split(" ");

            Integer v1 = Integer.parseInt(s1[0]);
            Integer v2 = Integer.parseInt(s1[1]);

            graph[v1].add(v2);
            graph[v2].add(v1);

        }
        for(int i=1 ; i <=N ; i++){
            Collections.sort(graph[i]);
        }

        dfs(start);
//        Arrays.fill(visited, false);
        System.out.println();
        bfs(start);


    }

    public static void dfs(int v){
        visited1[v] = true;
        System.out.print(v + " ");
        for(int i : graph[v]){
            if(!visited1[i]) dfs(i);

        }

    }

    public static void bfs(int v){
        Queue<Integer> queue = new LinkedList<>();

        queue.add(v);
        visited2[v] = true;

        while(!queue.isEmpty()){
            Integer poll = queue.poll();
            System.out.print(poll + " ");
            for(int i : graph[poll]){
                if(visited2[i]) continue;

                queue.add(i);
                visited2[i] = true;
            }

        }
        System.out.println();
    }
}
