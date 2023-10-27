import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class B25168 {
	private static final int limit = 7; // 백신 유효기간
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]); // 백신 개수
		int M = Integer.parseInt(input[1]); // 백신 선행관계 수
		List<Vaccine>[] lists = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) lists[i] = new ArrayList<>();
		
		// DP[i] : i번째 백신을 처음으로 접종하는 날 (메모이제이션)
		int[] DP = new int[N+1];
		
		// 진입 차수 개수 count
		int[] nodes = new int[N+1];
		
		Arrays.fill(DP, 0);
		
		for(int i = 0; i < M; i++) {
			input = in.readLine().split(" ");
			int S = Integer.parseInt(input[0]); // 이전에 접종해야 할 백신 번호
			int E = Integer.parseInt(input[1]); // 이후에 접종해야 하는 백신 번호
			int W = Integer.parseInt(input[2]); // 최소 대기기간
			
      // 재접종이 필요함.
      // 재접종 후에는 최소 대기기간에 대한 부작용이 성립하지 않아
      // 백신 유효기간 + 1 으로 계산하였으나, 
	    // 문제에서 요구하는 것은 최소 대기기간을 모두 소모한 후 재접종을 하는 것 같다.
			if(W >= limit) W += 1; 
			lists[S].add(new Vaccine(E, W));
			nodes[E]++; // 진입 차수 증가
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		
    // 진입 차수가 0인 백신은 1일차에 모두 접종하므로 DP 초기화 및 queue offer
		for(int i = 1; i <= N; i++) {
			if(nodes[i] == 0) { 
				DP[i] = 1; queue.offer(i);
			}
		}
		
    // 위상 정렬 수행
		while(!queue.isEmpty()) {
			int node = queue.poll();
			
			int size = lists[node].size();
			for(int i = 0; i < size; i++) {
				Vaccine vaccine = lists[node].get(i);
				int target = vaccine.num, taken_time = vaccine.day;

				DP[target] = Math.max(DP[target], DP[node] + taken_time);
        
				nodes[target]--;
				if(nodes[target] == 0)	queue.offer(target);
			}
		}
		
		Arrays.sort(DP);
		System.out.println(DP[N]);
	}
}

class Vaccine{
	int num; // 이후에 접종해야 하는 백신 번호
	int day; // 최소 대기기간
	
	public Vaccine() {}

	public Vaccine(int num, int day) {
		super();
		this.num = num;
		this.day = day;
	}
}