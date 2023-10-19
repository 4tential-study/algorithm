#include <iostream>
#include <string.h>
#include <algorithm>
#include <climits>
#include <cmath>
#include <cassert>
#include <vector>
#define MAX 987654321
#pragma warning(disable:4996)
using namespace std;
typedef long long int ll;
typedef pair<int, int> pii;
#define endl '\n'


vector<int> v;
double C;
int N;
int maxv;
void init() {
	cin >> C >> N;
	int cnt = 0;
	for (int i = 0; i < N; i++) {
		int x; cin >> x;
		maxv = max(maxv, x);
		if (x != 0) cnt++;
		else {
			if (cnt > 0) v.push_back(cnt);
			v.push_back(0);
			cnt = 0;
		}
	}
	if (cnt > 0) v.push_back(cnt);
}

void solve() {
	//2개 이상 스트릭을 살 수 있어도 최대 2개만 사용할 수 있음
	int canBuyCnt = (int)floor(C / (0.99));
	int chance = min(canBuyCnt, 2);

	int count = 0;
	//스트릭 유지 연속 최대 일수 cnt 구하기
	for (int i = 0; i < v.size(); i++) {//start:i
		int k = chance;
		int cnt = 0;

		for (int j = i; j < v.size(); j++) {//end 후보 j
			if (v[j] == 0) {//스트릭을 사용해야하는 경우
				if (k == 0) break;
				else {//스트릭이 있을 때
					k--; cnt++;
				}
			}
			else cnt += v[j];
		}
		count = max(count, cnt);
	}
	cout << count << endl;
	cout << maxv << endl;
}

int main() {
	freopen("sample_input.txt", "r", stdin);
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	init();
	solve();

	fclose(stdin);
}
