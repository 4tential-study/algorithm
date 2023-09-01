import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B4963 {
  // 우측부터 시작해서 시계방향
  static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
  static int[] dx = { 1, 1, 0, -1, -1, -1, 0, 1 };

  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    char[][] board;
    int count;
    while (true) {
      String[] str = in.readLine().split(" ");
      int width = Integer.parseInt(str[0]);
      int height = Integer.parseInt(str[1]);
      if (width == 0 && height == 0)
        break;

      board = new char[height][width];
      count = 0;
      for (int i = 0; i < height; i++) {
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int j = 0; j < width; j++)
          board[i][j] = st.nextToken().charAt(0);
      }

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          if (board[i][j] == '1') { // 처음 가 본 곳이라면 진행.
            count++;  solve(board, i, j);
          }
        }
      }
      sb.append(count).append("\n");
    }
    System.out.print(sb);
  }

  static void solve(char[][] board, int y, int x) {
    board[y][x] = '2';  // 현재 섬에 있는 곳으로 도착했으므로, 값을 바꿔준다.
    for(int type = 0; type < 8; type++){
      int cy = y + dy[type], cx = x + dx[type];
      if(inBoard(board, cy, cx) && board[cy][cx] == '1')  solve(board, cy, cx);
    }
  }

  static boolean inBoard(char[][] board, int y, int x){
    return y >= 0 && y < board.length && x >= 0 && x < board[0].length;
  }
}