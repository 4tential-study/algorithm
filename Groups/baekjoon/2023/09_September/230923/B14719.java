import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B14719 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = in.readLine().split(" ");
		int width = Integer.parseInt(input[1]);
		
		int[] heights = new int[width];
		
		input = in.readLine().split(" ");
		int max_heights = Integer.MIN_VALUE, max_index = 0;
		for(int i = 0; i < width; i++) {
			heights[i] = Integer.parseInt(input[i]);
			if(max_heights < heights[i]) {
				max_heights = heights[i];	max_index = i;
			}
		}
		int answer = 0;
		for(int h = max_heights; h > 0; h--) {
			int search = h, search_index = max_index;
			for(int left = max_index-1; left >= 0; left--) {
				if(heights[left] >= search) {
					answer += (Math.abs(search_index - left) - 1);
					search_index = left;
				}
			}
			search_index = max_index;
			for(int right = max_index+1; right < width; right++) {
				if(heights[right] >= search) {
					answer += (Math.abs(right - search_index) - 1);
					search_index = right;
				}
			}
		}
		
		System.out.println(answer);
	}
}