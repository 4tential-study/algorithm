import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class boj_7576 {
    static BufferedReader br;
    static int[][] board;
    static boolean[][] visited;

    static StringTokenizer st;
    static int x;
    static int y;

    static int[] dy  = new int[]{0,-1,0,1};
    static int[] dx = new int[]{-1,0,-1,0};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        x = Integer.parseInt(s[0]);
        y = Integer.parseInt(s[1]);
        ArrayList<int[]> start = new ArrayList<>();
        board = new int[y][x];

        for(int i=0 ; i < y ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0 ; j < x ; j++){
                board[i][j]= Integer.parseInt(st.nextToken());
                if(board[i][j]== 1) {
                    start.add(new int[]{i,j});
                }
            }
        }

        int zero = 0;
        for(int i=0 ; i < y ; i++){
            for(int j=0 ; j < x ; j++){
                if( board[i][j] == 0 ) zero++;
            }
        }

        if( zero==0 ) {
            System.out.println(0);
            return;
        }

        bfs(start);

    }

    public static void bfs(ArrayList<int[]> start){
        Queue<int[]> queue = new LinkedList<>();
        for(int[] each : start){
            queue.add(each);
            visited[each[0]][each[1]] = true;
        }
        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            for(int i=0 ; i < 4 ; i++) {
                int ay = poll[0] + dy[i];
                int ax = poll[1] + dx[i];
                if( ax>=0 && ax < x && ay >=0 && ay < y && board[ay][ax]==0 &&!visited[ay][ax]){
                    queue.add(new int[]{ay,ax});
                    visited[ay][ax] = true;
                    board[ay][ax] = 1;
                }
            }
        }

    }
}
