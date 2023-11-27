import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class B13975 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Queue<Long> queue = new PriorityQueue<>();
		
		int test_case = Integer.parseInt(in.readLine());
		for(int t = 0; t < test_case; t++) {
			queue.clear();
			int count = Integer.parseInt(in.readLine());
			String[] input = in.readLine().split(" ");
			
			for(int i = 0; i < count; i++)	queue.offer(Long.parseLong(input[i]));
			long sum = 0;
			
			while(queue.size() > 1) {
				long a = queue.poll(), b = queue.poll();
				long k = a + b;
				sum += k;
				queue.offer(k);
			}
			
			sb.append(sum).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
