#include<iostream>
#include<queue>
using namespace std;
int N, Max=0;
int H[101][101];

bool inRange(int x, int y) {
	return x >= 0 && y >= 0 && x < N && y < N;
}

int find(int r) {
	int visited[101][101] = { {0,}, };
	int cnt = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (H[i][j] > r && !visited[i][j]) {
				queue<pair<int, int>> bfs;
				bfs.emplace(i, j);
				visited[i][j] = 1;
				cnt++;

				while (!bfs.empty()) {
					int x = bfs.front().first;
					int y = bfs.front().second;
					bfs.pop();

					int delta[4][2] = { {1, 0}, {0, 1},{-1, 0},{0, -1} };
					for (int k = 0; k < 4; k++) {
						int nx = x + delta[k][0];
						int ny = y + delta[k][1];
						if (inRange(nx, ny) && H[nx][ny] > r && !visited[nx][ny]) {
							visited[nx][ny] = 1;
							bfs.emplace(nx, ny);
						}
					}

				}
			}
		}
	}
	return cnt;
}


int main() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> H[i][j];
		}
	}

	for (int i = 0; i <= 100; i++) {
		Max = max(find(i), Max);
	}
	cout << Max;
}