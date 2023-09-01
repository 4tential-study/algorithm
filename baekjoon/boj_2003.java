import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2003 {
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		int[] array = new int[n+1];
		String[] str=br.readLine().split(" ");
		
		for(int i=1 ; i <= n ; i++) {
			array[i] = Integer.parseInt(str[i-1]);
		}

		for(int i=1 ; i <= n ; i++) {
			array[i] = array[i-1] + array[i];			
		}
		
		
		int left = 0;
		int right = 0;
		int count = 0;
		while( right <= n && left <= n) { //		
			if(array[right] - array[left] == m ) { 
				left++;
				count++;
			}else if(array[right]-array[left] > m){	
				left++;
				continue;
			} else {				
				right++;
			}
		}
		
		System.out.println(count);
	}
}
