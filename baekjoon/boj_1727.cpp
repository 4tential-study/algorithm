#include <iostream>
#include <algorithm>
#include <string.h>
#include <climits>
#include <cassert>
#include <cmath>
#include <queue>
#pragma warning(disable:4996)
using namespace std;
typedef pair<int, int> pii;


int dp[1001][1001];
int arr1[1001], arr2[1001];
int N, M;

void init() {
	cin >> N >> M;
	for (int i = 0; i < N; i++) cin >> arr1[i];
	for (int i = 0; i < M; i++) cin >> arr2[i];
	sort(arr1, arr1 + N);
	sort(arr2, arr2 + M);
	memset(dp, -1, sizeof(dp));
}



int solve1(int cur1,int cur2) {//N이 작을 때
	if (cur1 == N) return 0;
	else if (cur2 == M) return INT_MAX;
	
	int& ret = dp[cur1][cur2];
	if (ret != -1) return ret;

	ret = INT_MAX;
	
	for (int i = cur2; i < M; i++) {
		int ret1 = solve1(cur1 + 1, i + 1);
		if (ret1 == INT_MAX) continue;
		ret = min(ret, ret1 + abs(arr1[cur1] - arr2[i]));
	}
	return ret;
}
 
int solve2(int cur1, int cur2) {//cur2는 무조건 arr1에서 짝을 선택해야함
	if (cur2 == M) return 0;
	else if (cur1 == N) return INT_MAX;

	int& ret = dp[cur1][cur2];
	if (ret != -1) return ret;

	ret = INT_MAX;

	for (int i = cur1; i < N; i++) {
		int ret1 = solve2(i + 1, cur2 + 1);
		if (ret1 == INT_MAX) continue;
		ret = min(ret, ret1 + abs(arr1[i] - arr2[cur2]));
	}
	return ret;
}

int main() {
	freopen("sample_input.txt", "r", stdin);
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	init();
	int ans = -1;
	if (N <= M) {
		ans=solve1(0, 0);
	}
	else {//N > M
		ans=solve2(0, 0);
	}
	if (ans == -1) assert(0);
	cout << ans << endl;

	fclose(stdin);
}