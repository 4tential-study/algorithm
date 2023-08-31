import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 아이디어
	 * - N개 만큼의 크기를 지닌 배열을 만든다.
	 * 	이 배열은 각 인덱스가 슬라이딩 윈도우의 크기를 뜻한다.
	 * 	N은 10000이며 각 요소당 위의 배열을 조회한다.
	 * 즉 최소 시간복잡도는 1*1이며
	 * 최대 시간복잡도는 10000 * 10000 
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int result=0;
		
		long windows[] = new long[10001];
		int arr[] = new int[N+1];
		
		st = new StringTokenizer(in.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			boolean flag=false;
			for(int j=1; j<=N; j++) {
				windows[j] += arr[i];
				if(i-j>=1) windows[j] -= arr[i-j];
				if(windows[j]==M) flag=true;
			}
			if(flag) result++;
		}
		System.out.println(result);
	}
}