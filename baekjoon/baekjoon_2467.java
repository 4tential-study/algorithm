import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		Integer[] arr = new Integer[N];
//		int[] arr2 = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		
		int A=0, B=0;
		int min = Integer.MAX_VALUE;
		
		// 야매 풀이
//		Arrays.sort(arr, new Comparator<Integer>() {
//			public int compare(Integer o1, Integer o2) {
//				return Integer.compare(Math.abs(o1), Math.abs(o2));
//			}
//		});
//		for(int i=0; i<N-1; i++) {
//			if(min > Math.abs(arr[i]+arr[i+1])) {
//				min = Math.abs(arr[i]+arr[i+1]);
//				A = arr[i];
//				B = arr[i+1];
//			}
//		}
		
		// 다른 사람 코드 보고 배운
		// 이분 탐색 풀이
		int start = 0;
		int end = N-1;
		int sPoint = start;
		int ePoint = end;
		
		while(start<end) {
			int sum = arr[start] + arr[end];
			int absSum = Math.abs(sum);
			
			if(absSum < min) {
				min = absSum;
				sPoint = start;
				ePoint = end; 
			}
			
			if(min == 0) break;
			
			if(sum < 0) {	// 0보다 작다면 더 커야하므로 start값을 증가
				start++;
			}
			else {
				end--;
			}
		}
		
		A = arr[sPoint];
		B = arr[ePoint];
		
		if(A<B) System.out.println(A+" "+B);
		else  System.out.println(B+" "+A);
	}
}