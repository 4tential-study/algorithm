package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class baekjoon_1701_cubeditor {

	//kmp 알고리즘 코드 참고!
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int len=line.length();
		int ans=0;
		
		//모든 부분문자열 탐색
		for (int i=0;i<len;i++) {
			String subStr = line.substring(i, len);
			ans=Math.max(ans, getMaxPartialLength(subStr));
		}
		System.out.print(ans);
	}
	
	// kmp 알고리즘 사용
	// 해당 문자열 내 존재하는 부분 문자열 중 접두사와 접미사가 같은 문자열의 최대 길이 구하기
	// 원래는 pi 배열을 리턴하여 문자열 탐색에 kmp 알고리즘으로 사용됨
	public static int getMaxPartialLength(String str) {
		int j=0; // 접두사 비교 문자열 인덱스
		int n = str.length();
		int max=0;
		int pi[] = new int[n];
		
		for (int i=1;i<n;i++) { // i : 접미사 비교 문자열 인덱스
			while ( j>0 && str.charAt(i)!=str.charAt(j)) {
				j=pi[j-1];
			}
			
			if (str.charAt(i)==str.charAt(j)) {
				pi[i]= ++j;
				max=Math.max(max, pi[i]);
			}
		}
		return max;
	}
}
