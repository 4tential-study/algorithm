package week1106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon_1516 {

	static int N, time[], indegree[], result[];
	static ArrayList<Integer> list[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		time = new int[N + 1];
		indegree = new int[N + 1];
		result = new int[N + 1];
		list = new ArrayList[N + 1];
		
		for(int i=1;i<=N;i++)
			list[i] = new ArrayList<>();
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			
			time[i] = Integer.parseInt(st.nextToken());
			while(st.hasMoreTokens()) {
				int preBuild = Integer.parseInt(st.nextToken());
				
				if(preBuild == -1)
					break;
				
				indegree[i]++;
				list[preBuild].add(i);
			}
		}
		
		solve();
		
		
		for(int i=1;i<=N;i++)
			sb.append(result[i]).append('\n');
		
		System.out.println(sb);
	
	}
	
	static void solve() {
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=1;i<=N;i++) {
			if(indegree[i] == 0) {
				q.offer(i);
				result[i] = time[i];
			}
		}
		
		while(!q.isEmpty()) {
			int idx = q.poll();
			  
			for(int i : list[idx]) {
				// 이전 원소 중 가장 짓는데 오래 걸리는 원소의 시간 + 자신의 시간 
				result[i] = Math.max(result[i], result[idx] + time[i]);
				if(--indegree[i] == 0) 
					q.offer(i);
			}
		}
	}

}