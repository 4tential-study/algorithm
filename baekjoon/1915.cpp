#include<iostream>
#include<algorithm>
using namespace std;
int N, M;
int Arr[1001][1001];

int main() {
    cin >> N >> M;
    char c;
    int len = 0;
    for(int i=0; i<N; i++) {
        for(int j=0; j<M; j++) {
            cin >> c;
            Arr[i][j] = (c-'0');
            if(i>0 && j>0 && Arr[i][j]) {
                Arr[i][j] += min(min(Arr[i-1][j], Arr[i][j-1]), Arr[i-1][j-1]);
            }
            len = max(len, Arr[i][j]);
        }
    }

    cout << len*len << '\n';
}