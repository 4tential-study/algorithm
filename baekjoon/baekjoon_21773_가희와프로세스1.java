package Algorithm.boj.day0920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjoon_21773{
	static class Process implements Comparable<Process>{
		int id;
		int priority;
		int time;
		public Process(int id, int time, int priority) {
			super();
			this.id = id;
			this.priority = priority;
			this.time = time;
		}
		@Override
		public int compareTo(Process o) {
			if(this.priority == o.priority) return Integer.compare(this.id, o.id);
			return Integer.compare(o.priority, this.priority);
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int T = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Process> schedule = new PriorityQueue<>();
				
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(in.readLine());
			
			int id = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			int priority = Integer.parseInt(st.nextToken());
			
			Process process = new Process(id, time, priority);
			
			schedule.offer(process);
		}
		
		for(int i=0; i<T; i++) {
			Process process = schedule.poll();
			--process.priority;
			if(--process.time>=1) schedule.offer(process);
			sb.append(process.id).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}