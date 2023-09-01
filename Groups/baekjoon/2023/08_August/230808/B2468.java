import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B2468 {
  // 우, 하, 좌, 상
  static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(in.readLine());
    int[][] board = new int[N][N];
    TreeSet<Integer> set = new TreeSet<>();
    for (int i = 0; i < N; i++) {
      String[] str = in.readLine().split(" ");
      for (int j = 0; j < N; j++) {
        board[i][j] = Integer.parseInt(str[j]);
        set.add(board[i][j]);
      }
    }

    int max_height = set.last(), max_count = 1;
    for (int height : set.headSet(max_height)) { // 마지막 원소를 제외하고 탐색.
            int count = bfs(board, N, height);
            max_count = Math.max(count, max_count);
    }
    
    System.out.println(max_count);
  }

  static int bfs(int[][] board, int length, int height) {
    int count = 0;
    Queue<int[]> queue = new ArrayDeque<>();
    boolean[][] isvisited = new boolean[length][length];
    for (int y = 0; y < length; y++) {
      for (int x = 0; x < length; x++) {
        if (isvisited[y][x]) continue;
        isvisited[y][x] = true;
        if (board[y][x] <= height)  continue;
        count++;  queue.offer(new int[] { y, x });
        while (!queue.isEmpty()) {
          int search_y = queue.peek()[0], search_x = queue.poll()[1];
          for (int types = 0; types < 4; types++) {
            int dy = search_y + dir[types][0], dx = search_x + dir[types][1];
            if (inBoard(dy, dx, length) && !isvisited[dy][dx]) {
              isvisited[dy][dx] = true;
              if (board[dy][dx] > height) queue.offer(new int[] { dy, dx });
            }
          }
        }
      }
    }
    return count;
  }

  static boolean inBoard(int y, int x, int length) {
    return (!(y < 0 || x < 0 || y >= length || x >= length));
  }
}