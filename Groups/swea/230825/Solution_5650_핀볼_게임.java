import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution_5650_핀볼_게임 {
  // 핀볼이 각 블럭을 조우하였을 때, 조우하기 직전의 이동 방향에서 어떤 이동 방향으로 바뀌는지를 표현
  private static int[][] blocks = {{0, 0, 0, 0},
  {1, 3, 0, 2},  {3, 0, 1, 2},
  {2, 0, 3, 1},  {1, 2, 3, 0},
  {1, 0, 3, 2}};

  // 0, 1, 2, 3
  private static byte[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우
  private static List<int[]>[] teleport = new ArrayList[5];
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(in.readLine().trim());
    for(int test_case = 1; test_case <= T; test_case++){
      sb.append("#").append(test_case).append(" ");
      
      for(List<int[]> list : teleport) list = new ArrayList<>();
      int length = Integer.parseInt(in.readLine()); // 핀볼 게임판의 크기
      int[][] board = new int[length][length];
      for(int y = 0; y < length; y++){
        String[] input = in.readLine().split(" ");
        for(int x = 0; x < length; x++){
          board[y][x] = Integer.parseInt(input[x]);
          if(board[y][x] >= 6) { // 웜홀일 경우
            int hole_num = board[y][x] - 6;
            teleport[hole_num].add(new int[] {y, x});
          }
        }
      }
      int result = Integer.MIN_VALUE;
      for(int y = 0; y < length; y++){
        for(int x = 0; x < length; x++){
          if(board[y][x] == 0)
            result = Math.max(result, solve(board, y, x));
        }
      }

      sb.append(result).append("\n");
    }
    System.out.println(sb);
  }

  static int solve(int[][] board, int start_y, int start_x){
    int max_count = Integer.MIN_VALUE, length = board.length;

    for(int type = 0; type < 4; type++){
      int count = 0;
      int move_dir = type;
      int dy = start_y, dx = start_x;
      while(true){
        // 블랙홀 혹은 시작 위치로 다시 돌아갔다면 탐색 종료
        if(board[dy][dx] == -1 || (dy == start_y && dx == start_x)) break;

        // 가장자리에 부딪혔다면, 이동 방향을 바꾼다.
        if(move_dir < 2 && (dy == 0 || dy == length-1)){
          move_dir = Math.abs(move_dir - 1);  count++;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 6caf306 (230829 푸는 중...)
          continue;
        }
        else if(move_dir >= 2 && (dx == 0 || dx == length-1)){
          move_dir = 5 - move_dir;  count++;
          continue;
<<<<<<< HEAD
=======
        }
        else if(move_dir >= 2 && (dx == 0 || dx == length-1)){
          move_dir = 5 - move_dir;  count++;
>>>>>>> 0cdf2e2 (230827 - 핀볼 게임 진행 중2)
=======
>>>>>>> 6caf306 (230829 푸는 중...)
        }

        // 블럭에 부딪혔다면, 이동 경로를 변경한다.
        if(board[dy][dx] > 0 && board[dy][dx] <= 5){
          move_dir = blocks[board[dy][dx]][move_dir];
          count++;
<<<<<<< HEAD
<<<<<<< HEAD
          continue;
=======
>>>>>>> 0cdf2e2 (230827 - 핀볼 게임 진행 중2)
=======
          continue;
>>>>>>> 6caf306 (230829 푸는 중...)
        }

        // 현재 위치가 웜홀 자리일 경우, 웜홀을 탄다.
        if(board[dy][dx] >= 6){
          int hole_num = board[dy][dx] - 6;
          if(dy == teleport[hole_num].get(0)[0] && dx == teleport[hole_num].get(0)[1]){
            dy = teleport[hole_num].get(1)[0];  dx = teleport[hole_num].get(1)[1];
          }
          else{
            dy = teleport[hole_num].get(0)[0];  dx = teleport[hole_num].get(0)[1];
          }
        }
      }
      max_count = Math.max(max_count, count);
    }
    return max_count;
  }
}
