#include <iostream>
#include <vector>
using namespace std;

int main() {

	double coin;
	int N;
	cin >> coin;
	cin >> N;

	// 스트릭 개수
	int k;
	if (coin >= 1.98) k = 2;
	else if (coin >= 0.99) k = 1;
	else k = 0;

	// 스트릭을 유지한 경우 일 수, 문제를 풀지 않은 경우 -1을 삽입
	vector<int> v;

	// 최대로 문제를 푼 개수
	int maxV = 0;

	int tmp, streak = 0;
	for (int i = 0; i < N; i++) {
		cin >> tmp;

		// 최대값 갱신
		if (tmp > maxV) maxV = tmp;

		// 문제를 푼날
		if (tmp != 0) {
			++streak;

			// 마지막인 경우
			if (i == N - 1) v.push_back(streak);
		}
		// 문제를 풀지 않은 날
		else {
			// 스트릭 끊기면 푸시~
			if (streak > 0) {
				v.push_back(streak);
				streak = 0;
			}

			// 오늘 안풀었어요
			v.push_back(-1);
		}
	}

	// 탐색
	int maxStreak = 0;

	for (int i = 0; i < v.size(); i++) {
		streak = 0;
		int prism = k;
		for (int j = i; j < v.size(); j++) {
			if (v[j] > 0) streak += v[j];
			// 문제를 풀지 않은 날의 경우
			else {
				// 처리 가능
				if (prism > 0) { streak++; prism--; }
				else {
					// 처리 불가 : 스트릭 끊김
					break;
				}
			}
		}

		// 갱신
		if (streak > maxStreak) maxStreak = streak;
	}

	// 결과값 출력
	cout << maxStreak << '\n' << maxV << '\n';

	return 0;
}
