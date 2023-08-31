import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class boj_15927 {
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		//모두 같은 문자인 경우, -1
		if( allSame(str) ) {
			System.out.println(-1);
			return;
		}
		
		for(int i=str.length() ; i > 1 ; i-- ) {		
			String line = str.substring(0,i); //부분문자열을 생성 
			if(!isPalindrome(line)) { //해당 문자열이 팰린드롬인지 체크
				System.out.println(line.length());
				return;
			}
		}
		System.out.println(-1);
		return;
	}
	
	public static boolean isPalindrome(String line) {
		boolean flag = true;
		for(int i=0 ; i < line.length()/2 ; i++) {
			int t = line.length()-1;	
			if(line.charAt(i) != line.charAt(t-i)) {
				return flag = false;
			}
		}
		return flag;
	}
	
	public static boolean allSame(String str) {
		char x = str.charAt(0);
		for(int i=1 ; i < str.length() ; i++) {
			if(x != str.charAt(i)) return false;
		}
		return true;
	}
}
