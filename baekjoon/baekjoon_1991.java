import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static List<Character>[] graph;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		graph = new ArrayList[N];
		for(int i=0; i<N; i++) graph[i] = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			char parent = st.nextToken().charAt(0);
			parent -= 'A';
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			
			graph[parent].add(left);
			graph[parent].add(right);			
		}
		
		prefixDFS('A');
		System.out.println(sb);
		sb.setLength(0);
		infixDFS('A');
		System.out.println(sb);
		sb.setLength(0);
		postfixDFS('A');
		System.out.println(sb);
		
	}
	
	static void prefixDFS(char parent) {
		sb.append(parent);
		
		if('A'<= graph[parent-'A'].get(0) && graph[parent-'A'].get(0) <= 'Z')
			prefixDFS(graph[parent-'A'].get(0));
		if('A'<= graph[parent-'A'].get(1) && graph[parent-'A'].get(1) <= 'Z')
			prefixDFS(graph[parent-'A'].get(1));
	}
	static void infixDFS(char parent) {
		
		if('A'<= graph[parent-'A'].get(0) && graph[parent-'A'].get(0) <= 'Z')
			infixDFS(graph[parent-'A'].get(0));
		sb.append(parent);
		if('A'<= graph[parent-'A'].get(1) && graph[parent-'A'].get(1) <= 'Z')
			infixDFS(graph[parent-'A'].get(1));
	}
	static void postfixDFS(char parent) {
		
		if('A'<= graph[parent-'A'].get(0) && graph[parent-'A'].get(0) <= 'Z')
			postfixDFS(graph[parent-'A'].get(0));
		if('A'<= graph[parent-'A'].get(1) && graph[parent-'A'].get(1) <= 'Z')
			postfixDFS(graph[parent-'A'].get(1));
		sb.append(parent);
	}
}