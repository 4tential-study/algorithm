#include<iostream>
#include<algorithm>
#define INF 10000
using namespace std;
int N, M, R;
int Cost[101][101];
int Item[101];
int main() {
	cin >> N >> M >> R;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			Cost[i][j] = INF;
			if (i == j) {
				Cost[i][j] = 0;
			}
		}
	}
	for (int i = 0; i < N; i++) {
		cin >> Item[i + 1];
	}
	for (int i = 0; i < R; i++) {
		int a, b, l;
		cin >> a >> b >> l;
		Cost[a][b] = l;
		Cost[b][a] = l;
	}
	
	for (int k = 1; k <= N; k++) {  // k 는 거쳐가는 정점
		for (int i = 1; i <= N; i++) {  // i 는 행 (출발 정점)
			for (int j = 1; j <= N; j++) {  // j 는 열 (도착 정점)
				if (Cost[i][k] + Cost[k][j] < Cost[i][j]) {
					Cost[i][j] = Cost[i][k] + Cost[k][j];
				}
			}
		}
	}
	int ans = 0;
	for (int i = 1; i <= N; i++) {
		int items = Item[i];
		for (int j = 1; j <= N; j++) {
			if (i != j && Cost[i][j] <= M) {
				items += Item[j];
			}
		}
		ans = max(ans, items);
	}
	cout << ans;
}