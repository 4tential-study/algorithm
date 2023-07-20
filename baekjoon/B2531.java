// Baekjoon 2531
// 완전 탐색, 투 포인터 사용
import java.io.*;
import java.util.*;

public class Q2531 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] dishes = new int[Integer.parseInt(st.nextToken())]; // 접시 수
		int types = Integer.parseInt(st.nextToken()); // 초밥 가짓 수
		int eat_number = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
		int coupon = Integer.parseInt(st.nextToken()); // 쿠폰 번호

		int[] isEaten = new int[types + 1]; // 초밥 1번째 종류 ~ N번째 종류까지 각각 먹은 개수 (편한 연산을 위해 types+1 만큼 생성)
		int count = 0; // 여태까지 먹은 종류 개수 count
		for (int i = 0; i < dishes.length; i++) {
			dishes[i] = Integer.parseInt(br.readLine());
			// 첫번째 접시부터 k번째까지 먹었을 때의 경우를 미리 준비함.
			if (i < eat_number) { 
				if (isEaten[dishes[i]]++ == 0)
					count++; // i번째 접시를 먹기 전 한 번도 안 먹어봤는지 확인. 안 먹어봤다면 count++
			}
		}

		if (isEaten[coupon]++ == 0)
			count++; // 미리 쿠폰 쓰기
		int max_count = count;

		// 두 개의 포인터를 사용.
		int start_idx = 0, end_idx = eat_number - 1;
		do {
			// start 포인터의 초밥 개수 제거하면 0인지 판별 후, 개수 변경
			if (--isEaten[dishes[start_idx]] == 0)
				count--; 
			start_idx = (start_idx + 1) % dishes.length; // start 포인터 위치 변경

			// end 포인터의 초밥 개수 증가하기 전 0인지 판별 후, 개수 변경
			end_idx = (end_idx + 1) % dishes.length; // end 포인터 위치 변경
			if (isEaten[dishes[end_idx]]++ == 0)
				count++; 

			max_count = Math.max(max_count, count);
		} while (start_idx != 0); // 한 바퀴 돌 때까지 loop

		System.out.println(max_count);
	}
}