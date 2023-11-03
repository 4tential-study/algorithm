#include<iostream>
#include<vector>
#include<map>
#include<algorithm>
using namespace std;
int N;
unordered_map<int, int> T;

int main() {
    int mE, mX, mM=0;
    cin >> N;
    for(int i=0; i<N; i++) {
        int e, x;
        cin >> e >> x;
        T[e]++;
        T[x]--;
    }
    vector<pair<int,int>> temp(T.begin(), T.end());
    sort(temp.begin(), temp.end());
    int cnt=0, e, x;
    for(pair<int,int>& t : temp) {
        if(t.second > 0) {
            e = t.first;
        }
        else if(t.second < 0) {
            x = t.first;
            if(cnt > mM) {
                mM = cnt;
                mE = e;
                mX = x;
            }
        }
        cnt += t.second;
        
    }
    cout << mM << '\n' << mE << ' ' << mX << '\n';
}