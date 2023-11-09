#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int N;

// 1022번쨰 수가 9876543210이므로 v의 max_size = 1023
vector <long long> v;

// x의 뒤에 x보다 작은 수를 붙여서 체크하기.
void finds(long long x) {
	int last = x % 10;

	for (int i = 0; i < last; ++i) {
		long long next = x * 10 + i;
		v.push_back(next);
		finds(next);
	}
}

int main() {

	cin >> N;

	// i로 시작하는 수 찾기
	// 0 -> 0, 1 -> 1 이므로 0도 추가해주기
	for (int i = 0; i <= 9; ++i) {
		v.push_back(i);
		finds(i);
	}

	// 정렬
	sort(v.begin(), v.end());

	// N번쨰 수가 존재하지 않는 경우 -1
	if (N >= v.size()) cout << -1 << '\n';
	else cout << v[N] << '\n';

	return 0;
}