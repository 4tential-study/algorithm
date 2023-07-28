package Algorithm.boj.day0728;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_14501 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] T = new int[N];
		int[] P = new int[N];
		int[] DP = new int[N];
		int result=0;
		
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		
		// DP[i]에 저장된 값은 이전에 저장해놓은 값이다.
		// 5일간의 상담으로 번 20원 : 루트 A
		// 1일 10원 + 1일 20원 + 2일 15원, 합산 45원 : 루트 B
		// 비교해야하는 것은 루트 A의 합산과 루트 B의 합산이다.
		
		
		
		for(int i=0; i<N; i++) {
			if(i+T[i]-1 < N) {
				
				DP[i+T[i]-1] = Math.max( DP[i+T[i]-1], P[i]+result );
			}
			result = Math.max(result, DP[i]);
		}
		System.out.println(result);
		
		// 드디어 DP 문제를 풀었습니다
	}
}
