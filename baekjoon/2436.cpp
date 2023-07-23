#include<iostream>
#include<cmath>
using namespace std;
int Gcd, Lcm;

int findGcd(int a, int b) {  //구글링함ㅎ
    if(b==0) return a;
    return findGcd(b, a%b);
}

int main() {
    cin >> Gcd >> Lcm;
    long long num1 = Gcd, num2 = Lcm;
    for(int i=1; i <= sqrt(Lcm/Gcd); i++){
        if((Lcm/Gcd) % i == 0){
            if(findGcd(i, (Lcm/Gcd) / i) == 1) {
                num1 = i*Gcd;
                num2 = (Lcm/Gcd) / i * Gcd;
            }
        }
     }

    cout << num1 << ' ' << num2;
}