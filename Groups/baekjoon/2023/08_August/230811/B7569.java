import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class B7569 {
  static int M, N, H; // 가로, 세로, 높이

  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String[] inputs = in.readLine().split(" ");
    M = Integer.parseInt(inputs[0]);
    N = Integer.parseInt(inputs[1]);
    H = Integer.parseInt(inputs[2]);

    int[][][] board = new int[N][M][H];
    Queue<int[]> queue = new ArrayDeque<>();
    boolean flag = false;
    for (int h = 0; h < H; h++) {
      for (int n = 0; n < N; n++) {
        inputs = in.readLine().split(" ");
        for (int m = 0; m < M; m++) {
          int value = Integer.parseInt(inputs[m]);
          board[n][m][h] = value;
          if (value == 1) queue.offer(new int[] { n, m, h });
          else if (!flag && value == 0) flag = true;
        }
      }
    }

    if (!flag) { System.out.println(0); return; }
    // 상, 하, 좌, 우, 위, 아래 (n, m, h)
    int[][] dir = { { 1, 0, 0 }, { -1, 0, 0 }, { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, 1 }, { 0, 0, -1 } };
    int count = 0;
    while (!queue.isEmpty()) {
      int current_size = queue.size();
      for (int i = 0; i < current_size; i++) {
        int[] current = queue.poll();
        for (int types = 0; types < 6; types++) {
          int dy = current[0] + dir[types][0], dx = current[1] + dir[types][1];
          int dz = current[2] + dir[types][2];
          if(inBoard(dy, dx, dz) && board[dy][dx][dz] == 0){
            board[dy][dx][dz] = 1;  queue.offer(new int[] {dy, dx, dz});
          }
        }
      }
      if (!queue.isEmpty()) count++;
    }

    // 아직 익지 않은 토마토가 있다면 -1 출력 후 main 메서드 종료
    for(int[][] heights : board){
      for(int[] rows : heights){
        for(int cell : rows)
          if(cell == 0){
              System.out.println(-1);
              return;                   
            }
        }
    }

    System.out.println(count);
  }

  static boolean inBoard(int y, int x, int z) {
    return (y >= 0 && x >= 0 && z >= 0 && y < N && x < M && z < H);
  }
}