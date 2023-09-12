import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

public class boj_2011 {
	static int len;
	static int cnt;
	static int[] list = new int[5001];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BigInteger n = new BigInteger(br.readLine());
		String str = String.valueOf(n);
		len = str.length();
		System.out.println(len);
		if(str.contains("0")) {
			System.out.println(-1);
			return;
		}
		permutation(str, 0, 0, list);
		System.out.println(cnt % 1000000);
	}
	
	public static void permutation(String str, int sum, int level, int[] list) {
		if(level <= len) {
			if(sum == len) {
				int prev = 0;
				boolean isValid = true;
				for(int idx = 0; idx < len ; idx++ ) {
					int i = prev;
					int endIdx = i + list[idx];
					if(endIdx > len) continue;
					String substring = str.substring(i, endIdx);
					
					BigInteger sub = new BigInteger(substring);
					prev = endIdx;
					if(sub.compareTo(BigInteger.valueOf(26)) == 1) {
						isValid = false;
						break;
					}
				}
				if(isValid) cnt++;
				return;
			}
		}
		if(level > len) {
			return;
		}
		
		for(int i=1 ; i <= 2 ; i++) { //1,2
			sum+= i;
			list[level] = i;
			permutation(str, sum, level+1 ,list );
			sum-= i;
			
		}
	}
}
//메모리초과
//indexOutOfRange() 해결하기
