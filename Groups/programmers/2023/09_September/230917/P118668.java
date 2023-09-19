import java.util.Arrays;

class Solution {
  public static int solution(int alp, int cop, int[][] problems) {
      int max_alp = 0, max_cop = 0;
      for(int i = 0; i < problems.length; i++){
        max_alp = (max_alp < problems[i][0]) ? problems[i][0] : max_alp;
        max_cop = (max_cop < problems[i][1]) ? problems[i][1] : max_cop;
      }

      if(max_alp <= alp && max_cop <= cop)  return 0;

      int[][] DP = new int[max_alp+1][max_cop+1];
      for(int i = 0; i <= max_alp; i++)
        Arrays.fill(DP[i], Integer.MAX_VALUE);
      

      DP[alp][cop] = 0;
      for(int y = alp; y <= max_alp; y++){
        for(int x = cop; x <= max_cop; x++){
          if(y == max_alp && x == max_cop) break;
          if(x+1 <= max_cop) DP[y][x+1] = Math.min(DP[y][x] + 1, DP[y][x+1]);
          if(y+1 <= max_alp) DP[y+1][x] = Math.min(DP[y][x] + 1, DP[y+1][x]);

          for(int idx = 0; idx < problems.length; idx++){
            if(y >= problems[idx][0] && x >= problems[idx][1]){
              int curr_alp = y + problems[idx][2];
              int curr_cop = x + problems[idx][3];
              curr_alp = (curr_alp > max_alp) ? max_alp : curr_alp;
              curr_cop = (curr_cop > max_cop) ? max_cop : curr_cop;
              DP[curr_alp][curr_cop] = Math.min(DP[y][x] + problems[idx][4], DP[curr_alp][curr_cop]);
            }
          }
        }
      }

      return DP[max_alp][max_cop];
  }

  // 0, 0, [[0, 3, 1, 0, 0], [5, 0, 0, 8, 1], [0, 10, 0, 0, 0]] 
  // public static void main(String[] args) {
  //   int alp = 0, cop = 0;
  //   int[][] problems = {{0, 3, 1, 0, 0}, {5, 0, 0, 8, 1}, {0, 10, 0, 0, 0}};

  //   System.out.println(solution(alp, cop, problems));
  // }
}