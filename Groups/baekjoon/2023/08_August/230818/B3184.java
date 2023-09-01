import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class B3184{
  static final char FLAT = '.', FENCE = '#', SHEEP = 'o', WOLF = 'v';
  static final byte[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
  static boolean[][] visited;
  static int sheep_count, wolves_count;
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String[] input = in.readLine().split(" ");
    int R = Integer.parseInt(input[0]); // 행
    int C = Integer.parseInt(input[1]); // 열

    char[][] garden = new char[R][C]; // 마당

    for(int i = 0; i < R; i++)
      garden[i] = in.readLine().toCharArray();
    
    visited = new boolean[R][C];
    for(int y = 0; y < R; y++){
      for(int x = 0; x < C; x++){
        if(!visited[y][x] && garden[y][x] != FENCE) solve(garden, y, x); 
      }
    }

    System.out.println(sheep_count + " " + wolves_count);
  }

  static void solve(char[][] board, int y, int x){
    int R = board.length, C = board[0].length;
    int sheep = 0, wolf = 0;
    Queue<int[]> queue = new ArrayDeque<>();
    queue.offer(new int[] {y, x});  visited[y][x] = true;
    while(!queue.isEmpty()){
      int cy = queue.peek()[0], cx = queue.poll()[1];
      // 해당 칸에 있는 것이 양인지 늑대인지 판별
      switch(board[cy][cx]){
        case SHEEP: sheep++;  break;
        case WOLF:  wolf++;   break;
      }
      for(byte type = 0; type < 4; type++){
        int dy = cy + dir[type][0];
        int dx = cx + dir[type][1];
        if(dy >= 0 && dy < R && dx >= 0 && dx < C 
        && !visited[dy][dx] && board[dy][dx] != FENCE){
          visited[dy][dx] = true;
          queue.offer(new int[] {dy, dx});
        }
      }
    }
    // 양 > 늑대 라면 양의 승리, 그렇지 않으면 늑대의 승리
    if(sheep > wolf)  sheep_count += sheep;
    else wolves_count += wolf;
  }
}