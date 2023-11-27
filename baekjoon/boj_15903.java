import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_15903 {
	static BufferedReader in;
	
	public static void main(String[] args) throws IOException{
		in = new BufferedReader(new InputStreamReader(System.in));
		String[] str =in.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int m = Integer.parseInt(str[1]);
		String[] cards =in.readLine().split(" ");
		long[] array = Arrays.stream(cards).mapToLong(Long::parseLong).toArray();
		
		for(int i=0 ; i < m ; i++) {
			Arrays.sort(array); // asc 
			long sum = array[0]+array[1];
			array[0] = sum;
			array[1] = sum;
		}
		
		long total = Arrays.stream(array).sum();
		System.out.println(total);
	}
}