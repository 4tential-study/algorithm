import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int L, C;
	static String vowels;
	static StringBuilder sb;
	static String[] input;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		vowels = "aeiou";
		input = new String[C];
		sb = new StringBuilder();
		
		st = new StringTokenizer(in.readLine());
		for(int i=0;i<C;i++) {
			input[i] = st.nextToken();
		}
		
		
		// 풀이
		Arrays.sort(input);
		
		bt(0, "", 0, 0);
		System.out.println(sb);
	}
	
	static void bt(int idx, String result, int vCnt, int cCnt) {
		int size = result.length();
		if(size==L) {
			sb.append(result+"\n");
			return;
		}
		
		// if문 최적화 필요
		for(int i=idx; i<C; i++) {
			if(L-size == 2 - cCnt) {
				// 자음일 때 재귀 호출
				if(!(vowels.contains(input[i]))) {
					bt(i+1, result+input[i], vCnt, cCnt+1);
				}
			}else if(L-size == 1-vCnt) {
				if(vowels.contains(input[i])) {
					bt(i+1, result+input[i], vCnt+1, cCnt);
				}
			}else {
				if(vowels.contains(input[i])) {
					bt(i+1, result+input[i], vCnt+1, cCnt);
				}else {
					bt(i+1, result+input[i], vCnt, cCnt+1);
				}
			}
			
		}
	}
}