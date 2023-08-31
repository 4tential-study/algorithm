package 백준_2343_기타레슨;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_2343_기타레슨 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int M = Integer.parseInt(line[1]);
		String[] line2 = br.readLine().split(" ");
		int[] arr = new int[N];
		int total=0;
		int maximum=0;
		int cnt=0;
		int result=0;
		for (int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(line2[i]);
			total +=arr[i];
			Math.max(maximum, arr[i]); // maximum에 테이프 중 가장 긴 시간을 저장
		}
		int start = total/M; // 시작지점은 (전체 시간 합/블루레이 개수)
		int end = total; // 종료지점은 전체 시간의 합
		while (start<=end) { // 이분탐색 시작
			int mid = (start+end)/2;
			if (mid>=maximum) {
				cnt=1;
				int partial_sum=0;
				for (int num: arr) {
					if (partial_sum+num<=mid) partial_sum+=num;
					else {
						cnt++;
						partial_sum=num;
					}
				}
				if (cnt<=M) {
					end = mid-1;
					result=mid;
				}
				else if (cnt>M) {
					start=mid+1;
				}
			}
			else start=mid+1;
		}
		System.out.println(result);
	}

}
