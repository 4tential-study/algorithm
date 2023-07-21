package Algorithm.boj.day0721;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_2579 {
	
	static int N;
	static int[] stairs;
	static int result;
	static int[] DP;
	static boolean[] v;
	
	// 디버깅용
	static int cnt, cnt2;
	
	// 아이디어 (실패한 코드 -> 시간 초과)
	// DP와 visited 맵을 활용한 완전탐색
	// 완전탐색을 통해 i번째까지 최댓값을 구했다면
	// i 이전으로 돌아와서 다시 i까지 나아가는 것을 방지
	// 대신 i번째까지의 DP 값을 쓰게 함.
	// (cnt2로 확인해보니 둘다 연산 횟수가 같음. 구현 실패)
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		stairs = new int[N];
		DP = new int[N];
		v = new boolean[N];
		result = Integer.MIN_VALUE;
		
		for (int i=0; i<N; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		
		// 디버깅용
		cnt=0;
		cnt2=0;
		
		recursion(0, 0, 1);
		recursion(1, 0, 1);
		
		System.out.println(result);
		System.out.println("cnt2 : "+cnt2);
		
	}
	
//	- 현재 인덱스 (idx)
//	- 현재까지의 합 (sum)
//	- 지금까지 연속으로 건너온 계단 수 (cont)
	static void recursion(int idx, int sum, int cont) {
		if(idx>=N) {
			return;
		}
		
		cnt2++;
//		System.out.println("\t\tcnt2 : "+ cnt2);
		
		sum += stairs[idx];
//		System.out.println("\t idx : "+idx+", sum : "+sum+", cont : "+cont);
		
		if(idx == N-1) {
//			System.out.println("cnt : "+ ++cnt + " "+ sum);
			result = Math.max(sum, result);
			return;
		}
		
		
		/* DP[i] = 현재까지 계산한 값
		 * if (v[0]부터 v[i-1] 까지 true라면 DP[i]는 최댓값 확정) {
			v[i] = true
			DP[i] 값 쓰기
		}
		else{
		 재귀 연산
		 }*/
		
		if(DP[idx]< sum) {
			DP[idx] = sum;
		}
		
		if (idx==0) {
			DP[idx] = stairs[idx];
			v[idx] = true;
		}else if(v[idx-1]==true) {
			v[idx] = true;
		}
		
		// 다음 계단으로 올라가기
		if(cont<2 && idx!=N-3) {
			// 이미 가봤던 계단이라면 다시 안 간다
			if(v[idx+1]==false) {
				
				recursion(idx+1, sum, cont+1);
			}
		}
		
		// 다다음 계단으로 올라가기
		if(idx!=N-2) {
			// 이미 가봤던 계단이라면 다시 안 간다
			if(v[idx+2]==false) {
				recursion(idx+2, sum, 1);
			}
		}
		
	}
}
