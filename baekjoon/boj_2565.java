import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj_2565 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st ;
	static ArrayList<int[]> lines = new ArrayList<>();
	static int[] length;
	
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		length = new int[501];
		for(int i=0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			lines.add(new int[] {x,y});						
		}
		
		lines.sort(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {	
				return o1[0]-o2[0];
			}	
		});
		
//		int prev = 501;
		int answer = 1;
		for (int k = 0; k < n; k++){
			length[k] = 1;
		    for (int i = 0; i < k; i++){
		        if(lines.get(i)[1] < lines.get(k)[1]){
		            length[k] = Math.max(length[k], length[i] + 1);
		        }
		    }
			answer = Math.max(length[k], answer);
		}

		System.out.println(n-answer);
	}
}
