package baekjoon;
import java.util.*;
import java.io.*;


public class boj_14225 {
	static ArrayList<Integer> list = new ArrayList<>();
	static BufferedReader br ;
	static boolean[] visited;
	static int N;
	
	public static void main(String[] args) throws IOException {		// TODO Auto-generated method stub
		br= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sum = 0;
		for( int i = 0 ; i < N ; i++) {
			Integer element = Integer.parseInt(st.nextToken());
			list.add(element);
			sum += element;
		}
		
		int[] result = new int[sum + 2];
		Collections.sort(list);
		visited = new boolean[list.size()];
		
		
		combination(0, 0, new ArrayList<>(), result);
				
		int start = 1;
		for(int i=1 ; i < result.length ; i++) {
			if(result[i] == 0) {
				System.out.println(i);
				break;
			}
		}
//		while(true) {
//			if (!totalList.contains(i)) {
//				System.out.println(i);
//				break;
//			}
//			i++;
//		}
		
		
	}
	

	
	public static void combination(int idx, int sum, ArrayList<Integer> tempList, int[] result) {
		int len = tempList.size();
		if(idx >= N) {
			return;
		}
		
		for(int i = idx ; i < list.size() ; i++) {
			Integer number = list.get(i);
			if(visited[i]) continue;
			
			visited[i] = true;
			result[sum+number] = 1;
			combination( i+1, sum+number , tempList, result );			
			visited[i] = false;
			
		}
	}
			
	

}
