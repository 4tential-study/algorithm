#include<iostream>
#include<queue>

using namespace std;
typedef long long ll;

int N;

priority_queue<ll, vector<ll>, greater<ll>> pq;

int main() {
	int T; cin >> T;

	for (int tc = 0; tc < T; ++tc) {
		 cin >> N;

		 ll ans = 0;
		 
		 int tmp;
		 for (int i = 0; i < N; ++i) {
			 cin >> tmp;
			 pq.push(tmp);
		 }

		 while (pq.size() > 1) {
			 ll k = pq.top();
			 pq.pop();
			 k += pq.top();
			 pq.pop();

			 pq.push(k);
			 ans += k;
		 }

		 pq.pop();
		 cout << ans << '\n';
	}

	return 0;
}