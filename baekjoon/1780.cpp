#include<iostream>
#include<map>
#include<cmath>
using namespace std;
int N;
int Paper[2188][2188];
map<int, int> Ans;

int determine(int x, int y, int size) {
	if (size == 1) {
		return Paper[x][y];
	}
	int nSize = size / 3;
	map<int, int> cnt;
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			int num = determine(x + i * nSize, y + j * nSize, nSize);
			cnt[num]++;
		}
	}
	if (cnt[-1] == 9) return -1;
	else if (cnt[0] == 9) return 0;
	else if (cnt[1] == 9) return 1;

	Ans[0] += cnt[0];
	Ans[1] += cnt[1];
	Ans[-1] += cnt[-1];
	return -2;
}
int main() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> Paper[i][j];
		}
	}

	int n = determine(0, 0, N);
	Ans[n]++;
	
	cout << Ans[-1] << '\n' << Ans[0] << '\n' << Ans[1];
}