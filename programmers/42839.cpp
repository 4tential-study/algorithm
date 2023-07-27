#include <string>
#include <set>
#include <algorithm>
using namespace std;
set<int> Nums;
int solution(string numbers) {
    int answer = 0;

    for (int i = 1; i <= numbers.size(); i++) {
        sort(numbers.begin(), numbers.end());
        do {
            string s = numbers.substr(0, i);
            int num = stoi(s);
            if (num > 1 && Nums.count(num) == 0) {
                int t = 2;
                while (t*t <= num && !(num % t == 0)) {
                    t++;
                }
                if (t*t > num) {
                    answer++;
                }
            }
            Nums.insert(num);
            reverse(numbers.begin() + i, numbers.end());
        } while (next_permutation(numbers.begin(), numbers.end()));

    }

    return answer;
}
