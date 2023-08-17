import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main  {
	
	static int N, result;
	static List<int[]>[] graph;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringTokenizer st;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		graph = new ArrayList[N+1];
		for(int i=1; i<=N; i++) graph[i] = new ArrayList<>();
		
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[parent].add(new int[] {child, weight});
		}
		
		dfs(1);
		
		System.out.println(result);
		
	}
	
	static int dfs(int parent) {
		int max = 0;
		int a = 0;
		int b = 0;
		for(int[] next : graph[parent]) {
			int child = next[0];
			int weight = next[1];
			
			int nodeWeight = dfs(child)+weight;
			
			max = Math.max(max, nodeWeight);
			
			if(nodeWeight > a || nodeWeight > b) {
				if(a>b) {
					b = a;
					a = nodeWeight;
				}else {
					a = b;
					b = nodeWeight;
				}
			}
		}
		result = Math.max(result, a+b);
		
		return max;
	}
	
}