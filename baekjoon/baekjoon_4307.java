import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int L = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			int far = -1;
			int close = 0;
			for(int i=0; i<N; i++) {
				int pos = Integer.parseInt(in.readLine());
				
				if(L-pos > far) {
					far = L-pos;
				}else if(pos > far) {
					far = pos;
				}
				
				if(pos >= L/2)
					close = Math.max(close, L-pos);
				else
					close = Math.max(close, pos);
			}
			
			sb.append(close+" "+far+"\n");
		}
		System.out.println(sb);
	}
}