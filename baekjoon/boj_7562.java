import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj_7562 {
    static BufferedReader br;
    static int[][] board;
    static int[] tar;
    static int I;
    static int[] dy = new int[]{-2, -1, 1, 2, -2, -1, 1, 2};
    static int[] dx = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
    static boolean[][] visited;

    // bfs최단경로에서 필요한 자료구조
    //1. Queue
    //2. 방문여부 확인용 boolean 배열
    //3. 이동방향을 담은 int 배열
    //4. 최단이동거리를 구하는 경우, 이전 방문 칸의 이동거리를 활용하기!

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int i=0 ; i < t ; i++){
            I = Integer.parseInt(br.readLine());
            board = new int[I][I];
            visited = new boolean[I][I];

            String[] current = br.readLine().split(" "); // y,x
            String[] target = br.readLine().split(" ");
            int[] cur = new int[]{Integer.parseInt(current[0]), Integer.parseInt(current[1]) };
            tar = new int[]{Integer.parseInt(target[0]), Integer.parseInt(target[1]) };

            bfs(cur);
            System.out.println(board[tar[0]][tar[1]]);
        }
    }

    public static void bfs(int[] cur){
        Queue<int[]> queue = new LinkedList<>();

        queue.add(cur);

        while(!queue.isEmpty()){

            int[] poll = queue.poll();

            int y = poll[0];
            int x = poll[1];
            visited[y][x] = true;
            if(y == tar[0] && x == tar[1]){
                break;
            }

            for(int i=0 ; i < 8 ; i++){
                int ay = y + dy[i];
                int ax = x + dx[i];
                if (ay >= 0 && ay < I && ax >= 0 && ax < I && !visited[ay][ax]) {
                    queue.add(new int[]{ay,ax});
                    board[ay][ax] = board[y][x]+1;
                    visited[ay][ax] = true;
                }
            }
        }
    }
}
