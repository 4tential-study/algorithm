#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;
int N, T;
int Dp[10001][4];
vector<int> TestCase;

int main() {
    cin >> T;
    for(int i=0; i<T; i++){
        cin >> N;
        TestCase.push_back(N);
    }
    int m = *max_element(TestCase.begin(), TestCase.end());
    Dp[1][1] = 1;
    Dp[2][1] = 1, Dp[2][2] = 1;
    Dp[3][1] = 1, Dp[3][2] = 1, Dp[3][3] = 1;

    for(int i=4; i<=m; i++){
        Dp[i][1] = 1;
        Dp[i][2] = Dp[i-2][2] + Dp[i-2][1];
        Dp[i][3] = Dp[i-3][3] + Dp[i-3][2] + Dp[i-3][1];    
    }

    for(int n : TestCase) {
        cout << Dp[n][3] + Dp[n][2] + Dp[n][1] << '\n';
    }
}