import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B2096 {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int columns = Integer.parseInt(in.readLine());

    int[][] min_DP = new int[columns+1][3];
    int[][] max_DP = new int[columns+1][3];

    for(int i = 1; i <= columns; i++){
      String[] input = in.readLine().split(" ");
      int[] cost = new int[3];

      for(int j = 0; j < 3; j++)
        cost[j] = Integer.parseInt(input[j]);
      
      for(int j = 0; j < 3; j++){
        switch(j){
          case 0:
            min_DP[i][j] = Math.min(min_DP[i-1][0], min_DP[i-1][1]);
            max_DP[i][j] = Math.max(max_DP[i-1][0], max_DP[i-1][1]);
            break;
          case 1:
            min_DP[i][j] = Math.min(Math.min(min_DP[i-1][0], min_DP[i-1][1]), min_DP[i-1][2]);
            max_DP[i][j] = Math.max(Math.max(max_DP[i-1][0], max_DP[i-1][1]), max_DP[i-1][2]);
            break;
          case 2:
            min_DP[i][j] = Math.min(min_DP[i-1][1], min_DP[i-1][2]);
            max_DP[i][j] = Math.max(max_DP[i-1][1], max_DP[i-1][2]);
            break;
        }
        min_DP[i][j] += cost[j];
        max_DP[i][j] += cost[j];
      }
    }
    int min_result = Math.min(Math.min(min_DP[columns][0], min_DP[columns][1]), min_DP[columns][2]);
    int max_result = Math.max(Math.max(max_DP[columns][0], max_DP[columns][1]), max_DP[columns][2]);

    System.out.println(max_result + " " + min_result);
  }
}
