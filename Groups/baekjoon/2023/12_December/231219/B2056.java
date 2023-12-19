import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class B2056 {
	static int[] parents;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		
		int[] times = new int[N+1]; // 각 작업이 끝났을 때 걸리는 시간을 기록함.
		Queue<Integer> queue;
		for(int i = 1; i <= N; i++) {
			String[] input = in.readLine().split(" ");
			int t = Integer.parseInt(input[0]);
			int prepare_count = Integer.parseInt(input[1]);
			if(prepare_count == 0)	times[i] = t;

            // 선행 관계가 있다면 선행 관계에 있는 작업 중 가장 오래 걸리는 시간을 찾은 후,
            // k번째 작업을 수행하는 데 걸리는 시간과 더한다.
			else {
				queue = new PriorityQueue<Integer>(Collections.reverseOrder());
				for(int j = 2; j < input.length; j++) {
					queue.offer(times[Integer.parseInt(input[j])]);
				}
				times[i] = t + queue.poll();
			}
		}
		
		Arrays.sort(times);
		System.out.println(times[N]);
	}
}