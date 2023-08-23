import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_1953 {
    static BufferedReader br;
    static StringTokenizer st;
    static int[][] map;
    static int[][] deltas ={
            {-1,0},{0,-1},{1,0},{0,1}, //1
            {-1,0},{0,0},{1,0},{0,0}, //ㅅ
    };

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for(int tc = 1 ; tc <= t ; t++){
            String[] s = br.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            map = new int[n][m];
            int r = Integer.parseInt(s[2]);
            int c = Integer.parseInt(s[3]);
            int l = Integer.parseInt(s[4]);

            for(int i=0 ; i < n ; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < m ; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs(r,c);
        }
    }

    public static void bfs(int r, int c){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r,c});

        while(!queue.isEmpty()){
            int[] poll = queue.poll();
           switch(map[poll[0]][poll[1]]){
               case 1:

                   break;
                   case2:
                   break;
                   case3:
                   break;
                   case4:
                   break;
                   case5:
                   break;
                   case6:
                   break;
                   case7:
                   break;

            }
        }
    }
}
