package 백준_4307_개미;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_4307_개미 {
	public static int l, n, loc, min_time, max_time;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int test_case=1;test_case<=T;test_case++) {
			min_time=0;
			max_time=0;
			String[] line = br.readLine().split(" ");
			l=Integer.parseInt(line[0]);
			n=Integer.parseInt(line[1]);
			for (int i=0;i<n;i++) {
				loc = Integer.parseInt(br.readLine());
				min_time=Math.max(Math.min(loc, l-loc),min_time);
				max_time=Math.max(Math.max(loc, l-loc),max_time);
			}
			sb.append(min_time).append(' ').append(max_time).append('\n');
		}
		System.out.println(sb);
	}

}
