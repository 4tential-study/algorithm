import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1516 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine()); // 건물 수
		
		List<Integer>[] lists = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) lists[i] = new ArrayList<>();
		
		int[] node_counts = new int[N+1]; // 진입 차수
		int[] seconds = new int[N+1]; // 각 건물의 건설 소요 시간
		int[] dp = new int[N+1]; // 추가 소요 시간
		
		
		// 위상 정렬을 위한 큐
		Queue<Integer> queue = new ArrayDeque<>();
		
		for(int i = 1; i<= N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			seconds[i] = Integer.parseInt(st.nextToken()); // 건물 건설 소요 시간
			
			// 이전에 지어야 할 건물의 번호 (-1 입력받으면 그만)
			while(true) {
				int input = Integer.parseInt(st.nextToken());
				if(input == -1)	break;
				
				lists[input].add(i); // 이전에 지어야 할 건물의 child 추가
				node_counts[i]++; // 진입 차수 증가
			}
		}
		
		for(int i = 1; i <= N; i++) {
			if(node_counts[i] == 0) queue.offer(i);
		}
		
		
		// 위상 정렬 수행
		while(!queue.isEmpty()) {
			int node = queue.poll();
			
			seconds[node] += dp[node];
			
			int size = lists[node].size();
			for(int i = 0; i < size; i++) {
				int child = lists[node].get(i);
				dp[child] = Math.max(dp[child], seconds[node]);
				node_counts[child]--;
				if(node_counts[child] == 0) queue.offer(child);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) sb.append(seconds[i]).append("\n");
		
		System.out.println(sb);
		
	}
}
