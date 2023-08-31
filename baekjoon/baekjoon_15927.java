import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String str = in.readLine();
		int result = -1;
		int left=0, right=0, mid=-1;
		
		if(str.length()%2==0) {
			left = str.length()/2 -1;
			right = left +1;
		}else {
			mid = str.length()/2;
			left = mid-1;
			right = mid+1;
		}
		
		boolean isSame = true;
		boolean isDecal = true;
		for(int i=1; i<str.length(); i++) {
			if(str.charAt(i) != str.charAt(i-1)) {
				isSame = false;
			}
			
			if(i>=right) {
				if(str.charAt(left)!=str.charAt(right)) {
					isDecal = false;
				}
				
				right++;
				left--;
			}
			
		}
		
		if(isDecal) {
			if(!isSame) {
				result = str.length()-1;
			}			
		}else result = str.length();
		System.out.println(result);
	}
}