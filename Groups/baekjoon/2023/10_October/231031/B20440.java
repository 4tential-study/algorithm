import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 이전에는 TreeMap을 활용하였으나, 모든 시간을 삽입한 후 한 번만 sort를 하면 된다고 생각하여,
// HashMap으로 대체한 후, 모든 시간을 삽입한 뒤 sort를 1번 수행하는 것을 변경.
public class B20440 {
	/*
	 * TreeMap 시간 복잡도 :
	 * get, containsKey, next -> O(logn)
	 * 
	 * HashMap 시간 복잡도 : 
	 * get, put, remove, containsKey -> 0(1), next -> O(h/n), h는 테이블 용량
	 */
	private static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		int count = Integer.parseInt(in.readLine()); // 최대 1_000_000
		
		
		for(int i = 0; i < count; i++) {
			input = in.readLine().split(" ");
			// 시간 0 ~ 2_100_000_000
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			
			settingValue(start, 1);
			settingValue(end, -1);
		}

		int current = 0, answer = 0;
		int[] range = new int[2];
		int start = 0;
		
		List<Integer> keyList = new ArrayList<>(map.keySet());
		Collections.sort(keyList);
		
		for(Integer time : keyList) {
			int weight = map.get(time);
			if(current < current + weight) {
				start = time;
			}
			else {
				if(answer < current) {
					answer = current;
					range[0] = start;	range[1] = time;
				}
			}
			current += weight;
		}
			
		System.out.println(answer);
		System.out.println(range[0] + " " + range[1]);
	}
	
	static void settingValue(int time, int weight) {
		if(!map.containsKey(time)) map.put(time, weight);
		else {
			int w = map.get(time) + weight;
			if(w == 0) map.remove(time);
			else map.put(time, w);
		}
	}
}