#include <iostream>
using namespace std;

int Coin[101];
long Dp[10001];
int N, K;

int main(){
    cin.tie(0);
    cout.tie(0);
    ios::sync_with_stdio(false);

    cin >> N >> K;

    for (int i = 0; i < N; i++){
        cin >> Coin[i];
    }
    Dp[0] = 1;
    for(int i = 0; i < N; i++){
        for(int j = Coin[i]; j <= K; j++){
            Dp[j] = Dp[j] + Dp[j-Coin[i]];
        }
    }
    cout << Dp[K];
}