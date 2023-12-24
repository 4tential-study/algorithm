#include <iostream>
#include <string.h>
#include <algorithm>
#include <climits>
#include <cmath>
#include <cassert>
#include <vector>
#include <stack>
#define MAX 987654321
#pragma warning(disable:4996)
using namespace std;
typedef long long int ll;
typedef pair<int, int> pii;
#define endl '\n'

int arr[501];
int sum[501];
int dp[501][501];
int N;

void init() {
	memset(dp, -1, sizeof(dp));
	memset(arr, 0, sizeof(arr));
	memset(sum, 0, sizeof(sum));

	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
		sum[i] = sum[i - 1] + arr[i]; //각 파일 용량 꺼내쓰기위한 누적합
	}
}

//[start,end]파일을 만드는데 드는 최소 비용
int calc(int start, int end) {
	if (start == end) return 0;
	assert(start <= end);

	int& ret = dp[start][end];
	if (ret != -1) return ret;

	ret = INT_MAX;
	//[start, i], [i+1, end]를 합치는 경우로 분기
	for (int i = start; i < end; i++) {
		int sum1 = sum[i] - sum[start - 1];
		int sum2 = sum[end] - sum[i];
		int ret1 = calc(start, i);
		int ret2 = calc(i + 1, end);
		ret = min(ret, ret1 + ret2 +sum1+sum2);
	}
	return ret;
}

void solve() {
	int ans = calc(1, N);
	cout << ans << endl;
}

int main() {
	freopen("sample_input.txt", "r", stdin);

	int tc; cin >> tc;
	while (tc--) {
		init();
		solve();
	}
	

	fclose(stdin);
}