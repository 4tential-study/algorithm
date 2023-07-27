#include<iostream>
#include<algorithm>
using namespace std;
int N, Min = 2000000000;
int W[11][11];
int Visited[11];

void solve(int cur, int cnt, int sum) {
	if (cnt == N) {
		if (W[cur][0] > 0)
			Min = min(Min, sum + W[cur][0]);
		return;
	}

	Visited[cur] = 1;

	for (int i = 0; i < N; i++) {
		if (W[cur][i] > 0 && !Visited[i]) {
			solve(i, cnt + 1, sum + W[cur][i]);
		}
	}

	Visited[cur] = 0;
}

int main() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> W[i][j];
		}
	}

	solve(0, 1, 0);
	
	cout << Min;
}