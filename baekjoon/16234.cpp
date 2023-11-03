#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>
using namespace std;
int N, L, R;
int Pop[51][51];
int Deltas[4][2] = { {1,0}, {0,1}, {-1,0}, {0,-1} };

bool inRange(int x, int y) {
	return x >= 0 && y >= 0 && x < N && y < N;
}

int movePop() {
	int move = 0;
	int visited[51][51] = { {0,}, };
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (!visited[i][j]) {
				queue<pair<int, int>> bfs;
				vector<pair<int, int>> united;
				bfs.emplace(i, j);
				united.emplace_back(i, j);
				visited[i][j] = 1;

				int cnt = 1, unitedPop = Pop[i][j];
				while (!bfs.empty()) {
					int x = bfs.front().first;
					int y = bfs.front().second;
					bfs.pop();

					for (int i = 0; i < 4; i++) {
						int nx = x + Deltas[i][0];
						int ny = y + Deltas[i][1];
						int diff = abs(Pop[x][y] - Pop[nx][ny]);
						if (inRange(nx, ny) && !visited[nx][ny] && diff >= L && diff <= R) {
							visited[nx][ny] = 1;
							united.emplace_back(nx, ny);
							bfs.emplace(nx, ny);
							cnt++;
							unitedPop += Pop[nx][ny];
						}
					}
				}
				int newPop = unitedPop / cnt;
				for (pair<int, int>& p : united) {
					Pop[p.first][p.second] = newPop;
				}
				if (cnt > 1) {
					move++;
				}
			}
		}
	}
	return move;
}

int main() {
    ios::sync_with_stdio(0), cin.tie(0), cout.tie(0);
	cin >> N >> L >> R;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> Pop[i][j];
		}
	}

	int day = 0;
	while (movePop() != 0) {
		day++;
	}
	
	cout << day;
}