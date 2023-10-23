#include<iostream>
#include<vector>
#include<queue>

using namespace std;

int dx[4] = {0,0,1,-1};
int dy[4] = {1,-1,0,0};

int map[12][6];

bool puyo() {
	bool visited[12][6] = { false, };

	// 임시 큐
	queue<pair<int, int>> q;
	
	// 터트릴 벡터
	vector<pair<int, int>> vv;

	// 터트리기
	for (int i = 0; i < 12; ++i) {
		for (int j = 0; j < 6; ++j) {
			if (map[i][j] == 0 || visited[i][j]) continue;

			// 연결된 개수
			int cnt = 1;
			q.push({ i, j });
			vv.push_back({ i, j });
			visited[i][j] = true;

			while (!q.empty()) {
				int x = q.front().first;
				int y = q.front().second;
				q.pop();

				for (int k = 0; k < 4; ++k) {
					int nx = x + dx[k];
					int ny = y + dy[k];

					if (nx < 0 || nx >= 12 || ny < 0 || ny >= 6) continue;
					if (visited[nx][ny] || map[nx][ny] != map[i][j]) continue;

					++cnt;
					visited[nx][ny] = true;
					q.push({ nx,ny });
					vv.push_back({ nx,ny });
				}
			}

			// 터트릴 수 없으면 넣은거 뺴주기
			if (cnt < 4) {
				for (int k = 0; k < cnt; ++k) vv.pop_back();
			}
		}
	}

	if (vv.size() == 0) return false;

	// 터트리기
	for (int i = 0; i < vv.size(); ++i) {
		map[vv[i].first][vv[i].second] = 0;
	}

	// 내려주기
	for (int i = 0; i < 6; ++i) {
		vector<int> v = {};
		for (int j = 11; j >= 0; --j) {
			if (map[j][i] != 0)v.push_back(map[j][i]);
		}

		for (int j = 0; j < v.size(); ++j) {
			map[11 - j][i] = v[j];
		}
		for (int j = v.size(); j < 12; ++j) {
			map[11 - j][i] = 0;
		}
	}

	return true;
}

int solve() {
	int cnt = 0;
	
	while (true) {

		// 터지지 않으면 종료
		if (!puyo())	break;

		++cnt;
	}

	return cnt;
}

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	//R, G, B, P, Y
	char tmp;
	for (int i = 0; i < 12; ++i) {
		for (int j = 0; j < 6; ++j) {
			cin >> tmp;
			if (tmp == '.') map[i][j] = 0;
			else if (tmp == 'R') map[i][j] = 1;
			else if(tmp == 'G') map[i][j] = 2;
			else if (tmp == 'B') map[i][j] = 3;
			else if (tmp == 'P') map[i][j] = 4;
			else if (tmp == 'Y') map[i][j] = 5;
		}
	}

	cout << solve() << '\n';

	return 0;
}