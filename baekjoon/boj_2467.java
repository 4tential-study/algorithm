import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2467 {
	static BufferedReader in;
	static StringTokenizer st;
	static int n;
	static int[] array;
	public static void main(String[] args) throws IOException{
		in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());
		array = new int[n];
		st = new StringTokenizer(in.readLine());
		
		for(int i=0 ; i < n ; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		
	}
}
