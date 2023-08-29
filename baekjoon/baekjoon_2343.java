import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		/*
		 * 이분 탐색 시에 중요한 것은 무엇을 left와 right로 지정할 것인가이다.
		 */
		
		long low = 0;
		long high = 0;
		
		
		st = new StringTokenizer(in.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			low = Math.max(low, arr[i]);
			high += arr[i];
		}
		
		while(low<=high) {
			// 이분 탐색으로 탐색할 것은 블루레이 개수이다.
			// 임의의 녹화 길이 값을 정하고 강의 시간을 조회하며
			// 합산한 값이 녹화 길이 값을 초과할 때마다 블루레이 개수를 증가시킨다.
			// 만약 블루레이 개수가 적다면 임의로 정한 시간이 너무 커 녹화길이가 너무 길다는 것이기 때문에
			// 녹화길이를 줄이고 다시 계산해본다. 반대로 블루레이 개수가 많다면 녹화길이를 늘리고 다시 계산한다.
			
			long mid = (low + high) / 2;
			long cnt=0;
			long sum=0;
			
			for(int i=0; i<N; i++) {
				if(arr[i]+sum > mid) {
					// 동영상 녹화시간을 합산한 값이 mid(블루레이 당 가능한 녹화길이)를 넘어선다면 새로운 블루레이를 사용
					sum = 0;
					cnt++;
				}
				sum += arr[i];
			}
			
			
			if(sum!=0) cnt++; // 만약 sum이 0이 아니라면 영상이 적어도 하나는 들어갔다는 것이고 이는 블루레이를 적어도 1개를 썼다는 것이다. 
			
			if(cnt<=M) {	// 만약 블루레이 개수가 M 이하이라면 녹화 가능시간이 너무 길다는 것이므로 줄인다.
				high = mid-1;
			}else {
				low = mid+1;
			}
			
		}
		
		System.out.println(low);
	}
}