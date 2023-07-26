#include<iostream>
using namespace std;
int N, K;
long long Cnt[202][202]={{0,},};
int main(){
    cin >> N >> K;
    for(int i=0; i<=N; ++i) Cnt[1][i] = 1;
    
    for(int k=2; k<=K; ++k){
        for(int n=0; n<=N; ++n){
            long long c = 0;
            for(int i=0; i<=n; ++i){
                c += Cnt[k-1][i];
                c %= 1000000000;
            }
            Cnt[k][n] = c;
        }
    }
    cout << Cnt[K][N];
}