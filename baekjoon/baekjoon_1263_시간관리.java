package week1127;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class baekjoon_1263_시간관리 {
	static class Work implements Comparable<Work>{
		int t;
		int s;
		public Work(int t, int s) {
			super();
			this.t = t;
			this.s = s;
		}
		@Override
		public String toString() {
			return "Work [t=" + t + ", s=" + s + "]";
		}
		@Override
		public int compareTo(Work o) {
			return Integer.compare(o.s, this.s);
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int workCnt = Integer.parseInt(in.readLine());
		
		Work[] workList = new Work[workCnt];
				
		for(int i=0; i<workCnt; i++) {
			String[] time = in.readLine().split(" ");
			int t = Integer.parseInt(time[0]);
			int s = Integer.parseInt(time[1]);
			
			workList[i] = new Work(t, s);
		}
		
		Arrays.sort(workList);
		
		int lastTime = workList[0].s;
		for(int i=0; i<workCnt; i++) {
			if(lastTime < workList[i].s)
				lastTime = lastTime - workList[i].t;
			else
				lastTime = workList[i].s - workList[i].t;
		}
		
		if(lastTime < 0) System.out.println("-1");
		else System.out.println(lastTime);
	}
}
