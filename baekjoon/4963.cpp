#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>
using namespace std;
int W, H;
int Map[51][51];
vector<pair<int, int>> Land;
int Visited[51][51] = { {0,}, };

bool isLand(int x, int y) {
	return x >= 0 && y >= 0 && x < H && y < W && Map[x][y] && !Visited[x][y];
}

int main() {
	ios::sync_with_stdio(0), cin.tie(0), cout.tie(0);
	cin >> W >> H;
	while(W != 0 && H != 0){	
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				cin >> Map[i][j];
				if(Map[i][j] == 1)
					Land.emplace_back(i, j);
			}
		}

		int deltas[8][2] = { {-1, -1}, {-1, 0}, {0, -1}, {1, 1}, {1, 0}, {0, 1}, {1, -1}, {-1, 1} };
		int cnt = 0;
		
		for (pair<int, int> p : Land) {
			queue<pair<int, int>> bfs;
			if (!Visited[p.first][p.second]) {
				bfs.push(p);
				cnt++;
			}
			while (!bfs.empty()) {
				int x = bfs.front().first;
				int y = bfs.front().second;
				bfs.pop();
				
				for (int i = 0; i < 8; i++) {
					int nx = x + deltas[i][0];
					int ny = y + deltas[i][1];
					if (isLand(nx, ny)) {
						bfs.emplace(nx, ny);
						Visited[nx][ny] = 1;
					}
				}
			}
		}
	
		cout << cnt << "\n";
		Land.clear();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				Visited[i][j] = 0;
			}
		}

		cin >> W >> H;
	}
}