import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_21773 {
    static BufferedReader in;
    static StringTokenizer st;
    static StringBuilder sb;
    
    public static void main(String[] args) throws IOException {
    	sb = new StringBuilder();
    	
        PriorityQueue<Process> pq = new PriorityQueue<>();
        in = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = in.readLine().split(" ");
        int T = Integer.parseInt(inputs[0]);
        int n = Integer.parseInt(inputs[1]);
        for(int i=0 ; i < n ; i++){
            String[] line = in.readLine().split(" ");
            int id = Integer.parseInt(line[0]);
            int t = Integer.parseInt(line[1]);
            int ord = Integer.parseInt(line[2]);
            Process process = new Process(id, t, ord);
            pq.offer(process);
        }
        
        int sec = 1;
        while(sec++ <= T){
            if(pq.peek() == null) return;
            Process poll = pq.poll();
            sb.append(poll.id+ "\n");
            if(--poll.t <= 0) continue;
            pq.offer(new Process(poll.id, poll.t, --poll.ord));
           
        }
        
        System.out.println(sb.toString());
    }

    public static class Process implements Comparable<Process>{
        int id;
        int t;
        int ord;
        Process(int id, int t, int ord){
            this.id = id;
            this.t =t ;
            this.ord = ord;
        }

        @Override
        public int compareTo(Process o) {
            if(this.ord == o.ord) {
                return this.id - o.id;
            } else return o.ord - this.ord;
        }
    }
}
