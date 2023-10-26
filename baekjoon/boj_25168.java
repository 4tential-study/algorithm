import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_25168{
    static BufferedReader in;
    static StringTokenizer st;

    static int n;
    static int m;

    static final int EXPIRE = 7;
    static ArrayList<int[]>[] adj;
    public static void main(String[] args) throws IOException{
        in = new BufferedReader(new InputStreamReader(System.in));
        String[] ins = in.readLine().split(" ");
        int n = Integer.parseInt(ins[0]);
        int m = Integer.parseInt(ins[1]);
        adj = new ArrayList[n+1];
        int[] days = new int[n+1];
        for(int i=0 ; i <= n ;i++) {
            adj[i] = new ArrayList<>();
        }
        int[] indegree = new int[n+1];
        for(int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            indegree[e]++;
            adj[s].add(new int[] {e,w});
        }
        Queue<Integer> q = new ArrayDeque<>();

        for(int i=1 ; i <= n ; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
                days[i]= 1;
            }
        }


        while(!q.isEmpty()) {
            int poll = q.poll();//{s, 마지막 갱신된 접종일}

            for(int[] each : adj[poll]) {//{e, w} //each[1] == 대기식간
                int e = each[0];
                int next = days[poll]+each[1];

                if(each[1] >= EXPIRE ) ++next;
                if(indegree[e] == 0)  continue;
                if(days[e] < next) days[e] = next;
                if(--indegree[e] == 0) q.offer(e);

                }

        }

        int max = 0;
        for(int i = 1 ; i <= n ; i++) {
            max= Math.max(max, days[i]);
        }
        System.out.println(max);
    }
}