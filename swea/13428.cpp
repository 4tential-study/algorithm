#include<iostream>
#include<algorithm>
#include<cmath>
using namespace std;
int Nums[10];
int Cnt;
 
int toNum() {
    int num = 0;
    for (int i = 0; i < Cnt; i++) {
        num = num * 10 + Nums[i];
    }
    return num;
}
 
int main() {
    int T;
    cin >> T;
    for (int t = 0; t < T; t++) {
        int n;
        cin >> n;
        Cnt = 0;
        int temp = n;   
        while (temp != 0) {
            Nums[Cnt++] = temp % 10;
            temp /= 10;
        }
        reverse(Nums, Nums + Cnt);
 
        int minN=n, maxN=n;
        int threshold = pow(10, Cnt - 1);
 
        for (int i = 0; i < Cnt; i++) {
            for (int j = i+1; j < Cnt; j++) {
                swap(Nums[i], Nums[j]);
                int candi = toNum();
                if (candi >= threshold) {
                    minN = min(candi, minN);
                    maxN = max(candi, maxN);
                }
                swap(Nums[i], Nums[j]);
            }
        }
         
        cout << '#' << t+1 << ' ' << minN << ' ' << maxN << '\n';
    }   
}