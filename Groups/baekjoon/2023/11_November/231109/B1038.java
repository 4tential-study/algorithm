import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class B1038 {
	static List<Long> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		if(N <= 10) {
			System.out.println(N); return;
		}
		else if(N >= 1023) {
			System.out.println("-1"); return;
		}
		
		for(int i = 0; i < 10; i++) {
			DFS(i);
		}
		
		Collections.sort(list);
		System.out.println(list.get(N));
	}
	
	static void DFS(long num) {
		list.add(num);
		long mod = num % 10;
		if(mod == 0)	return;
		
		for(long i = mod-1; i >= 0; i--) {
			long newValue = num * 10 + i;
			DFS(newValue);
		}
	}
}