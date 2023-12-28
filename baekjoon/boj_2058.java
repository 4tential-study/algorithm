import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class boj_2058 {
    static BufferedReader br;
    static int[] indegree;
    static boolean[] visited;
    static int[] times;
    static ArrayList<Integer>[] adj;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        indegree = new int[n+1];
        visited = new boolean[n+1];
        times = new int[n+1];
        adj = new ArrayList[n+1];
        for(int i=0 ; i <= n ; i++){
            adj[i] = new ArrayList<>();
        }
        int[] ts = new int[n+1];
        for(int i=0 ; i < n ; i++){
            String[] splits = br.readLine().split(" ");
            int time = Integer.parseInt(splits[0]);
            ts[i+1] = times[i+1] = time;
            int cnt = Integer.parseInt(splits[1]);
            indegree[i+1] = cnt;
            for(int j=0 ; j < cnt ; j++){
                int pre = Integer.parseInt(splits[j+2]);
                adj[pre].add(i+1);
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        for(int i=1 ; i <= n ; i++){
            if(indegree[i] == 0 && !visited[i]) {
                q.offer(new int[]{i, times[i]});
                visited[i] = true;
            }
        }


        int t = 0;
        while(!q.isEmpty()){
            int[] poll = q.poll();
            for(Integer each : adj[poll[0]]){
                ts[each] = Math.max(ts[each], ts[poll[0]] + times[each]);
                indegree[each]--;
                if(indegree[each] == 0 && !visited[each]) {
                    q.offer(new int[]{each, times[each]});
                    visited[each] = true;
                }
            }
        }
        int ans = 0;
        for(int i=0 ; i < ts.length ; i++){
            ans=Math.max(ans, ts[i]);
        }
        System.out.println(ans);

    }
}
