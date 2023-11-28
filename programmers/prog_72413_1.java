import java.util.*;

class prog_72413_1 {
    static ArrayList<ArrayList<Node>> board = new ArrayList<>();
    static int[] distance;
    static boolean[] visited;
    static int[] withDistance;
    static int[][] dist;

    public class Node{
        int node;
        int edge;

        Node(int node, int edge){
            this.node = node;
            this.edge = edge;
        }


    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        distance = new int[n+1];
        visited = new boolean[n+1];
        dist = new int[n+1][n+1];

        for(int i=1 ; i <= n ; i++){
            for(int j=1 ; j <= n ; j++){
                if(i==j) {
                    dist[i][j] = 0;
                } else dist[i][j] = 40000000;

            }

        }
        for(int[] edge : fares){
            int si = edge[0];
            int e = edge[1];
            int x = edge[2];
            dist[si][e] = x;
            dist[e][si] = x;
        }

        floyd(n);

        int answer = (int) 1e9;

        for(int k = 1 ; k <= n ; k++){
            answer = Math.min(answer, dist[s][k]+dist[k][a]+dist[k][b]);
        }
        return answer;

    }




    public void floyd(int n){
        for(int k = 1 ; k <= n ; k++){
            for(int a = 1 ; a <= n ; a++){
                for(int b =  1 ; b <= n ; b++){
                    if(dist[a][k] + dist[k][b] < dist[a][b]){
                        dist[a][b] = dist[a][k] + dist[k][b];
                    }
                }
            }
        }
    }


}
