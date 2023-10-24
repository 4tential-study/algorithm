#include <iostream>
#include <algorithm>

using namespace std;

int N, M;

int man[1001];
int girl[1001];

int dp[1001][1001];

// 그냥 답 봤습니다 문제 너무 어렵네요

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	cin >> N >> M;

	for (int i = 0; i < N; ++i) cin >> man[i];
	for (int i = 0; i < M; ++i) cin >> girl[i];

	sort(man, man + N);
	sort(girl, girl + M);

	// 1. dp[i-1][j-1]에서 남 1, 여1 추가 된 경우
	// 2. dp[i-1][j]에서 여 1 추가된 경우
	// 3. dp[i][j-1]에서 남 1 추가된 경우
	// -> dp[i][j]이다.

	// 1의 경우 추가된 친구 둘 커플시켜
	// 2, 3의 경우 새로 추가된 친구가 커플을 맺은 경우 아닌 경우
	// 비교
	
	// 정렬된 상태기 때문에 새로 추가된 친구가 마지막 친구와 사귀는 경우만 체크하면 됨

	for (int i = 1; i <= N; ++i) {
		for (int j = 1; j <= M; ++j) {
			if (i == j) {
				dp[i][j] = dp[i - 1][j - 1] + abs(man[i - 1] - girl[j - 1]);
			}
			else if (i > j) {
				dp[i][j] = min(dp[i - 1][j - 1] + abs(man[i - 1] - girl[j - 1]), dp[i-1][j]);
			}
			else {
				dp[i][j] = min(dp[i - 1][j - 1] + abs(man[i - 1] - girl[j - 1]), dp[i][j-1]);
			}
		}
	}

	cout << dp[N][M] << '\n';

	return 0;
}