import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N, C, pos[], result=0;
	static List<Integer> installed;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
//		N = 200_000;
//		C = 100_000;
		pos = new int[N];
//		installed = new boolean[N];
		installed = new ArrayList<Integer>();
		
		for(int i=0; i<N; i++) {
			pos[i] = Integer.parseInt(in.readLine());
		}
		
//		int num=0;
//		for(int i=0; i<N; i++) {
//			pos[i] = num;
//			num+=400;
//		}
		Arrays.sort(pos);
//		
//		installed[0] = true;
//		installed[N-1] = true;
		installed.add(pos[0]);
		installed.add(pos[N-1]);
		solve(C-2, 0, N-1);
		System.out.println(result);
//		System.out.println(cnt00);
	}
	static int cnt00;
	static void solve(int c, int left, int right) {
		cnt00++;
//		System.out.println(cnt00);
		// 1 2 3 4 5 6 7 8 9 10 40 95 99 100
		// 4
		// 답 : 9 (1, 10, 40, 100) 
		/*
		 * - mid와 (start, end) 사이의 차가 1 초과일 때 offer
		 * - 반복 횟수가 적을 수록 start와 end의 차가 크다
		 * start와 end 중 mid와의 value 차이가 큰 쪽 
		 */
		
		
		// 1 2 30 95 96 97 98 99 100
		// 4
		// 답 : 5 (1, 30, 95, 100)
		
		if(!(left<right)) return;
		
//		System.out.println(c+","+left+","+right);
		if(c==0) {
			
			int gap = Integer.MAX_VALUE;
			int prev=-1;
			int cur=-1;
//			for(int i=0; i<N; i++) {
////				cnt00++;
//				if(installed[i]) {
//					prev = cur;
//					cur = i;
//					if(prev!=-1) {
//						gap = Math.min(gap, pos[cur]-pos[prev]);
//					}
//				}
//			}
			Collections.sort(installed);
			for(int n : installed) {
				prev=cur;
				cur=n;
				if(prev!=-1) {
					gap = Math.min(gap, cur-prev);
				}
			}

			result = Math.max(result, gap);
			
			return;
		}
		
		int midIndex = (left+right)/2;
		int midValueIndex = midIndex;
		int target = (pos[right]+pos[left])/2;
		
		if(pos[midIndex]==target) {
//			installed[midIndex] = true;
			installed.add(pos[midIndex]);
			solve(c-1, left, midIndex);
			solve(c-1, midIndex, right);
//			installed[midIndex] = false;
			installed.remove(installed.indexOf(pos[midIndex]));
		}
		else if(pos[midIndex]<target) {
			int g = target-pos[midIndex];
			
			for(int i=midIndex+1; i<right; i++) {
				if(g <= Math.abs(target-pos[i])) break;
				g = Math.abs(target-pos[i]);
				midValueIndex = i;
			}

//			installed[midValueIndex] = true;

			installed.add(pos[midValueIndex]);
//			if(pos[midValueIndex]-pos[left] >= pos[right]-pos[midValueIndex])
				solve(c-1, left, midValueIndex);
//			else
				solve(c-1, midValueIndex, right);
//			installed[midValueIndex] = false;
			installed.remove(installed.indexOf(pos[midValueIndex]));
			
		}else {
			int g = pos[midIndex]-target;
			
			for(int i=midIndex-1; left<i; i--) {
				if(g <= Math.abs(target-pos[i])) break;
				g = Math.abs(target-pos[i]);
				midValueIndex = i;
			}

//			installed[midValueIndex] = true;

			installed.add(pos[midValueIndex]);
//			if(pos[midValueIndex]-pos[left] >= pos[right]-pos[midValueIndex])
				solve(c-1, left, midValueIndex);
//			else
				solve(c-1, midValueIndex, right);
//			installed[midValueIndex] = false;
			installed.remove(installed.indexOf(pos[midValueIndex]));
		}
		
	}
}