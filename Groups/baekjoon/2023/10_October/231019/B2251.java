import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.TreeSet;

public class B2251 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");
		int[] max_amounts = new int[3]; // 물통 A, B, C에 담을 수 있는 최대 량
		for(int i = 0; i < 3; i++)
			max_amounts[i] = Integer.parseInt(input[i]);
		
		int[] liters = new int[3];
		liters[2] = max_amounts[2];
		
		boolean[][][] 가능 = new boolean[max_amounts[0]+1][max_amounts[1]+1][max_amounts[2]+1];
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0, 0, max_amounts[2]});
		TreeSet<Integer> set = new TreeSet<>();
		while(!queue.isEmpty()) {
			int[] current = queue.poll().clone();
			if(가능[current[0]][current[1]][current[2]]) continue;
			
			가능[current[0]][current[1]][current[2]] = true;
			if(current[0] == 0)	set.add(current[2]);
			for(int i = 0; i < 3; i++) {
				if(current[i] == 0)	continue;
				// 물통 i에서 물통 j로 물을 옮긴다.
				for(int j = 0; j < 3; j++) {
					if(i == j)	continue;
					// 물통 j에 넣을 수 있는 량을 계산함.
					int canFill = Math.min(max_amounts[j] - current[j], current[i]); 

					// 물통 j를 채운 후의 결과를 clone_array에 계산한 후,
					// 여태까지 나온 사건이 아닌 경우, queue에 넣는다.
					int[] clone_array = current.clone();
					clone_array[j] += canFill;
					clone_array[i] -= canFill;
					if(!가능[clone_array[0]][clone_array[1]][clone_array[2]]) {
						queue.offer(new int[] {clone_array[0], clone_array[1], clone_array[2]});
					}
				}
			}
		}
		
		Iterator iter = set.iterator();
		while(iter.hasNext()) {
			System.out.print(iter.next() + " ");
		}
	}
}
