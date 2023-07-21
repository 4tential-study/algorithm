#include<iostream>
#include<algorithm>
using namespace std;
int N;
int Stair[301];
int Dp[301][2];//

int main() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> Stair[i];
	}

	Dp[0][0] = Dp[0][1] = Stair[0];
	Dp[1][0] = Stair[1];
	Dp[1][1] = Stair[0] + Stair[1];

	for (int i = 2; i < N; i++) {
		Dp[i][0] = max(Dp[i - 2][0], Dp[i - 2][1]) + Stair[i];
		Dp[i][1] = Dp[i - 1][0] + Stair[i];
	}
	cout << max(Dp[N - 1][0], Dp[N - 1][1]);
}