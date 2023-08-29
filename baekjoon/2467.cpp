#include<iostream>
using namespace std;
int N;
int Liquid[100001];

int main() {
    cin >> N;
    for(int i=0; i<N; i++) {
        cin >> Liquid[i];
    }
    
    int begin = 0, end = N-1;
    int minCV = abs(Liquid[begin] + Liquid[end]);
    int a = begin, b = end;
    while(begin < end) {
        int cv = Liquid[begin] + Liquid[end];
        if(abs(cv) < minCV) {
            minCV = abs(cv);
            a = begin;
            b = end;
        }

        if(cv < 0) {
            begin++;
        }
        else {
            end--;
        }
    }
    cout << Liquid[a] << ' ' << Liquid[b];
}