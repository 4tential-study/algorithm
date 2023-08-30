import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2343 {
	static BufferedReader in;
	static StringTokenizer st;
	static int n;
	static int m;
	static boolean[] selected;
	static int[] array;
	static int min = 999999999;
	
	public static void main(String[] args) throws IOException{
		in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs =in.readLine().split(" ");
		n = Integer.parseInt(inputs[0]);
		m = Integer.parseInt(inputs[1]);
		st = new StringTokenizer(in.readLine());
	
		array = new int[n];
		for(int i=0 ; i < n ; i++) {
			array[i]=Integer.parseInt(st.nextToken());
		}
		
		int len = n-1;
		boolean[] visited = new boolean[len];
		comb(0,0,new int[m-1],visited);
		
		System.out.println(min);
	}
	
	public static void comb(int prev, int level, int[] temp, boolean[] visited) { 
		if (level == m-1) {
			int[] list = new int[m];
			selected = new boolean[n];
			for(int i=0 ; i < temp.length ; i++) {
				int end = temp[i];
				int sum = 0;
			
				for(int j=0 ; j <= end ; j++) {
					
					if(!selected[j]) {
						selected[j] = true;
						sum+=array[j];
					}	
					
				}
				list[i]=sum;
			}
			int s = 0;
			for(int j=0 ; j < selected.length ; j++) {
				if(!selected[j]) {
					selected[j] = true;
					s+=array[j];
				}					
			}
		
			list[m-1]=s;
				
			int tMax = 0;
			for(Integer each : list ) {
				tMax = Math.max(each, tMax);
			}				
			min = Math.min(min, tMax);
			return;
		}
	
		for(int i=prev ; i < n-1 ; i++) {
			if(!visited[i]) {
				visited[i] = true;
				temp[level] = i;
				prev = i;
				comb(prev, level+1, temp, visited);
				visited[i] = false;
			}
 		}
	}
}
