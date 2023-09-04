package 백준_2003_수들의합2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_2003_수들의합2 {
	public static int N,M,sum,result,start,end;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		String[] line2 = br.readLine().split(" ");
		int[] arr = new int[N];
		for (int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(line2[i]);
		}
		for (int cnt=1;cnt<=N;cnt++) {
			sum=0;
			start=0;
			for (int i=0;i<cnt;i++) {
				sum+=arr[i];
			}
			if (sum==M) result++;
			end=cnt;
			while(end!=N) {
				sum-=arr[start];
				sum+=arr[end];
				start++;
				end++;
				if (sum==M) result++;
			}
		}
		System.out.println(result);
	}
}
