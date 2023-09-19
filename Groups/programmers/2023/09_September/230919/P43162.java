class Solution {
    public static int solution(int n, int[][] computers) {
        int answer = 0;

        boolean[] visited = new boolean[n];

        for(int i = 0; i < n; i++){
          if(visited[i]) continue;
          answer++;
          dfs(visited, computers, i);
        }
        return answer;
    }

    static void dfs(boolean[] visited, int[][] computers, int idx){
      if(visited[idx]) return;

      visited[idx] = true;

      for(int i = 0; i < visited.length; i++){
        if(computers[idx][i] == 1 && idx != i){
          dfs(visited, computers, i);
        }
      }
    }
}