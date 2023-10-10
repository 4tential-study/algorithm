import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_9935 {
	static BufferedReader in;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		in = new BufferedReader(new InputStreamReader(System.in));
		String str = in.readLine();
		String p = in.readLine();
		
		Queue<int[]> q = new ArrayDeque<>();
//		Queue<String> queue = new ArrayDeque<>();
		int idx = 0;
		int start = 0;
		while(true) {
			for(int i=0 ; i < str.length() ; i++) {
				if(str.charAt(i) == p.charAt(idx)) {
					idx++;
				} else if(str.charAt(i) == p.charAt(0)) { 
					idx = 1;
				} else idx = 0;
				
				if(idx == p.length()) { //전부 같으면
					q.add(new int[] {start, i-p.length()});
					start = i+1;
					idx = 0;
				}
			}
			q.add(new int[] {start, str.length()-1});
			
			String temp = "";
			if(q.isEmpty()) {
				break;
			}else {
				int size = q.size();
				for(int s = 0 ; s < size ; s++) {
					int[] sub = q.poll();
//					queue.offer(str.substring(sub[0],sub[1]));
					if(sub[1] == str.length()-1) {
						temp += str.substring(sub[0]);
					}
					else temp += str.substring(sub[0],sub[1]+1);
				}
				str = temp;
			}
			
			
		}
		
		System.out.println(str);
	}
}
