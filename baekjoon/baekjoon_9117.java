package week1106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// 브루트 포스 아이디어 완성은 블로그 참고.
// : 시간 초과를 해결하기 위해서는 set 필요
// https://sohee-dev.tistory.com/60
public class baekjoon_9177  {
	
	static String firstWord, secondWord, lastWord;
	static int tc;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		
		for(tc=0; tc<T; tc++) {
			sb.append("Data set "+(tc+1)+": ");
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			firstWord = st.nextToken();
			secondWord = st.nextToken();
			lastWord = st.nextToken();

			int len1 = firstWord.length();
			int len2 = secondWord.length();
			int len3 = lastWord.length();
			Set<Character> set = new HashSet<>();
			
			for(int i=0; i<Math.max(len1, len2); i++) {
				if(i<len1) set.add(firstWord.charAt(i));
				if(i<len2) set.add(secondWord.charAt(i));
			}
			boolean flag = true;
			for(int i=0; i<len3; i++) {
				if(!set.contains(lastWord.charAt(i))) {
					flag=false;
					break;
				}
			}
			if(!flag) {
				sb.append("no\n");
				continue;
			}
			
			if(check(0, 0)) {
				sb.append("yes\n");
			}else sb.append("no\n");
		}
		System.out.println(sb.toString());
	}
	
	static boolean check(int firstStep, int secondStep) {
		int step = firstStep + secondStep;
		
		if(step == lastWord.length()) {
			return true;
		}
		
		char c = lastWord.charAt(step);
		
		if(firstWord.length()>firstStep && firstWord.charAt(firstStep) == c) {
			if(check(firstStep+1, secondStep)) return true; 
		}
		
		if(secondWord.length()>secondStep && secondWord.charAt(secondStep) == c) {
			if(check(firstStep, secondStep+1)) return true;
		}
		
		return false;
	}
}