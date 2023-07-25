import java.io.*;
import java.util.Arrays;

/*
 * 분할 정복을 이용하여 문제를 풀었다.
 * 별을 찍기 전 board를 ' '으로 초기화를 하였고, solve 메서드에서 별을 찍는 작업을 수행함.
 * 분할 정복에서 가장 작은 칸(1*1)은 '*'으로 return.
 * 이전에 해당 칸이 다음 단계(3*3, 9*9 ...)에서 center인 경우, 바로 return하여
 * 별 찍는 작업을 수행하지 않게 구현하였다.
 */

public class B2447 {
	static char[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int size = Integer.parseInt(br.readLine());

		board = new char[size][size];
		// 별이 없는 곳은 ' ' 이므로 모든 값을 해당 값으로 초기화.
		// char의 default 값은 ' '이 아님! default : '\u0000', ' ' = 32(ASCII)
		for(int i = 0; i < size; i++)	Arrays.fill(board[i], ' ');
		
		solve(0, 0, size, false);
		for (int i = 0; i < size; i++) {
			String str = String.valueOf(board[i]);
			bw.write(str);
			bw.newLine();
		}
		bw.flush();
	}

	// index를 입력받음.
	static void solve(int start_y, int start_x, int size, boolean isCenter) {
		if (isCenter) return; // 가운데 판이라면 별 찍기 실행하지 않을 것이다.
		if (size == 1) {
			board[start_y][start_x] = '*';
			return;
		}
		/*
		 * 1	2	3
		 * 4	5	6
		 * 7	8	9
		 * 위와 같이 구간을 나누었을 때,
		 * 각 구간의 왼쪽 상단 지점(1번)이 (y,x)가 되도록 for문을 수행하였다.
		 * (y,x)가 5번째 구간이라면 수행하지 않으며,
		 * 이외의 구간일 경우 더 작은 문제 (27*27 -> 9*9 -> 3*3 ...)으로 수행되게 solve를 재귀함.
		 */
		for (int y = start_y; y < start_y + size; y += (size / 3)) {
			for (int x = start_x; x < start_x + size; x += (size / 3)) {
				boolean b; // 센터인가요?
				if (y == start_y + (size / 3) && x == start_x + (size / 3))
					b = true;
				else
					b = false;
				solve(y, x, size / 3, b);
			}
		}
	}
}