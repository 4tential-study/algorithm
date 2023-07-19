#include<bits/stdc++.h>
using namespace std;
int T, N;
int Namu[10001];

int main(){
    cin >> T;
    for(int i=0; i<T; i++){
        cin >> N;
        for(int j=0; j<N; j++){
            cin >> Namu[j];
        }
        int level = 0;

        sort(Namu, Namu+N);

        stack<int> left, right;
        left.push(Namu[0]);
        right.push(Namu[0]);
        
        for(int j=1; j<N; j++){
            if(j%2==0) {
                level = max(level, Namu[j]-right.top());
                right.push(Namu[j]);
            }
            else {
                level = max(level, Namu[j]-left.top());
                left.push(Namu[j]);
            }
        }
        level = max(level, abs(right.top()-left.top()));
        cout << level << '\n';
    }
}