#include<iostream>
using namespace std;
int N;
int Dp[101][10];  //길이, 끝나는자리 수

int main() {
	cin >> N;
	Dp[1][0] = 0;
	for (int i = 1; i <= 9; i++) {
		Dp[1][i] = 1;
	}
	for (int i = 2; i <= N; i++) {
		Dp[i][0] = Dp[i - 1][1];
		Dp[i][9] = Dp[i - 1][8];
		for (int j = 1; j <= 8; j++) {
			Dp[i][j] = (Dp[i - 1][j - 1] + Dp[i - 1][j + 1]) % 1000000000;
		}
	}
	int sum = 0;
	for (int i = 0; i <= 9; i++) {
		sum = (sum + Dp[N][i]) % 1000000000;
	}
	cout << sum;
}