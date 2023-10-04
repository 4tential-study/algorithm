import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B1701 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String str = in.readLine();
		int answer = 0;
		for(int i = 0; i < str.length(); i++) {
			answer = Math.max(solve(str.substring(i, str.length())), answer);
		}
		
		System.out.println(answer);
	}
	
	static int solve(String str) {
		int answer = 0;
		int[] pi = new int[str.length()];
		for(int i = 1, j = 0; i < str.length(); i++) {
			while(j > 0 && str.charAt(i) != str.charAt(j)) {
				j = pi[j-1];
			}
			if(str.charAt(i) == str.charAt(j)) {
				pi[i] = ++j;
				answer = Math.max(answer, pi[i]);
			}
			else pi[i] = 0;
		}
		
		return answer;
	}
}