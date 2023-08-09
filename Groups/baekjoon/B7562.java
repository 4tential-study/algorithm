import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class B7562 {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int test_case = Integer.parseInt(in.readLine());
    while(test_case-- > 0){
      int length = Integer.parseInt(in.readLine());
      int[] start, end;
      String[] temp = in.readLine().split(" ");
      start = new int[]{Integer.parseInt(temp[0]), Integer.parseInt(temp[1])};

      temp = in.readLine().split(" ");
      end = new int[]{Integer.parseInt(temp[0]), Integer.parseInt(temp[1])};

      if(isequalsCoordinate(start, end)) sb.append(0).append("\n");
      else sb.append(bfs(length, start, end)).append("\n");
    }
    System.out.println(sb);
  }

  static int bfs(int length, int[] start, int[] end){
    int count = 1;
    boolean[][] board = new boolean[length][length];
    int[][] dir = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
    Queue<int[]> queue = new ArrayDeque<>();

    queue.offer(start);
    while(!queue.isEmpty()){
      int search_count = queue.size();
      for(int i = 0; i < search_count; i++){
        int[] current = queue.poll();
        for(int type = 0; type < dir.length; type++){
          int dy = current[0] + dir[type][0], dx = current[1] + dir[type][1];
          if(!inBoard(dy, dx, length) || board[dy][dx]) continue;
          board[dy][dx] = true;
          if(isequalsCoordinate(new int[]{dy, dx}, end))  return count;
          queue.offer(new int[]{dy, dx});
        }
      }
      count++;
    }
    return count;
  }

  static boolean inBoard(int y, int x, int length){
    return y >= 0 && x >= 0 && y < length && x < length;
  }

  static boolean isequalsCoordinate(int[] a, int[] b){
    return a[0] == b[0] && a[1] == b[1];
  }
}
