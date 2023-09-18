import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    static class stat implements Comparable<stat>{
      int alp, cop;
      int cost;

      public stat(int alp, int cop, int cost) {
        this.alp = alp;
        this.cop = cop;
        this.cost = cost;
      }

      @Override
      public int compareTo(Solution.stat o) {
        return this.cost - o.cost;
      }
    }

    // problems 길이 최대 6
    // 알고력, 코딩력, 문제 목록
    public static int solution(int alp, int cop, int[][] problems) {
        int answer = Integer.MAX_VALUE;
        // 알고력과 코딩력을 늘리는 방법
        // 1. 알고리즘 공부 혹은 코딩 공부
        // 각각 알고력, 코딩력을 1 증가. cost = 1

        // 2. 현재 풀 수 있는 문제 중 하나를 풀어 알고력과 코딩력을 높임.

        // solved_problem.add(new int[]{0, 0, 1, 0, 1}); // 알고리즘 공부
        // solved_problem.add(new int[]{0, 0, 0, 1, 1}); // 코딩 공부

        int max_alp = 0, max_cop = 0; // 주어져 있는 문제 중 요구하는 알고력, 코딩력 중 가장 높은 수치를 저장.
        for(int i = 0; i < problems.length; i++){
          int alp_req = problems[i][0]; // 문제를 푸는 데 필요한 알고력
          int cop_req = problems[i][1]; // 문제를 푸는 데 필요한 코딩력
          max_alp = (max_alp < alp_req) ? alp_req : max_alp;
          max_cop = (max_cop < cop_req) ? cop_req : max_cop;
        }

        Queue<stat> queue = new PriorityQueue<>();
        queue.offer(new stat(alp, cop, 0));

        while(!queue.isEmpty()){
          stat current = queue.poll();
          int curr_alp = current.alp;
          int curr_cop = current.cop;
          int curr_cost = current.cost;
          System.out.println("curr_alp : " + curr_alp + "curr_cop : " + curr_cop + "curr_cost : " + curr_cost);
          System.out.println("answer : " + answer);
          if(curr_alp >= max_alp && curr_cop >= max_cop){
            answer = (answer > curr_cost) ? curr_cost : answer;
            break;
          }

          queue.offer(new stat(curr_alp + 1, curr_cop, curr_cost+1));
          queue.offer(new stat(curr_alp, curr_cop + 1, curr_cost+1));

          for(int i = 0; i < problems.length; i++){
            if(curr_alp >= problems[i][0] && curr_cop >= problems[i][1]){
              queue.offer(new stat(curr_alp + problems[i][2], curr_cop + problems[i][3], curr_cost + problems[i][4]));
            }
          }
        }
        return answer;
    }

    public static void main(String[] args) {
      int alp = 10, cop = 10;
      int[][] problems = {{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}};

      System.out.println(solution(alp, cop, problems));
    }
}