// baekjoon 11497
#include <iostream>
#include <vector>
#include <algorithm> // sort 함수 사용
#include <climits> // INT_MIN 사용
#include <cstdlib> // abs 함수 사용
using namespace std;
int main()
{
	// 동기화를 비활성화시켜 속도를 높임.
	// 하지만 멀티 쓰레드 환경에서는 출력 순서를 보장할 수 없다.
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int Testcase;		 cin >> Testcase;
	while (Testcase-- > 0) {
		int log;	cin >> log; // 통나무 개수
		vector<int> logs, result;
		for (int i = 0; i < log; i++) {
			int len; cin >> len;		logs.push_back(len);
		}
		// 통나무 정렬
		sort(logs.begin(), logs.end());

		// 배치 결과 생성.
		int size = logs.size();
		for (int i = 0; i < size / 2.0; i++) {
			result.push_back(logs[i]);
			logs.erase(logs.begin() + i);
		}

		while (logs.size() > 0) {
			result.push_back(logs.back());
			logs.pop_back();
		}

		int max_gap = INT_MIN;
		for (int i = 0; i < result.size(); i++) {
			int next_index = (i + 1) % (result.size());
			int value = abs(result[i] - result[next_index]);
			max_gap = (max_gap < value) ? value : max_gap;
		}

		cout << max_gap << endl;
	}
}