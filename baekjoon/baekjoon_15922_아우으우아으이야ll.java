package Algorithm.boj.day0908;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_15922 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int arr[][] = new int[N][2];

		int left = 0;
		int right = 0;
		
		int result = 0;
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			
			if(arr[right][1] < arr[i][0]) {
				result += arr[right][1] - arr[left][0];
				left = i;
			}
			
			if(arr[right][1] < arr[i][1]) right = i;			
			
		}
		result += arr[right][1] - arr[left][0];
		
		System.out.println(result);
	}
}
