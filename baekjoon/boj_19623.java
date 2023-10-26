

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_19623 {
	static BufferedReader in;
	static StringTokenizer st;
	static int[] dp;
	
	public static void main(String[] args) throws IOException{
		in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		ArrayList<Meeting> mList = new ArrayList<>();
		int[] ends = new int[n+1];
		
		for(int i=0 ; i < n  ;i++) {
			st = new StringTokenizer(in.readLine());
			int sta = Integer.parseInt(st.nextToken());
			Integer e = Integer.parseInt(st.nextToken());
			int peop = Integer.parseInt(st.nextToken());
			Meeting m = new Meeting(sta,e ,peop ); 
			mList.add(m);
			ends[i+1] = e;
		}
		Arrays.sort(ends);
		Collections.sort(mList);
		dp = new int[n+1];
		dp[0] = 0;
		dp[1]= mList.get(0).people;
	
		for(int i=2; i <= n ; i++) {
				Meeting meeting = mList.get(i-1);
				int lb = binarySearch(ends , meeting.start);
				dp[i] = Math.max(dp[i-1],  (lb >= 0 ? dp[lb] : 0) + meeting.people); //이차원배열로 바꾸기
		
		}		
		System.out.println(dp[n]);
	}
	
	public static class Meeting implements Comparable<Meeting>{
		int start;
		int end;
		int people;
		
		Meeting(int start, int end ,int people){
			this.start= start;
			this.end = end;
			this.people = people;
		}
		
		@Override
		public int compareTo(Meeting m) {
			if(m.end == this.end) {
				return this.people - m.people; 
			}
			return this.end - m.end;
		}
		
	}

	
	public static int binarySearch(int[] array, int value) { //target보다 작은 애들 중 가장 큰 애
		int left = 0;
		int right = array.length-1;	
		while(left < right) {
			int mid = (left+right)/2;
			if(value <= array[mid]) {
				right = mid;
			} else {
				left = mid + 1; 
			}
		}
		return right-1;
	}
}
