package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class baekjoon_14622_소수게임 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		List<Integer> dae_list = new ArrayList<>();
		List<Integer> kyu_list = new ArrayList<>();
		long dae_score = 0;
		long kyu_score = 0;
		int dae_1 = 0, dae_2 = 0, dae_3 = 0;
		int kyu_1 = 0, kyu_2 = 0, kyu_3 = 0;
		int[] li = new int[5000001];

		for (int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");

			// 대웅이의 경우를 먼저 실행
			int dae = Integer.parseInt(line[0]);

			// 이미 등장한 소수를 말한 경우
			if (li[dae] == 1)
				dae_score -= 1000;

			else {
				// 대웅이가 부른 숫자가 소수일 때
				if (isPrimeNumber(dae) == true) {
					// 소수인 경우에만 해당 숫자를 말한 것을 체크한다.
					li[dae] = 1;
					// 리스트에 소수를 추가한다.
					dae_list.add(dae);
					// 만약 리스트에 숫자가 두 개이면
					if (dae_list.size() == 2) {
						// 더 큰 걸 1, 작은 걸 2로 저장한다.
						dae_1 = Math.max(dae_list.get(0), dae_list.get(1));
						dae_2 = Math.min(dae_list.get(0), dae_list.get(1));
					}
					// 만약 리스트에 숫자가 세 개 이상이면
					else if (dae_list.size() >= 3) {
						// 새로운 숫자가 가장 클 때 1,2,3 갱신
						if (dae > dae_1) {
							dae_3 = dae_2;
							dae_2 = dae_1;
							dae_1 = dae;
						}
						// 새로운 숫자가 두 번째로 클 때 1,2,3 갱신
						else if (dae > dae_2) {
							dae_3 = dae_2;
							dae_2 = dae;
						}
						// 새로운 숫자가 세 번째로 클 때 1,2,3 갱신
						else if (dae > dae_3) {
							dae_3 = dae;
						}
					}
				}
				// 대웅이가 부른 숫자가 소수가 아닐 때
				else {
					// 만약 상대방이 말한 소수가 3개 미만일 때
					if (kyu_list.size() < 3) {
						kyu_score += 1000;
					}
					// 상대방이 말한 소수가 3개 이상일 때
					else {
						kyu_score += kyu_3;
					}
				}
			}

			// 규성이의 경우를 실행
			int kyu = Integer.parseInt(line[1]);

			// 이미 등장한 소수를 말한 경우
			if (li[kyu] == 1)
				kyu_score -= 1000;

			else {
				// 규성이가 부른 숫자가 소수일 때
				if (isPrimeNumber(kyu) == true) {
					// 소수인 경우에만 해당 숫자를 말한 것을 체크한다.
					li[kyu] = 1;
					// 리스트에 소수를 추가한다.
					kyu_list.add(kyu);
					// 만약 리스트에 숫자가 두 개이면
					if (kyu_list.size() == 2) {
						// 더 큰 걸 1, 작은 걸 2로 저장한다.
						kyu_1 = Math.max(kyu_list.get(0), kyu_list.get(1));
						kyu_2 = Math.min(kyu_list.get(0), kyu_list.get(1));
					}
					// 만약 리스트에 숫자가 세 개 이상이면
					else if (kyu_list.size() >= 3) {
						// 새로운 숫자가 가장 클 때 1,2,3 갱신
						if (kyu > kyu_1) {
							kyu_3 = kyu_2;
							kyu_2 = kyu_1;
							kyu_1 = kyu;
						}
						// 새로운 숫자가 두 번째로 클 때 1,2,3 갱신
						else if (kyu > kyu_2) {
							kyu_3 = kyu_2;
							kyu_2 = kyu;
						}
						// 새로운 숫자가 세 번째롤 클 때 1,2,3 갱신
						else if (kyu > kyu_3) {
							kyu_3 = kyu;
						}
					}
				}
				// 규성이가 부른 숫자가 소수가 아닐 때
				else {
					// 만약 상대방이 말한 소수가 3개 미만일 때
					if (dae_list.size() < 3) {
						dae_score += 1000;
					}
					// 상대방이 말한 소수가 3개 이상일 때
					else {
						dae_score += dae_3;
					}
				}
			}
		}
		// 결과 비교
		if (dae_score > kyu_score)
			System.out.println("소수의 신 갓대웅");
		else if (dae_score < kyu_score)
			System.out.println("소수 마스터 갓규성");
		else
			System.out.println("우열을 가릴 수 없음");
	}

	// 소수 판별 함수. 1은 소수가 아님에 주의!!
	static boolean isPrimeNumber(int number) {
		if (number == 1 || number == 0)
			return false;
		for (int i = 2; i <= (int) Math.sqrt(number); i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
}