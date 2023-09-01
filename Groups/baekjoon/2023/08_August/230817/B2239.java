import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class B2239 {
  static int[][] board;
  static Set<Integer>[] rows, cols, squares;
  static List<int[]> zero_list;
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    board = new int[9][9];
    rows = new HashSet[9];  cols = new HashSet[9];  squares = new HashSet[9];
    zero_list = new ArrayList<>();
    for(int i = 0; i < 9; i++){
      rows[i] = new HashSet<>();  cols[i] = new HashSet<>();
      squares[i] = new HashSet<>();
    }

    for(int i = 0; i < 9; i++){
      String input = in.readLine();
      for(int j = 0; j < 9; j++) {
        int value = input.charAt(j) - '0';
        board[i][j] = value;
        if(value != 0){
          cols[i].add(value); rows[j].add(value);
          squares[(i/3)*3 + (j/3)].add(value);
        }
        else zero_list.add(new int[]{i, j});
      }
    }
    solve(0);
  }

  static void solve(int index){
    if(index == zero_list.size()){
      String temp = boardToString();
      if(temp.length() == 9*9){
            for(int i = 0; i < 9*9; i+=9) System.out.println(temp.substring(i, i+9)); 
            System.exit(0);
      }
      else return;
    } 

    int current_y = zero_list.get(index)[0];
    int current_x = zero_list.get(index)[1];
    int square_num = (current_y/3)*3 + (current_x/3);

    for(int i = 1; i <= 9; i++){
      if(!rows[current_x].contains(i) && !cols[current_y].contains(i) && !squares[square_num].contains(i)){
        board[current_y][current_x] = i;
        rows[current_x].add(i); cols[current_y].add(i); squares[square_num].add(i);
        solve(index+1);
        board[current_y][current_x] = 0;
        rows[current_x].remove(Integer.valueOf(i));
        cols[current_y].remove(Integer.valueOf(i));
        squares[square_num].remove(Integer.valueOf(i));
      }
    }
  }

  static String boardToString(){
    String temp = "";
    for(int i = 0; i < 9; i++){
      for(int j = 0; j < 9; j++){
        if(board[i][j] == 0)  return temp;
        temp += String.valueOf(board[i][j]);
      }
    }
    return temp;
  }
}
