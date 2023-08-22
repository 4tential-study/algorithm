import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
//bfs
public class boj_2206 {
    static BufferedReader br;
    static StringTokenizer st;
    static int[][] map;
    static int n;
    static int m;
    static int[][] ans ;
    static boolean[][] used;

    static final int VISITED  = 100;
    static int[][] deltas = {{1,0},{0,1}, {-1,0}, {0,-1}};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        map = new int[n][m];
        ans = new int[n][m];
        used= new boolean[n][m];

        for(int i=0 ; i < n ; i++){
            String s = br.readLine();
            for(int j = 0  ; j < m ; j++){
                map[i][j] = s.charAt(j) - '0';

            }
        }
        ans[0][0] = 1;
        bfs(0,0);
        for(int i=0 ; i < n  ; i++){
            for(int j = 0 ; j < m ;j ++){
                System.out.print(ans[i][j]);
            }
            System.out.println();
        }
        for(int i=0 ; i < n  ; i++){
            for(int j = 0 ; j < m ;j ++){
                System.out.print(used[i][j]);
            }
            System.out.println();
        }
        int answer = ans[n-1][m-1] != 0 ? ans[n-1][m-1] : -1;
        System.out.println(answer);
    }

    public static void bfs(int y, int x){
        Queue<int[]> dq = new ArrayDeque<int[]>();
        dq.add(new int[]{y,x});

        while(!dq.isEmpty()){
            int[] poll = dq.poll(); //5,2
            for(int i=0 ; i < 4 ; i++){
                int ay = poll[0] + deltas[i][0];
                int ax = poll[1] + deltas[i][1];

                if(ay >= 0 && ay < n && ax >= 0 && ax < m && map[ay][ax] != VISITED){
                    if(!used[poll[0]][poll[1]] && map[ay][ax] == 1 ){
                        map[ay][ax] = VISITED;
                        used[ay][ax] = true;
                        dq.add(new int[]{ay,ax});
                        ans[ay][ax] = ans[poll[0]][poll[1]] + 1;
                    } else if(map[ay][ax] == 0) {
                        map[ay][ax] = VISITED;
                        dq.add(new int[]{ay, ax});
                        ans[ay][ax] = ans[poll[0]][poll[1]] + 1;
                        used[ay][ax] = used[poll[0]][poll[1]];
                    }
                }
            }
        }
    }
}
