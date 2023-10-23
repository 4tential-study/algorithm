#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>
using namespace std;
char Puyo[13][7];
int Deltas[4][2] = { {1,0}, {0,1}, {-1,0}, {0,-1} };

bool inRange(int x, int y) {
	return x >= 0 && y >= 0 && x < 12 && y < 6;
}
bool bomb() {
	bool bomb = false;
	int visited[13][7] = { {0, }, };
	for (int i = 11; i >= 0; i--) {
		for (int j = 0; j < 6; j++) {
			if (Puyo[i][j] != '.' && !visited[i][j]) {
				int cnt = 1;
				visited[i][j] = 1;
				queue<pair<int, int>> bfs;
				vector<pair<int, int>> temp{ {i, j} };
				bfs.emplace(i, j);

				while (!bfs.empty()) {
					int x = bfs.front().first;
					int y = bfs.front().second;
					bfs.pop();

					for (int k = 0; k < 4; k++) {
						int nx = x + Deltas[k][0];
						int ny = y + Deltas[k][1];

						if (inRange(nx, ny) && !visited[nx][ny] && Puyo[nx][ny] == Puyo[i][j]) {
							bfs.emplace(nx, ny);
							temp.emplace_back(nx, ny);
							visited[nx][ny] = 1;
							cnt++;
						}
					}
				}
				if (cnt >= 4) {
					bomb = true;
					for (pair<int, int>& p : temp) {
						Puyo[p.first][p.second] = '.';
					}
				}

			}
		}
	}


	return bomb;
}

void drop() {
	for (int j = 0; j < 6; j++) {
		for (int i = 11; i >= 0; i--) {
			if (Puyo[i][j] == '.') {
				int temp = i;
				while (temp >= 0 && Puyo[temp][j] == '.') {
					temp--;
				}
				if (temp < 0) {
					break;
				}
				Puyo[i][j] = Puyo[temp][j];
				Puyo[temp][j] = '.';
			}
		}
	}
}

int main() {
	for (int i = 0; i < 12; i++) {
		for (int j = 0; j < 6; j++) {
			cin >> Puyo[i][j];
		}
	}
	int cons = 0;
	while (bomb()) {
		cons++;
		drop();
	}
	cout << cons;
}