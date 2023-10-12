#include <iostream>
#include <string.h>
#include <algorithm>
#include <climits>
#include <cmath>
#include <cassert>
#include <unordered_map>
#include <unordered_set>
#include <queue>
#include <time.h>
#define MAX 987654321
#pragma warning(disable:4996)
using namespace std;
typedef long long int ll;
typedef pair<int, int> pii;
#define endl '\n'


int N, H;
vector<int> upv;
vector<int> downv;

void init() {
	cin >> N >> H;
	for (int i = 0; i < N / 2; i++) {
		int x; cin >> x;
		upv.push_back(x);
		cin >> x;
		downv.push_back(x);
	}

	sort(upv.begin(), upv.end());
	sort(downv.begin(), downv.end());
}

void solve() {
	int ret = INT_MAX, cnt = 0;
	for (int i = 1; i <= H; i++) {
		int x1 = i, x2 = H - i +1;
		int idx1 = lower_bound(upv.begin(), upv.end(), x1) - upv.begin();
		int upCnt = N/2 - idx1;
		int idx2 = lower_bound(downv.begin(), downv.end(), x2) - downv.begin();
		int downCnt = N/2 - idx2;
		int Cnt = upCnt + downCnt;
		
		if (ret > Cnt) {
			ret = Cnt; cnt = 1;
		}
		else if (ret == Cnt) {
			cnt++;
		}
	}
	cout << ret << " " << cnt << endl;
}

int main() {
	freopen("sample_input.txt", "r", stdin);
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	init();
	solve();


	fclose(stdin);
}
