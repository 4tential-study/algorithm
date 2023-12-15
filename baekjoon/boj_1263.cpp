#include <iostream>
#include <algorithm>
#include <string.h>
#include <climits>
#include <cassert>
#include <cmath>
#include <vector>
#pragma warning(disable:4996)
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;

int N;
vector<pii> v;

void init() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		int t,e; cin >> t>> e; //걸리는 시간, 마감시간
		v.push_back({e,t}); //마감시간 순으로 정렬하기 위해 순서 바꿔넣기
	}
	sort(v.begin(), v.end());
}

void solve() {
	int cur = 1000000;
	for (int i = N - 1; i >= 0; i--) {
		int e = v[i].first;
		int t = v[i].second;
		cur = min(cur, e);
		cur -= t;
	}
	if (cur < 0) cout << -1 << endl;
	else cout << cur << endl;
}

int main() {
	freopen("sample_input.txt", "r", stdin);
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	init();
	solve();

	fclose(stdin);
}