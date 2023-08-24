#include<iostream>
#include<cmath>
using namespace std;
 
int T, K;
int Magnetic[4][8];
int Idx[4][2] = { {6, 2}, {6, 2} , {6, 2} , {6, 2} };
 
void rotate(int m, int d) {
    int first = Magnetic[0][Idx[0][1]] != Magnetic[1][Idx[1][0]];
    int second = Magnetic[1][Idx[1][1]] != Magnetic[2][Idx[2][0]];
    int third = Magnetic[2][Idx[2][1]] != Magnetic[3][Idx[3][0]];
 
    int avail[4] = { 0, first, second, third };
    Idx[m][0] = (Idx[m][0] + d + 8) % 8;
    Idx[m][1] = (Idx[m][1] + d + 8) % 8;
 
    int l = m, r = m, lD = d, rD = d;
    while (r + 1 < 4 && avail[r + 1]) {
        rD *= -1;
        r++;
        Idx[r][0] = (Idx[r][0] + rD + 8) % 8;
        Idx[r][1] = (Idx[r][1] + rD + 8) % 8;
    }
    while (l >= 0 && avail[l]) {
        lD *= -1;
        l--;
        Idx[l][0] = (Idx[l][0] + lD + 8) % 8;
        Idx[l][1] = (Idx[l][1] + lD + 8) % 8;
    }
}
 
 
int main() {
    cin >> T;
    for (int t = 1; t <= T; t++) {
        cin >> K;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                cin >> Magnetic[i][j];
            }
        }
        for (int i = 0; i < K; i++) {
            int m, d;
            cin >> m >> d;
            rotate(m - 1, (-1)*d);
        }
        int score = 0;
        for (int i = 0; i < 4; i++) {
            int topIdx = (Idx[i][0] + 2) % 8;
            if (Magnetic[i][topIdx]) {
                score += pow(2, i);
            }
        }
        Idx[0][0] = 6, Idx[0][1] = 2;
        Idx[1][0] = 6, Idx[1][1] = 2;
        Idx[2][0] = 6, Idx[2][1] = 2;
        Idx[3][0] = 6, Idx[3][1] = 2;
 
        cout << '#' << t << ' ';
        cout << score << '\n';
    }
}