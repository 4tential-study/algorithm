import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_20055 {
	static BufferedReader br;
	static StringTokenizer st;
	static int n;
	static int k;
	static int[] belt;
	static boolean[] has;
	static int zero;
	static int phase=0;
	public static void main(String[] args) throws IOException{
		br= new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		k = Integer.parseInt(input[1]);
		belt = new int[n*2];
		has = new boolean[n*2];
	
		
		st = new StringTokenizer(br.readLine());
		int cnt = 0;
		int std = n*2;
		int order = 0;
		
		for(int i=0 ; i < belt.length ; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}

		int next;
		int cur = 0;
		
//		while(zero < k) {
//			System.out.println("whhile");
//			phase++;
//			cur %= n*2;
//			putOn();
//			if(zero >= k ) break;
//			next = cur+1;
//			moveTo();
//			System.out.println(Arrays.toString(belt));
//			layDown();
//		}
		
		
	
		System.out.println(phase);
	}
	
	public static void putOn() {
		if(belt[0] > 0 && !has[0]) {
			belt[0]--;
			has[0] = true;
			if(belt[0] == 0) {
				zero++;
			}
		} else return;				
	}
	
	public static void moveTo() {
		System.out.println("moveTo");
		phase++;
		for(int i=0 ; i < belt.length ; i++) {
			int next = (i+1) % belt.length;
			if(has[i] && belt[next] > 0 && !has[next]) {
				System.out.println("if문 --- " +i);
				belt[next]--;
				if(belt[next] == 0) {
					if(++zero >= k) break;
				}
				has[i] = false;
				has[next] = true;
				
			}
		}
//		if(belt[to] > 0 && !has[to]) {
//			//올라간다/이동한다
//			belt[to]--;
//			if(belt[to] == 0) zero++;
//			has[from] = false;
//			has[to] = true;
//			return to;
//		} else return from;
	}
	
	public static void layDown() {
		has[n-1] = false;
	}
}
