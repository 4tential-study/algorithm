import java.io.*;

public class B2579 {
	static int[] DP, scores;
	static int steps;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		steps = Integer.parseInt(br.readLine());
		scores = new int[steps + 1];
		DP = new int[steps + 1];
		for (int i = 1; i <= steps; i++)
			scores[i] = Integer.parseInt(br.readLine());

		solve();

		System.out.println(DP[steps]);
	}

	static void solve() {
		// steps가 2 이하일 경우를 대비하여 조건문을 사용하였다.
		if(steps >= 1)	DP[1] = scores[1];
		if(steps >= 2)	DP[2] = scores[1] + scores[2];
		/*
		 * 세 번째 이상의 계단을 가는 점화식을 세우는 데 오래 걸린 것 같다.
		 * 0 -> 1 -> 3, 0 -> 2 -> 3 으로 먼저 생각해보았지만, 계산이 더 복잡해지기도 하였고 오답이 나오기도 하였다.
		 * 첫 시작을 한 칸이냐 두 칸이냐를 생각하지 않고
		 * 아예 시작하는 계단에서 두 칸씩 이동하는 것으로 생각하게 된다면,
		 * 연속으로 세 계단을 밟는 조건은 생각하지 않아도 되며 더 단순하게 생각해볼 수 있다.
		 * 그래서 0 -> 2 -> 3, 1 -> 3 이 두 가지를 체크한다면 해결되는 문제였다.
		 */
		for (int i = 3; i <= steps; i++) {
			DP[i] = Math.max(DP[i-3]+scores[i-1], DP[i-2]) + scores[i];
		}
	}
}