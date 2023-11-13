import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class B15903 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");
		
		int card_count = Integer.parseInt(input[0]);
		int play_count = Integer.parseInt(input[1]);
	
		input = in.readLine().split(" ");
		Queue<Long> queue = new PriorityQueue<>();
		
		for(int i = 0; i < card_count; i++) queue.offer(Long.parseLong(input[i]));
		
		for(int p = 0; p < play_count; p++) {
			long num1 = queue.poll(), num2 = queue.poll();
			long sum = num1 + num2;
			queue.offer(sum);	queue.offer(sum);
		}
		
		long answer = 0;
		int size = queue.size();
		for(int i = 0; i < size; i++) {
			answer += queue.poll();
		}
		
		System.out.println(answer);
	}
}