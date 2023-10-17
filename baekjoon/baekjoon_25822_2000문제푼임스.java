package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class baekjoon_25822_2000문제푼임스 {

	// 자료 참고했습니다...
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String c = br.readLine();
		int cnt = 0;
		if (c.compareTo("1.98")>=0)
			cnt = 2;
		else if (c.compareTo("0.99")>=0)
			cnt = 1;
		
		int max_sol=0;
		int n = Integer.parseInt(br.readLine());
		int[] streak = new int[n+1];
		String[] line = br.readLine().split(" ");
		for (int i=1;i<=n;i++) {
			int sol = Integer.parseInt(line[i-1]);
			if (sol==0)
				streak[i]=streak[i-1]+1;
			else
				streak[i]=streak[i-1];
			max_sol=Math.max(max_sol, sol);
		}
	
		int start = 1, end = 1;
		int max_streak = 0;
		while (end<=n) {
			int need = streak[end]-streak[start-1];
			if (need<=cnt) {
				max_streak = Math.max(max_streak, end-start+1);
				end++;
			}else {
				start++;
			}
		}
		System.out.println(max_streak);
		System.out.println(max_sol);
	}
}