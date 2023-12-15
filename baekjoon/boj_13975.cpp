#include <iostream>
#include <algorithm>
#include <string.h>
#include <climits>
#include <cassert>
#include <cmath>
#include <queue>
#pragma warning(disable:4996)
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;

priority_queue<ll,vector<ll>, greater<ll>> q;
int N;

void init() {
	cin >> N;
	while (!q.empty()) q.pop();
	for (int i = 0; i < N; i++) {
		int x; cin >> x;
		q.push(x);
	}
}

ll solve() {
	ll sum = 0;
	while (q.size() > 1) {
		ll a = q.top(); q.pop();
		ll b = q.top(); q.pop();
		sum += a + b;
		q.push(a + b);
	}
	return sum;
}

int main() {
	freopen("sample_input.txt", "r", stdin);
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	int tc; cin >> tc;
	while (tc--) {
		init();
		cout << solve() << endl;
	}
	

	fclose(stdin);
}