package Algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 수업 시간에 못 풀어서 급하게 풀다가 못 푼 코드입니다...
// 리뷰 스킵해주셔도 좋습니다!

public class day0727_42839 {
	static boolean v[];
	static int result=0;
	static int N;
	static String str;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		N = str.length();
		v = new boolean[N];
		for(int i=0; i<N; i++) {
			recursion(i, str.substring(i,i+1));
		}
		
		System.out.println(result);
	}
	
	static void recursion(int idx, String nowNumber) {
		v[idx] = true;
		
		int num = Integer.parseInt(nowNumber);
		
		for(int i=2; i<Math.sqrt(num); i++) {
			if(num%i==0) {
				v[idx] = false;
				return;
			}
		}
		
		if(num!=1)result++;
		
		for(int i=0; i<N; i++) {
			if(v[i]) continue;
			recursion(i, nowNumber+str.substring(i,i+1));
		}
		
		v[idx] = false;
	}
}