#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;
int N, Min = 1000000000, Max = -1000000000;
int A[12];

int main() {
    cin >> N;
    for(int i=0; i<N; i++){
        cin >> A[i];
    }

    vector<int> temp;
    for(int i=1; i<=4; i++){
        int cnt;
        cin >> cnt;
        for(int j=0; j<cnt; j++){
            temp.push_back(i);
        }
    }

    do{
        int num=A[0], idx = 1;
        for(int oper : temp) {
            switch (oper) {
            case 1:
                num += A[idx++];
                break;
            case 2:
                num -= A[idx++];
                break;
            case 3:
                num *= A[idx++];
                break;
            case 4:
                num /= A[idx++];
                break;
            }
        }
        Min = min(Min, num);
        Max = max(Max, num);
    }while(next_permutation(temp.begin(), temp.end()));
    
    cout << Max << '\n' << Min;
}