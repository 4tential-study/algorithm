package Algorithm.boj.day0918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon_20055 {
	
	static int N, K, A[] = new int[200];
	static boolean isExist[] = new boolean[200];
	static Queue<Integer> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 입력 받기
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		q = new ArrayDeque<Integer>();
		
		st = new StringTokenizer(in.readLine());
		
		for(int i=0; i<2*N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int stage = 0;
		int zeroCount = 0;
		int left = 0;
		int right = N-1;
		
		while(zeroCount<K) {
			
			stage++;
			left = rotate(--left);
			right = rotate(--right);
			
			int qsize = q.size();
			
			for(int i=0; i<qsize; i++){
				int curPos = q.poll();
				int nextPos = rotate(curPos+1);
				
				// 현재 위치가 마지막 위치라면 바로 내림
				if(curPos==right) {
					isExist[curPos] = false;
					continue;
				}
				
				// 다음 위치에 로봇이 있는지, 내구도가 충분한지 확인
				else if(!isExist[nextPos] && A[nextPos]>=1) {
					// 조건을 충족한다면 다음 위치로 이동
					isExist[curPos] = false;	
					if(--A[nextPos] == 0) zeroCount++;
					
					// 만약 다음 위치가 마지막 위치라면 바로 내림	
					if(nextPos==right) continue;
					
					isExist[nextPos] = true;
					q.offer(nextPos);
				}else {
					q.offer(curPos);
				}
				
			}
			
			// 시작 지점이 비어있다면 로봇을 올린다.
			if(!isExist[left] && A[left]>=1) {
				q.offer(left);
				isExist[left] = true;
				if(--A[left]==0) zeroCount++;
			}
		}
		
		System.out.println(stage);
	}
	static int rotate(int num) {
		if(num<0) {
			num += 2*N;
		}
		if(num>=2*N) {
			num -= 2*N;
		}
		
		return num;
	}
}