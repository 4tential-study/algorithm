#include<iostream>
using namespace std;

int Coin[21];
long Dp[10001];
int T, N, M;

int main(){
    cin >> T;
    for(int i=0; i<T; i++){
        cin >> N;
        for(int j=0; j<N; j++){
            cin >> Coin[j];
        }
        cin >> M;
        Dp[0] = 1;

        for(int j=0; j < N; j++){
            for(int k=Coin[j]; k<=M; k++){
                Dp[k] += Dp[k - Coin[j]];
            }
        }
        cout << Dp[M] << '\n';
        for(int j=0; j<=M; j++){
            Dp[j]=0;
        }
    }
}