import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;

public class boj_1325 {
	static BufferedReader br;
	static StringTokenizer st;
	static int[] board; //노드개수저장
	static boolean[] visited;
	static ArrayList<Integer>[] links; //연결 저장
	static StringBuilder sb;
	static int count ;
	
	public static void main(String[] args) throws IOException {
		sb=new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int m = Integer.parseInt(str[1]);
		links = new ArrayList[n+1];  
		visited = new boolean[n+1];
		board = new int[n+1];
		
		for(int i=0 ; i < links.length ; i++) {
			links[i] = new ArrayList<>();
		}
		for(int i=0 ; i < m ; i++) {
			st= new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			links[a].add(b);	
		}		
		
		for(int i=1 ; i <= n ; i++) {
			count = 0;
			visited = new boolean[n+1];
			bfs(i);		
		}
		
		int max=0;
		for(int i=1 ; i <= n ; i++) {
			if(max < board[i]) max = board[i];
		}
		
		for(int i=1 ; i <= n ; i++) {
			if(max == board[i]) {
				sb.append(i+" ");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	public static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		visited[start] = true;
		while(!queue.isEmpty()) {
			Integer pop = queue.poll();
			for(Integer node : links[pop]) {
				if(!visited[node]) {
					queue.add(node);		
					visited[node] = true;
					board[node]++;					
				}
			}
		}
	}

}
