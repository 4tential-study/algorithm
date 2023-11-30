import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class boj_1263 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Task> pq = new PriorityQueue<>();
        for(int i=0 ; i < n ; i++){
            String[] str = br.readLine().split(" ");
            int t = Integer.parseInt(str[0]);
            int s = Integer.parseInt(str[1]);
            pq.offer(new Task(t,s)); // 비용, 마감시간
        }

        int time = 9999999;
        while(!pq.isEmpty()){
           Task task = pq.poll();
            if (time <= task.s) {
                time -= task.t;
            } else if (time > task.s){
                time = task.s - task.t; // 시간 갱신
            }
        }
        time = time < 0 ? -1 : time;
        System.out.println(time);
    }

    public static class Task implements Comparable<Task>{
        int t;
        int s;

        @Override
        public int compareTo(Task o) {
            if(this.s == o.s){
                return this.t-o.t;
            }
            return o.s - this.s;
        }

        Task(int t, int s){
            this.t = t;
            this.s = s;
        }
    }
}
