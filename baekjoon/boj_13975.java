import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class boj_13975 {
    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br= new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        for(int t = 0 ; t < T; t++){
            int n = Integer.parseInt(br.readLine());
            String[] splits = br.readLine().split(" ");
            int[] cost = new int[n];
            for(int i=0 ; i < splits.length ; i++){
                cost[i]= Integer.parseInt(splits[i]);
            }
            Arrays.sort(cost);
            PriorityQueue<Long> pq = new PriorityQueue<>();
            for(int i=0 ; i < cost.length ;i++){
                pq.offer(Long.valueOf(cost[i]));
            }
            long ans = 0;
            while(!pq.isEmpty()){
                if(pq.size() == 1) {
                    break;
                }
                long min = pq.poll();
                long lessMin = pq.poll();
                pq.offer(min+lessMin);
                ans += min+lessMin;
            }
           sb.append(ans+"\n");
        }
        System.out.println(sb.toString());
    }
}
