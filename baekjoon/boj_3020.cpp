#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// 동굴의 길이, 높이
int N, H;

// 뺴액

// 정답 : 최소 파괴 개수, 중복인 개수
int minBreak, cnt;

// 석순, 종유석
vector<int> suksun;
vector<int> jongyu;

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	cin >> N >> H;

	int k;
	for (register int i = 0; i < N; i++) {
		cin >> k;

		// 석순 -> 아래에서 솟음
		if (i % 2 == 0) {
			suksun.push_back(k);
		}
		// 종유석 -> 위에서 솓음
		else {
			jongyu.push_back(k);
		}
	}

	// 정렬 -> 이분탐색을 위해서(나보다 큰 놈이 몇개 있는지)
	sort(suksun.begin(), suksun.end());
	sort(jongyu.begin(), jongyu.end());


	// 초기화
	minBreak = 200001; cnt = 0;

	// 각 높이별로 탐색
	int curBreak;
	for (int i = 1; i <= H; i++) {
		// 나와 같거나 나보다 높은 석순의 idx
		int sIdx = upper_bound(suksun.begin(), suksun.end(), i - 1) - suksun.begin();

		// sIdx ~ : 내가 뚫어야 함.
		// suksun.size() - sIdx : 내가 뚫어야 하는 개수

		// 나한테 까지 오는 종유석의 개수
		int jIdx = upper_bound(jongyu.begin(), jongyu.end(), (H - i)) - jongyu.begin();

		// jIdx ~ : 내가 뚫어야 함
		// jongyu.size() - jCnt : 내가 뚫어햐 할 개수

		curBreak = N - sIdx - jIdx;
		
		// 새로 갱신되는 경우, cnt도 초기화
		if (curBreak < minBreak) {
			minBreak = curBreak;
			cnt = 1;
		}
		// 최소값이 같은 경우 ++개수
		else if (curBreak == minBreak) {
			++cnt;
		}
	}

	// 결과값 출력
	cout << minBreak << ' ' << cnt << '\n';

	return 0;
}
