#include<iostream>
#include<cstring>
#include<vector>
#include<set>
using namespace std;
int N;
bool Prime[5000001];
set<int, greater<int>> A, B;

void setPrime() {
    Prime[0] = Prime[1] = false;

    for (int i = 2; i*i <= 5000000; i++) {
        if (Prime[i]) {
            for (int j = i * i; j <= 5000000; j += i) {
                Prime[j] = false;
            }
        }
    }
}

int findThird(set<int, greater<int>>& s) {
    if(s.size() < 3) {
        return 1000;
    }
    auto it = s.begin();
    for(int i=0; i<2; i++) {
        it++;
    }
    return *it;
}
int main() {
    memset(Prime, true, sizeof(Prime));
    setPrime();

    cin >> N;
    long long scoreA = 0, scoreB = 0;

    for (int i = 0; i < N; i++) {
        int a, b;
        cin >> a >> b;
        if (!Prime[a]) {
            scoreB += findThird(B);
        }
        else if(A.find(a) != A.end() || B.find(a) != B.end()){
            scoreA -= 1000;
        }
        else {
            A.insert(a);
        }

        if (!Prime[b]) {
            scoreA += findThird(A);
        }
        else if(A.find(b) != A.end() || B.find(b) != B.end()){
            scoreB -= 1000;
        }
        else {
            B.insert(b);
        }
    }

    if(scoreA > scoreB) {
        cout << "소수의 신 갓대웅";
    }
    else if(scoreA < scoreB) {
        cout << "소수 마스터 갓규성";
    }
    else {
        cout << "우열을 가릴 수 없음" ;
    }
}