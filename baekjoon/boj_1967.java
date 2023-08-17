import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_1967 {
	static BufferedReader br;
	static StringTokenizer st;
	
//	12
//	1 2 3
//	1 3 2
//	2 4 5
//	3 5 11
//	3 6 9
//	4 7 1
//	4 8 7
//	5 9 15
//	5 10 4
//	6 11 6
//	6 12 10
	static int n;
	static ArrayList<int[]>[] adjList;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		adjList = new ArrayList[n+1];
		for(int i=1 ; i <= n ; i++) {
			adjList[i] = new ArrayList<int[]>();
		}
		
		for(int i=1 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[p].add(new int[] {c,w});
		}
		
		//리프노드부터 리프노드까지..
		for(int i=1 ; i <= n ; i++ ) {
			if (adjList[i].isEmpty()) 
		}
	}

}
