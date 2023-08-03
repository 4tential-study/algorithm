#include<bits/stdc++.h>
using namespace std;
int T, N;
long long cnt[1000001];

int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> T;
    
    for(int i=0; i<T; ++i){
        cin >> N;   
        cnt[1] = 1; cnt[2] = 2; cnt[3] = 4;
        for(int j=4; j<=N; ++j){
            cnt[j] = cnt[j-1] + cnt[j-2] + cnt[j-3];
            cnt[j] %= 1000000009;
        }
        cout << cnt[N] << endl;
    }
}
