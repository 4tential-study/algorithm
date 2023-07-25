#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int N, Min = 9000;
int S[21][21];
vector<int> Team, Exp;

int cal(){
	int sum=0;
	for (int i = 0; i < N/2; i++) {
		for (int j = i + 1; j < N/2; j++) {
			int x = Team[i], y = Team[j];
			sum += (S[x][y] + S[y][x]);
		}
	}
	return sum;
}

void comb(int cnt, int next) {
	if (cnt == N / 2) {
		Exp.push_back(cal());
		return;
	}
	for (int i = next; i < N; i++) {
		Team[cnt] = i;
		comb(cnt + 1, i + 1);
	}

}

int main() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> S[i][j];
		}
	}
	Team.resize(N / 2);
	comb(0, 0);

	for (int i = 0; i < Exp.size() / 2; i++) {
		Min = min(Min, abs(Exp[i] - Exp[Exp.size()-i-1]));
	}
	cout << Min;
}