#include <iostream>
#include <vector>
#include <queue>

using namespace std;

// 백신 수, 선행관계 수
int N, M;

// 진입차수
int inDegree[10001];

// 완료 시간
int vaccineTime[10001];

// 관계 v[s] = e, w / s접종 후 w일 후 e 접종
vector<pair<int, int>> v[10001];

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	cin >> N >> M;

	int s, e, w;
	for (int i = 0; i < M; ++i) {
		// S 접종 후, W 일 후 E 접종
		cin >> s >> e >> w;

		v[s].push_back({ e,w });
		++inDegree[e];
	}

	queue<int> q;
	for (int i = 1; i <= N; ++i) {
		if (inDegree[i] == 0) {
			q.push(i);
			vaccineTime[i] = 1;
		}
	}

	// 작업 시작
	while (!q.empty()) {
		int cur = q.front();
		q.pop();

		for (int i = 0; i < v[cur].size(); ++i) {
			int nx = v[cur][i].first;
			int nc = vaccineTime[cur] + v[cur][i].second;

			if (v[cur][i].second >= 7) ++nc;

			// 다 처리한 백신이면
			if (inDegree[nx] == 0) continue;

			// 가장 마지막에 끝나는 백신의 시간
			if (vaccineTime[nx] < nc) {
				vaccineTime[nx] = nc;
			}

			// 백신 처리
			if (--inDegree[nx] == 0) {
				q.push(nx);
			}
		}
	}

	int res = 0;
	for (int i = 1; i <= N; ++i) {
		if (res < vaccineTime[i]) res = vaccineTime[i];
	}

	cout << res << '\n';

	return 0;
}