package week1002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class baekjoon_1701 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String str = in.readLine(); 
		int max = 0;
		for(int i=0; i<str.length(); i++) {
			max = Math.max(max, KMP(str.substring(i, str.length()).toCharArray()));
		}
		System.out.println(max);
	}
	
	static int KMP( char[] P) {		
		
		int[] pi = new int[P.length];
		int max = 0;
		
		for(int i=1, j=0; i<P.length; i++) {
			while(j>0 && P[i] != P[j]) {
				j = pi[j-1];
			}
			
			if(P[i] == P[j]) {
				pi[i] = ++j;
				max = Math.max(max, pi[i]);
			}else {
				pi[i] = 0;
			}
		}
		
		return max;
	}
}
