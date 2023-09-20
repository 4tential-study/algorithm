import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class B21773 {
  static class Process implements Comparable<Process> {
    int id, priority, time;

    public Process(int id, int priority, int time) {
      this.id = id;
      this.priority = priority;
      this.time = time;
    }

    @Override
    public int compareTo(Process o) {
      return (this.priority == o.priority) ? this.id - o.id : o.priority - this.priority;
    }
  }
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    String[] input = in.readLine().split(" ");

    int Time = Integer.parseInt(input[0]);
    int process_count = Integer.parseInt(input[1]);

    Queue<Process> queue = new PriorityQueue<>();
    for(int i = 0; i < process_count; i++){
      input = in.readLine().split(" ");
      int id = Integer.parseInt(input[0]);
      int time = Integer.parseInt(input[1]);
      int priority = Integer.parseInt(input[2]);
      queue.offer(new Process(id, priority, time));
    }

    while(Time-- > 0 && !queue.isEmpty()){
      Process current = queue.poll();
      sb.append(current.id).append("\n");
      current.time--;
      if(current.time <= 0) continue;
      else {
        current.priority--;
        queue.offer(current);
      }
    }

    System.out.println(sb.toString());
  }
}