package Algorithm.boj.day0730;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class baekjoon_9663 {
	
	static int N;
	static int result=0;
//	static List<Set<Integer>> forbid;  
	static List<ArrayList<Integer>> forbid;
	
	static int cnt = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.close();
		forbid = new ArrayList<>(4);	// 0 : 행 검색, 1 : 열 검색, 2 : 슬래시 검색, 3 : 백슬래시 검색
//		forbid.add(new HashSet<Integer>());
//		forbid.add(new HashSet<Integer>());
//		forbid.add(new HashSet<Integer>());
//		forbid.add(new HashSet<Integer>());
		forbid.add(new ArrayList<Integer>());
		forbid.add(new ArrayList<Integer>());
		forbid.add(new ArrayList<Integer>());
		forbid.add(new ArrayList<Integer>());
		
		N_QUEEN(0, 0);
		
		System.out.println(result);
//		System.out.println(cnt);
	}
	
	static void N_QUEEN(int q_cnt, int y){
		// 현재 재귀에 들어온 순간 설치한 퀸의 수가 N개라면 리턴 
		if(q_cnt==N) {
			result++;
			return;
		}
		
		// 이전에 둔 퀸과 행, 열, 대각선 방향이 같다면 넘어간다.
		// 그러기 위해서 현재까지 설치한 퀸의 위치를 저장한다.
		
		for(int i=y; i<N; i++) {
//			if(forbid.get(0).contains(i)) continue;
			
			for (int j=0; j<N; j++) {
				cnt++;
				if(forbid.get(1).contains(j)) continue;
				if(forbid.get(2).contains(i-j)) continue;
				if(forbid.get(3).contains(i+j)) continue;
				

//				forbid.get(0).add(i);
				forbid.get(1).add(j);
				forbid.get(2).add(i-j);
				forbid.get(3).add(i+j);
				
				N_QUEEN(q_cnt+1, i+1);
				
////				forbid.get(0).remove(i);
//				forbid.get(1).remove(j);
//				forbid.get(2).remove(i-j);
//				forbid.get(3).remove(i+j);
				forbid.get(1).remove(forbid.get(1).size()-1);
				forbid.get(2).remove(forbid.get(2).size()-1);
				forbid.get(3).remove(forbid.get(3).size()-1);
			}
		}
	}
}

