class Solution {
    static int N, COMPUTERS[][];
	static boolean[] visited;
	
	public int solution(int n, int[][] computers) {
        int answer = 0;
        
        visited = new boolean[n];
       N = n;
       COMPUTERS = computers;
        
        for(int i=0; i<n; i++) {
        	if(!visited[i]) {
        		dfs(i);
        		answer++;
        	}
        }
        
        
        return answer;
    }
	
	static void dfs(int idx) {
		visited[idx] = true;
		
		for(int i=0; i<N; i++) {
			if(i==idx) continue;
			
			if(!visited[i] && COMPUTERS[idx][i]==1) {
				dfs(i);
			}
		}
	}
}