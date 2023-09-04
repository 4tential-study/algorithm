import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class B14621 {
  static class Edge implements Comparable<Edge>{
    int num;
    int weight;

    Edge(int num, int weight){
      this.num = num;
      this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
      return this.weight - o.weight;
    }
  }
  
  static List<Edge>[] edge_arrays;
  static boolean[] isMale; // 해당 학교가 남학교인지에 대한 여부
  static int schools, edges; // 학교 수, 연결하는 도로 수
  static int answer = Integer.MAX_VALUE;
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String[] input = in.readLine().split(" ");

    schools = Integer.parseInt(input[0]);
    edges = Integer.parseInt(input[1]);

    // 1번 학교부터 시작하므로 schools+1 만큼 할당
    edge_arrays = new ArrayList[schools+1];
    for(int i = 0; i < schools+1; i++)  edge_arrays[i] = new ArrayList<Edge>();
    isMale = new boolean[schools+1];

    input = in.readLine().split(" ");
    for(int i = 1; i <= schools; i++){
      isMale[i] = input[i-1].equals("M");
    }


    for(int i = 0; i < edges; i++){
      input = in.readLine().split(" ");
      int from = Integer.parseInt(input[0]);
      int to = Integer.parseInt(input[1]);
      int weight = Integer.parseInt(input[2]);

      // 성별이 다른 대학교끼리 연결되어 있다면 추가.
      if(isMale[from] != isMale[to]){
        edge_arrays[from].add(new Edge(to, weight));
        edge_arrays[to].add(new Edge(from, weight));
      }
    }

    for(int i = 1; i <= schools; i++) Collections.sort(edge_arrays[i]);
    
    solve(); // 프림 알고리즘 시작.

    if(answer == Integer.MAX_VALUE)  System.out.println(-1);
    else System.out.println(answer);
  }

  static void solve() {
    int edge_count = 0, road_length = 0;
    boolean[] visited = new boolean[schools+1];
    Queue<Edge> queue = new PriorityQueue<>();
    queue.offer(new Edge(1, 0));

    while(!queue.isEmpty()){
      Edge current = queue.poll();

      if(visited[current.num]) continue;

      visited[current.num] = true;
      edge_count++;
      road_length += current.weight;

      for(Edge edge : edge_arrays[current.num]){
        if(!visited[edge.num])
          queue.offer(edge);
      }
    }

    // 모든 학교를 방문했다면 answer의 값을 변경.
    if(edge_count == schools){
      answer = Math.min(answer, road_length);
      return;
    }
  }
}