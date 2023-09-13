package Algorithm.boj.day0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon_16510 {
	
	static int N, M, preQueue[];
	static StringBuilder sb = new StringBuilder(); 
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		preQueue = new int[N];
		
		st = new StringTokenizer(in.readLine());
		for(int i=0; i<N; i++) {
			preQueue[i] = Integer.parseInt(st.nextToken());
			if(i!=0) preQueue[i] += preQueue[i-1];
		}
		
		for(int i=0; i<M; i++) {
			int canWorkTime = Integer.parseInt(in.readLine());
			
			sb.append(search(canWorkTime)).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	static int search(int canWorkTime) {
		int left = 0;
		int right = N-1;
		
		int candi = 0;
		
		while(left <= right) {
			int mid = (left+right)/2;
			if(canWorkTime == preQueue[mid]) {
				candi = mid+1;
				break;
			}
			if(preQueue[mid] < canWorkTime) {
				left = mid+1;
				candi = mid+1;
			}else {
				right = mid-1;
			}
		}
		
		return candi;
	}
}
