#include<iostream>
#include<algorithm>
using namespace std;
int T, N, L;
int Ants[100001];

int main() {
    cin >> T; 
    for (int t = 0; t < T; t++) {
        cin >> L >> N;
        for (int i = 0; i < N; i++) {
            cin >> Ants[i];
        }
        sort(Ants, Ants + N);
        int fastest = 0;
        for (int i = 0; i < N; i++) {
            if (Ants[i] < L / 2) {
                if (Ants[i] > fastest) {
                    fastest = Ants[i];
                }
            }
            else {
                if (L - Ants[i] > fastest) {
                    fastest = L - Ants[i];
                }
            }
        }
        int latest = max(L - Ants[0],  Ants[N-1]);

        cout << fastest << ' ' << latest;
    } 
}