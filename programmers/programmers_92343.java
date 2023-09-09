
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
	static boolean visited[]; 
	static List<Integer> answers;
    public int solution(int[] info, int[][] edges) {
		answers = new ArrayList<Integer>();
		visited = new boolean[info.length];
		
		
		
		visited[0] = true;
		bt(info, edges, 1, 0);
		
        return Collections.max(answers);
    }
	static void bt(int[] info, int[][] edges, int sheeps, int wolves) {
		
		if(sheeps > wolves)
			answers.add(sheeps);
		else 
			return;
		
		
		for(int i=0; i<edges.length; i++) {
			if(visited[edges[i][0]] && !visited[edges[i][1]]) {
				visited[edges[i][1]] = true;
				  
				if(info[edges[i][1]]==1)
					bt(info, edges,  sheeps, wolves+1);
				else
					bt(info, edges,  sheeps+1, wolves);
				visited[edges[i][1]] = false;
			}
		}
	}
}