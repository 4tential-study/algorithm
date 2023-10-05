import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class B14622 {
	private static boolean[] isPrime, iscalled;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int rounds = Integer.parseInt(in.readLine());
		long daewoong = 0, kyuseong = 0;
		
		isPrime = new boolean[5_000_000];
		iscalled = new boolean[5_000_000];
		Arrays.fill(isPrime, true);
		
		Eratos();
		String[] input;
		
		// 대웅이의 소수
		Queue<Integer> dQueue = new PriorityQueue<>(Collections.reverseOrder());
		
		// 규성이의 소수
		Queue<Integer> kQueue = new PriorityQueue<>(Collections.reverseOrder());
		
		for(int r = 0; r < rounds; r++) {
			input = in.readLine().split(" ");
			int dNum = Integer.parseInt(input[0]); // 대웅이 외친 수
			int kNum = Integer.parseInt(input[1]); // 규성이 외친 수
			
			// 대웅이 소수가 아닌 수를 불렀을 경우
			if(!isPrime[dNum]) {
				// 규성이 3개 미만의 소수를 말했다면, 규성은 1000점을 얻는다.
				if(kQueue.size() < 3) { 
					kyuseong += 1000;
				}
				// 규성이 3개 이상을 불렀다면, 규성은 3번째로 큰 소수의 크기만큼 점수를 얻는다.
				else {
					int i1, i2;
					i1 = kQueue.poll(); i2 = kQueue.poll();
					kyuseong += kQueue.peek();
					kQueue.offer(i1); kQueue.offer(i2);
				}
			}
			// 대웅이가 소수를 불렀을 경우
			else {
				// 이미 부른 소수일 경우 대웅이의 점수가 -1000
				if(iscalled[dNum]) daewoong -= 1000;
				else {
					iscalled[dNum] = true;
					dQueue.offer(dNum);
				}
			}
			
			// 규성이 소수가 아닌 수를 불렀을 경우
			if(!isPrime[kNum]) {
				// 대웅이 3개 미만의 소수를 말했다면, 대웅은 1000점을 얻는다.
				if(dQueue.size() < 3) { 
					daewoong += 1000;
				}
				// 대웅이 3개 이상을 불렀다면, 대웅은 3번째로 큰 소수의 크기만큼 점수를 얻는다.
				else {
					int i1, i2;
					i1 = dQueue.poll(); i2 = dQueue.poll();
					daewoong += dQueue.peek();
					dQueue.offer(i1); dQueue.offer(i2);
				}
			}
			// 규성이 소수를 불렀을 경우
			else {
				// 이미 부른 소수일 경우 규성의 점수가 -1000
				if(iscalled[kNum]) kyuseong -= 1000;
				else {
					iscalled[kNum] = true;
					kQueue.offer(kNum);
				}
			}
		}
		
		if(daewoong == kyuseong) System.out.println("우열을 가릴 수 없음");
		else if(daewoong > kyuseong) System.out.println("소수의 신 갓대웅");
		else System.out.println("소수 마스터 갓규성");
	}
	
	
	
	static void Eratos() {
		int length = isPrime.length;
		isPrime[0] = isPrime[1] = false;
		
		int temp = (int)Math.sqrt(length)+1;
		for(int i = 2; i < temp; i++) {
			if(!isPrime[i]) continue;
			
			for(int j = 2; j*i < length; j++)	isPrime[j*i] = false;
		}
		
	}
}
