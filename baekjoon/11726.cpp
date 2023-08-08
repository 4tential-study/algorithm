#include<iostream>
#define MOD 10007
using namespace std;
int N;
int Dp[1001];

int main() {
	cin >> N;
	Dp[1] = 1;
	Dp[2] = 2;
	for (int i = 3; i <= N; i++) {
		Dp[i] = (Dp[i - 1] + Dp[i - 2]) % MOD;
	}
	cout << Dp[N];
}