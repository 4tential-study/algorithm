// 메모리 초과... 내일 개선해보자
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class B10282 {
    private static final int INF = 999_999_999;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());

        while(T-- > 0){
            String[] input = in.readLine().split(" ");
            int computer_count = Integer.parseInt(input[0]);
            int dependencies = Integer.parseInt(input[1]);
            int hacked = Integer.parseInt(input[2]);

            int[][] map = new int[computer_count+1][computer_count+1];
            for(int[] arr : map)    Arrays.fill(arr, INF);

            for(int depend = 0; depend < dependencies; depend++){
                input = in.readLine().split(" ");
                int to = Integer.parseInt(input[0]);
                int from = Integer.parseInt(input[1]);
                map[from][to] = Integer.parseInt(input[2]);
            }

            Dijkstra(map, hacked, sb);
        }
        System.out.println(sb);
    }

    static void Dijkstra(int[][] map, int start, StringBuilder sb){
        Queue<int[]> queue = new PriorityQueue<>((o1, o2)->{
            return (o1[1] == o2[1])? o1[0] - o2[0] : o1[1] - o2[1];
        });
        int length = map.length;
        boolean[] visited = new boolean[length];
        int[] distances = new int[length];
        Arrays.fill(distances, INF);
        distances[start] = 0;
        queue.offer(new int[] {start, 0});

        while(!queue.isEmpty()){
            int num = queue.peek()[0];
            int cost = queue.poll()[1];

            if(visited[num]) continue;
            visited[num] = true;
            for(int i = 1; i < length; i++){
                if(!visited[i]){
                    int after = cost + map[num][i];
                    distances[i] = Math.min(distances[i], after);
                    queue.offer(new int[] {i, distances[i]});
                }
            }
        }

        Arrays.sort(distances);
        int count = 0, max = 0;
        for(int a : distances){
            if(a >= INF) break;
            count++;
            max = Math.max(max, a);
        }

        sb.append(count).append(" ");
        sb.append(max).append("\n");
    }
}
