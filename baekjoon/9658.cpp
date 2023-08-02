#include<iostream>
#include<string>
using namespace std;
int N;
int Dp[1001]; 

int main() {
	cin >> N;

	Dp[1] = 0;
	Dp[2] = 1;
	Dp[3] = 0;
	Dp[4] = 1;

	for (int i = 5; i <= N; i++) {
		Dp[i] = !Dp[i - 1] || !Dp[i - 3] || !Dp[i - 4];
	}

	string ans = Dp[N] ? "SK" : "CY";
	cout << ans;
}