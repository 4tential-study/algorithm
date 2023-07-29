import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D3_13428 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			String board = br.readLine();
			int min, max;
			max = min = Integer.parseInt(board);
			int length = board.length();
			if(length >= 2) { 
				for(int i = 0; i < length; i++) {
					for(int j = i+1; j < length; j++) {
						char[] chars = board.toCharArray();
						chars[i] = board.charAt(j);
						chars[j] = board.charAt(i);
						int result = Integer.parseInt(String.valueOf(chars));
						if(result < Math.pow(10, length-1)) continue;
						
						max = (max < result) ? result : max;
						min = (min > result) ? result : min;
					}
				}
			}
			
			System.out.printf("#%d %d %d\n", tc, min, max);
		}
	}
}
