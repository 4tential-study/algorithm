#include <iostream>
#include <vector>
using namespace std;

// 팩토리얼 미리 만들어놓기
long long fa[21];

// 사용했는가
bool used[21];

void Factorial(int N) {
	fa[0] = fa[1] = 1;
	for (int i = 2; i <= N; ++i) fa[i] = fa[i - 1] * i;
}

int main() {
	int N; cin >> N;
	int t;  cin >> t;

	Factorial(N);
	
	// IDEA
	// N = 4인 경우
	// 첫번째 자리 하나에 3*2*1의 경우가 존재
	// 첫번쨰, 두번쨰 자리가 정해진 경우 2*1의 경우
	// 요런 느낌으로

	if (t == 1) {
		// K번째
		long long K;  cin >> K;

		vector<int> res;

		// solve
		for (int i = 1; i <= N; ++i) { // 1번째 자리 ~ N번쨰 자리
			for (int j = 1; j <= N; ++j) { // 자리에 들어가는 숫자
				// 이미 사용한 숫자면 패스
				if (used[j]) continue;

				// fa[N - i] : 해당 자리수가 가지는 경우의 수

				if (fa[N - i] < K) K -= fa[N - i];
				else {
					res.push_back(j);
					used[j] = true;
					break;
				}
			}
		}

		// output
		for (int i = 0; i < res.size(); ++i) {
			cout << res[i];
			if (i != N - 1) cout << ' ';
			else cout << '\n';
		}
	}
	// 해당 순열이 몇 번쨰 순열인지
	else{
		// 순열 입력 받기
		int value[21];
		for (int i = 1; i <= N; ++i) cin >> value[i];

		long long ans = 1;

		// solve
		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j < value[i]; ++j) {
				if (used[j]) continue;

				// 순서 더해주기
				ans += fa[N - i];
			}

			used[value[i]] = true;
		}

		// output
		cout << ans << '\n';
	}

	return 0;
}

// 마지막 자리의 경우 f[0]을 쓰기 때문에 이것도 초기화...
// 여기서 1 FAIL
// long long 안써서 1 FAIL