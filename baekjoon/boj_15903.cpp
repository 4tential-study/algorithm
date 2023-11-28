#include<iostream>
#include<set>
using namespace std;
typedef long long ll;
int N, M;

multiset<ll> s;

int main() {
	cin >> N >> M;

	int tmp;
	for (int i = 0; i < N; ++i) {
		cin >> tmp;
		s.insert(tmp);
	}

	for (int i = 0; i < M; ++i) {
		// 최소값
		ll tmp = *s.begin();
		s.erase(s.begin());

		tmp += *s.begin();
		s.erase(s.begin());

		s.insert(tmp);
		s.insert(tmp);
	}

	// 합 구하기
	ll res = 0;
	for (multiset<ll>::iterator iter = s.begin(); iter != s.end(); ++iter) {
		res += *iter;
	}

	cout << res << '\n';

	return 0;
}
