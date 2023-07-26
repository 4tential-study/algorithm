#include<bits/stdc++.h>
using namespace std;
int N;
long Dp[100001][4]={{0,},};  // blank / xo / ox / xx
int main(){
    cin >> N;
    Dp[1][1] = Dp[1][2] = Dp[1][3] = 1; 
    for(int i=2; i<=N; ++i){
        Dp[i][1] = (Dp[i-1][2] + Dp[i-1][3]) % 9901;
        Dp[i][2] = (Dp[i-1][1] + Dp[i-1][3]) % 9901;
        Dp[i][3] = (Dp[i-1][1] + Dp[i-1][2] + Dp[i-1][3]) % 9901;
    }
    cout << (Dp[N][1] + Dp[N][2] + Dp[N][3]) % 9901;
}