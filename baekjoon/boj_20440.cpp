#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N;

int arr[2000001];

vector<int> m;

vector<pair<int, int>> v;

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	cin >> N;

	int a, b;
	for (int i = 0; i < N; ++i) {
		// a ~ b 시간에 모기가 존재한다.
		cin >> a >> b;
	
		v.push_back({ a, b });
		m.push_back(a);
		m.push_back(b);
	}

	// 좌표 압축
	sort(m.begin(), m.end());
	m.erase(unique(m.begin(), m.end()), m.end());

	// 값의 크기 순서대로 새로 번호를 정해준다.(m의 index)

	sort(v.begin(), v.end());
	for (int i = 0; i < N; ++i) {
		// 모기가 들어오고 나가는 시간의 누적합을 구하기 위해 체크.
		// index를 빠르게 찾기 위해서 이분탐색 사용
		++arr[lower_bound(m.begin(), m.end(), v[i].first) - m.begin()];
		--arr[lower_bound(m.begin(), m.end(), v[i].second) - m.begin()];
	}

	// 여기는 각자의 취향으로...
	int maxV = 0, st = 0, en = 0;
	int tmp = 0, tst = 0, ten = 0;
	for (int i = 0; i < m.size(); ++i) {
		if (arr[i] > 0) {
			tst = m[i];
		}
		else if (arr[i] < 0) {
			ten = m[i];

			// 갱신되는 경우
			if (tmp > maxV) {
				maxV = tmp;
				st = tst;
				en  = ten;
			}
		}

		tmp += arr[i];
	}

	cout << maxV << '\n';
	cout << st << ' ' << en << '\n';

	return 0;
}

// 상수값이 생각보다 커서 map으로 하면 시간초과가 나오네요...