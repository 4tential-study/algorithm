package Algorithm.boj.day0730;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon_15666 {
	
	static int N;
	static int M;
	static boolean[] isPresent = new boolean[10001];
	static int[] A;
	static int[] arr;
	static int cnt=0;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N];
		arr = new int[M];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (isPresent[num]==false) {
				isPresent[num]=true;
				A[cnt] = num;
				cnt++;
			}
		}
		A = Arrays.copyOfRange(A, 0, cnt);
		Arrays.sort(A);
		
		bf(0, 0);
		System.out.println(sb);
		br.close();
	}
	
	static void bf(int idx, int m) {
		if(m>=M) {
			for(int a : arr) {
				sb.append(a+" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=idx; i<cnt; i++) {
			arr[m] = A[i];
			bf(i, m+1);
		}
	}
}
