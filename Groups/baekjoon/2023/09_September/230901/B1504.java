import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class B1504 {

  public static class Node implements Comparable<Node>{
    int node_num;
    int distance;

    Node(int num, int distance){
      node_num = num;
      this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
      return this.distance - o.distance;
    }
  }
  
  private static int[][] map;
  private static int Node_count;
  private static final int INF = 200_000_001;
  public static void main(String[] args) throws Exception{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String[] input = in.readLine().split(" ");

    Node_count = Integer.parseInt(input[0]);
    int Edge_count = Integer.parseInt(input[1]);

    map = new int[Node_count+1][Node_count+1];


    for(int i = 0; i < Edge_count; i++){
      input = in.readLine().split(" ");
      int from = Integer.parseInt(input[0]);
      int to = Integer.parseInt(input[1]);
      int weight = Integer.parseInt(input[2]);
      map[from][to] = map[to][from] = weight;
    }

    input = in.readLine().split(" ");
    int nodeA = Integer.parseInt(input[0]);
    int nodeB = Integer.parseInt(input[1]);

    int resultAB, resultBA;
    resultAB = Dijkstra(1, nodeA) + Dijkstra(nodeA, nodeB) + Dijkstra(nodeB, Node_count);
    resultBA = Dijkstra(1, nodeB) + Dijkstra(nodeB, nodeA) + Dijkstra(nodeA, Node_count);

    int ans = Math.min(resultAB, resultBA);
    if(ans >= INF)  System.out.println(-1);
    else System.out.println(ans);
  }

  static int Dijkstra(int start, int finish){
    if(start == finish) return 0;
    
    int[] distances = new int[Node_count+1];
    Arrays.fill(distances, INF);

    Queue<Node> checkQueue = new PriorityQueue<>();
    checkQueue.offer(new Node(start, 0)); 
    distances[start] = 0;

    while(!checkQueue.isEmpty()){
      Node node = checkQueue.poll();
      int search = node.node_num;
      for(int i = 1; i <= Node_count; i++){
        if(map[search][i] == 0) continue;
        int value = node.distance + map[search][i];
        if(distances[i] > value){
          distances[i] = value;
          checkQueue.offer(new Node(i, value));
        }
      }
    }


    return distances[finish];
  }
}
