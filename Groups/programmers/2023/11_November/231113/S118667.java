import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
	static int solution(int[] queue1, int[] queue2) {
		int answer = -2, length = queue1.length;
		Queue<Integer> q1 = new ArrayDeque<>();
		Queue<Integer> q2 = new ArrayDeque<>();
		
		long sum1 = 0, sum2 = 0;
		for(int i = 0; i < length; i++) {
			sum1 += queue1[i];	q1.offer(queue1[i]);
		}
		
		for(int i = 0; i < length; i++) {
			sum2 += queue2[i];	q2.offer(queue2[i]);
		}
		
		if((sum1 + sum2) % 2 != 0)	return -1;
		
		long target = (sum1 + sum2) / 2;
		
		int count = 0, value;
		while(sum1 != sum2) {
            if(count > (queue1.length + queue2.length) * 2) return -1;
			if(sum1 > sum2) {
				value = q1.poll();
				sum1 -= value;	sum2 += value;
				q2.offer(value);
			}
			else {
				value = q2.poll();
				sum2 -= value;	sum1 += value;
				q1.offer(value);
			}
			count++;
		}
		
		return count;
	}
}