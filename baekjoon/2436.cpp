#include<iostream>
#include<cmath>
using namespace std;
int Gcd, Lcm;

// int findGcd(int a, int b) {  //구글링함ㅎ
//     if(b==0) return a;
//     return findGcd(b, a%b);
// }

int main() {
    cin >> Gcd >> Lcm;
    long long num1 = Gcd, num2 = Lcm;
    for(int i=1; i < Lcm/Gcd; i++){

        for(int j=i+1; j<Lcm/Gcd; j++){
            long long a = Gcd*i;
            long long b = Gcd*j;
            if(a * b / Gcd == Lcm) {
                if(num1 + num2 > a + b) {
                    num1 = a;
                    num2 = b;
                }
            }
        }
    }

    cout << num1 << ' ' << num2;
}