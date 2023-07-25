package Algorithm.boj.day0723; 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


// 두 자연수에 해당하는 후보값들은 최소공배수를 최대공약수로 나눈 값의 약수와 같다
// 앞에서부터 i번째 값과 뒤에서부터 i번째 값은 서로소일 때 문제의 목표인 두 자연수에 해당한다
// 두 자연수의 차가 적을수록 합이 작으므로 i값이 서로 교차할 때의 두 자연수를 출력한다

public class Baekjoon_2436 {
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int GCD = Integer.parseInt(st.nextToken());
		int LCM = Integer.parseInt(st.nextToken());
		int LG = LCM/GCD;	
		
		ArrayList<Integer> CMofLG = new ArrayList<Integer>();

		for(int i=1; i<=LG; i++) {
			if(LG%i==0) {
				CMofLG.add(i);
			}
		}
		
		int size = CMofLG.size();
		int rx = 1;
		int ry = LG;
		
		for(int i=0; i<size/2+1; i++) {
			int x = CMofLG.get(i);
			int y = CMofLG.get(size-1-i);
			int g = getGCD(x,y);
			
			
			if(g==1 && x*y*GCD == LCM) {
				rx = x;
				ry = y;
			}
		}
		System.out.println(rx+" "+ry);
	}
	
	static int getGCD (int a, int b) {
		if (b ==0)
			return a;
		else
			return getGCD(b,a%b);
	} //재귀함수를 활용하는 방법
}
