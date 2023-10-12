package study_4tential;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 자료 참고, 누적합

public class baekjoon_3020_개똥벌레 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int H = Integer.parseInt(line[1]);
		int[] ss_cnt = new int[H + 1]; // 해당 높이에 존재하는 석순 수 저장할 배열
		int[] js_cnt = new int[H + 1]; // 해당 높이에 존재하는 종유석 수 저장할 배열
		int min_val = Integer.MAX_VALUE;
		int cnt = 0;

		for (int i = 0; i < N / 2; i++) {
			int ss = Integer.parseInt(br.readLine()); // 주어진 석순의 높이
			int js = Integer.parseInt(br.readLine()); // 주어진 종유석의 높이(아래로 내려오는 형태이지만, 후에 처리)
			ss_cnt[ss]+=1;
			js_cnt[js]+=1;
		}
		for (int i=H;i>1;i--) {
			ss_cnt[i-1]+=ss_cnt[i]; // 예를 들어, 높이가 5인 석순이 2개, 2인 석순이 1개라면 높이 1에는 석순이 3개 존재한다. 이와 같이 타고 내려간다.
			js_cnt[i-1]+=js_cnt[i];
		}
		for (int i=1;i<=H;i++) {
			if (min_val>ss_cnt[i]+js_cnt[H-i+1]) { // 종유석의 경우 위에서부터 아래로 내려온다. 예를 들어 js_cnt가 321이라면, 각 공간에는 종유석이 1개, 2개, 3개 존재하는 것이므로 H-i+1번쨰와 같다.
				min_val=ss_cnt[i]+js_cnt[H-i+1];
				cnt=1;
			}
			else if (min_val==ss_cnt[i]+js_cnt[H-i+1])
				cnt++;
		}
		System.out.println(min_val+" "+cnt);
	}
}
