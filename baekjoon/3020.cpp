#include<iostream>
#include<algorithm>
using namespace std;
int N, H;
int Stalagmite[100001];
int Stalactite[100001];

int main() {
	cin >> N >> H;
	for (int i = 0; i < N; i++) {
		if (i % 2 == 0) {
			cin >> Stalagmite[i / 2];
		}
		else {
			cin >> Stalactite[i / 2];
		}
	}
	sort(Stalactite, Stalactite + N / 2);
	sort(Stalagmite, Stalagmite + N / 2);

	int minCnt = N, cntStal = 0;

	for (int h = 0; h < H; h++) {
		int idx1 = lower_bound(Stalagmite, Stalagmite + N / 2, h + 1) - Stalagmite;
		int cnt1 = N / 2 - idx1;

		int idx2 = lower_bound(Stalactite, Stalactite + N / 2, H - h) - Stalactite;
		int cnt2 = N / 2 - idx2;

		int cnt = cnt1 + cnt2;
		if (cnt < minCnt) {
			minCnt = cnt;
			cntStal = 1;
		}
		else if (cnt == minCnt) {
			cntStal++;
		}
	}
	cout << minCnt << " " << cntStal;
}