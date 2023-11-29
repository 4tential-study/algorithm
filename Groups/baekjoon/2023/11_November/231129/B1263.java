import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1263 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine()); // 일의 수
		
		// 우선순위 큐 생성. (마감해야하는 시간 오름차순으로 정렬)
		Queue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return (o1[1] == o2[1])? o1[0] - o2[0] : o1[1] - o2[1];
			}
		});
		
		StringTokenizer st;
		
		// 해야할 일 들 입력받기
		for(int i = 0; i < N; i++) {
			int[] work = new int[2];
			st = new StringTokenizer(in.readLine());
			work[0] = Integer.parseInt(st.nextToken()); // 소요 시간
			work[1] = Integer.parseInt(st.nextToken()); // 마감 기한
			queue.offer(work);
		}
		/* 
		 * answer를 k로 두고 예제 입력을 정렬한 후
		 * 3 5
		 * 8 14
		 * 1 16
		 * 5 20
		 * 위의 일들을 이렇게 계산해보자.
		 * k + 3 <= 5
		 * k + 11(3+8) <= 14
		 * k + 12(11+1) <= 16
		 * k + 17(12+5) <= 20
		 * 
		 * 위의 식을 모두 만족하는 k의 최대 값이 문제의 해답이라 생각하였다.
		 */
		int answer = Integer.MAX_VALUE;
		int sum = 0;
		while(!queue.isEmpty()) {
			int[] work = queue.poll();
			sum += work[0];
			if(sum > work[1]) {
				answer = -1;	break;
			}
			answer = Math.min(answer, work[1] - sum);
		}
		
		System.out.println(answer);
	}
}
