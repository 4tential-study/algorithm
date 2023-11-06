import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1516 {
    static BufferedReader in;
    static StringTokenizer st;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer[]> q = new ArrayDeque<>();
        int n = Integer.parseInt(in.readLine());
        int[] ans = new int[n+1];
        int[] array = new int[n+1];
        boolean[] visited = new boolean[n+1];
        int[] times = new int[n+1];
        adj = new ArrayList[n+1];

        for(int i=0 ; i <= n ; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i=1 ;  i <= n ; i++){
            String[] ins = in.readLine().split(" ");
            if(ins.length == 1) ;
            int t = Integer.parseInt(ins[0]);
            times[i] = t;
            for(int j=1 ; j < ins.length ; j++) {
                if (ins[j].equals("-1")) break;
                else {
                    array[i] += 1;
                    adj[Integer.parseInt(ins[j])].add(i);
                }
            }
        }
        
        for(int i=1 ; i <= n ; i++){
            if(array[i] == 0) {
                q.offer(new Integer[]{i, times[i]});
                visited[i] = true;
            }
        }

        while(!q.isEmpty()){
            Integer[] poll = q.poll();
            ans[poll[0]] = poll[1];
           
            for(Integer each : adj[poll[0]]){ // 3, 5
            	int maxTime = Math.max(ans[each], poll[1]); // 60
            	ans[each] = maxTime;
                if(!visited[each]){
                    array[each]--;
                    if(array[each] == 0){
                        visited[each] = true;
                        q.offer(new Integer[]{each, maxTime+times[each]});//4, 100
                        ans[each] = Math.max(ans[each], maxTime+times[each]);
                    }   
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1 ; i < ans.length ; i++){
            sb.append(ans[i] + "\n");
        }
        System.out.println(sb.toString());
    }
}
