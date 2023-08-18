import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2206 {
  // 우 하 좌 상
  static final char ROAD = '0', WALL = '1';
  static final byte[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(in.readLine());
    int height = Integer.parseInt(st.nextToken());
    int width = Integer.parseInt(st.nextToken());

    char[][] board = new char[height][width];

    for(int y = 0; y < height; y++)
      board[y] = in.readLine().toCharArray();
    boolean[][][] visited = new boolean[height][width][2]; // 벽을 깼을 경우, 깨지 않았을 경우

    Queue<int[]> queue = new ArrayDeque<>();
    queue.offer(new int[]{0,0,0});  visited[0][0][0] = true;
    int length = 0; // 이동 거리
    boolean finish = false;

    Search:
    while(!queue.isEmpty()){
      int size = queue.size();
        for(int i = 0; i < size; i++){
        int cy = queue.peek()[0]; int cx = queue.peek()[1];
        int isBreak = queue.poll()[2];
        for(byte type = 0; type < 4; type++){
          int dy = cy + dir[type][0], dx = cx + dir[type][1];
          if(dy >= 0 && dx >= 0 && dy < height && dx < width && !visited[dy][dx][isBreak]){
            if(board[dy][dx] == WALL && isBreak < 1){
              visited[dy][dx][isBreak+1] = true;
              queue.offer(new int[]{dy, dx, isBreak+1});
              if(dy == height-1 && dx == width-1){
                length++; finish = true; break Search;
              }
            }
            else {
              visited[dy][dx][isBreak] = true;
              queue.offer(new int[]{dy, dx, isBreak});
              if(dy == height-1 && dx == width-1){
                length++; finish = true; break Search;
              }
            }
          }
        }
      }
      length++;
    }

    if(!finish) System.out.println(-1);
    else System.out.println(length);
  }
}
