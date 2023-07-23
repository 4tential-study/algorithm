package Algorithm.boj.day0723;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_14888 {
	static int N;
	static int[] A;
	static int MIN=Integer.MAX_VALUE,
				MAX=Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int plus, minus, mult, div;
		
		st = new StringTokenizer(br.readLine());
		plus = Integer.parseInt(st.nextToken());
		minus = Integer.parseInt(st.nextToken());
		mult = Integer.parseInt(st.nextToken());
		div = Integer.parseInt(st.nextToken());
		
		recursion(1, A[0], plus, minus, mult, div);
		
		System.out.println(MAX);
		System.out.println(MIN);
	}
	
	static void recursion(int idx, int sum, int plus, int minus, int mult, int div) {
		if(idx>=N) {
			MIN = Math.min(MIN, sum);
			MAX = Math.max(MAX, sum);
			return;
		}
		
		// 연산자마다 재귀 돌기
		// 덧셈
		if(plus>0) recursion(idx+1, sum+A[idx], plus-1, minus, mult, div);
		// 뺄셈
		if(minus>0) recursion(idx+1, sum-A[idx], plus, minus-1, mult, div);
		// 곱셈
		if(mult>0) recursion(idx+1, sum*A[idx], plus, minus, mult-1, div);
		// 나눗셈
		if(div>0) {
			if(sum<0) {
				sum = -(Math.abs(sum)/A[idx]);
				recursion(idx+1, sum, plus, minus, mult, div-1);
			}else {
				recursion(idx+1, sum/A[idx], plus, minus, mult, div-1);
			}
		}
	}
}
