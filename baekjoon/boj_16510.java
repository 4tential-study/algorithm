import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_16510 {
	static BufferedReader in;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		in = new BufferedReader(new InputStreamReader(System.in));
		String[] inp = in.readLine().split(" ");
		
		int n = Integer.parseInt(inp[0]);
		int m = Integer.parseInt(inp[1]);
		int[] task = new int[n];
		int[] time= new int[m];
		st = new StringTokenizer(in.readLine());
		for(int i=0 ; i < n ; i++) {
			task[i] = Integer.parseInt(st.nextToken());
		}
		//누적합
		for(int i=1 ; i < n ; i++) {
			task[i] += task[i-1];
		}
		
		for(int i=0 ; i < m ; i++) {
			time[i] = Integer.parseInt(in.readLine());
		}
		
		//일이 한개이고, 일하는시간이 걸리는 시간보다 더 이른 경우
		if (m == 1 && task[0] > time[0]) {
			System.out.println(0);
			return;
		}
		
		//이분탐색
		for(int i=0 ; i < m ; i++) {
			int ans = Arrays.binarySearch(task, time[i]);
			
			if(ans<0) {
				ans+=1;
				ans*=-1;
				System.out.println(ans);
			} else System.out.println(ans+1);
		}
		
	}

}
