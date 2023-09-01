import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B1780 {
  static byte[][] board;
  static int[] count = new int[3];
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int size = Integer.parseInt(in.readLine());

    board = new byte[size][size];
    for(int i = 0; i < size; i++) {
      String[] input = in.readLine().split(" ");
      for(int j = 0; j < size; j++) board[i][j] = Byte.parseByte(input[j]);
    }
    paper_count(size, 0, 0);
    for(int i = 0; i < 3; i++)  System.out.println(count[i]);
  }

  static void paper_count(int length, int y, int x){
    byte first = board[y][x];
    for(int i = y; i < y + length; i++){
      for(int j = x; j < x + length; j++){
        if(first != board[i][j]){
          int new_length = length/3;
          for(int new_y = y; new_y < y + length; new_y+=new_length){
            for(int new_x = x; new_x < x + length; new_x += new_length){
              paper_count(new_length, new_y, new_x);
            }
          }
          return;
        }
      }
    }
      count[first+1]++;
  }
}
