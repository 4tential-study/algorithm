import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_5650_핀볼_게임 {
  // 핀볼이 각 블럭을 조우하였을 때, 조우하기 직전의 이동 방향에서 어떤 이동 방향으로 바뀌는지를 표현
  private static int[][] blocks = {{0, 0, 0, 0},
  {1, 3, 0, 2},  {3, 0, 1, 2},
  {2, 0, 3, 1},  {1, 2, 3, 0},
  {1, 0, 3, 2}};

  // 0 , 1, 2, 3
  private static byte[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(in.readLine().trim());
    for(int test_case = 1; test_case <= T; test_case++){
      sb.append("#").append(test_case).append(" ");
      int length = Integer.parseInt(in.readLine()); // 핀볼 게임판의 크기
      int[][] board = new int[length][length];
      for(int y = 0; y < length; y++){
        String[] input = in.readLine().split(" ");
        for(int x = 0; x < length; x++){
          board[y][x] = Integer.parseInt(input[x]);
          if(board[y][x] >= 6) continue; // 웜홀일 경우
        }
      }
    }
    System.out.println(sb);
  }
}
