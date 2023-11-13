#include <iostream>
#include <vector>
using namespace std;

int N, k;

// 이진트리라는 조건이 없었네여 -> 다가족 가능
vector<int> jasik[51];

bool visited[51];

// k의 자식들 방문 체크
void check(int x) {
	visited[x] = true;

	for (int i = 0; i < jasik[x].size(); ++i) {
		check(jasik[x][i]);
	}
}

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	cin >> N;
	
	// -1이 있기 때문에 전부 +1 했어요
	int tmp;
	for (int i = 1; i <= N; ++i) {
		cin >> tmp;
		jasik[tmp + 1].push_back(i);
	}

	cin >> k;

	check(k + 1);

	// 자식이 없고, 방문하지 않은 친구 체크
	int cnt = 0;
	for (int i = 1; i <= N; ++i) {
		if (jasik[i].size() == 0 && !visited[i]) {
			++cnt;
		}

		// 지워지는 친구만 자식으로 존재하는 경우 체크
		if (jasik[i].size() == 1 && jasik[i][0] == k + 1) {
			++cnt;
		}
	}

	cout << cnt << '\n';

	return 0;
}