#include<string>
#include<iostream>
#include<vector>
using namespace std;
string Route;
int N;
int Light[16][16];
int Zombie[16][16];
int Deltas[8][2] = { {0, -1}, {1, 0}, {0, 1}, {-1, 0}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1} };

bool inRange(int x, int y) {
	return x >= 0 && y >= 0 && x < N && y < N;
}

int main() {
	cin >> N >> Route;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			char c;
			cin >> c;
			if (c == 'S') {
				Light[i][j] = 2;
			}
			else if (c == 'Z') {
				Zombie[i][j] = 1;
			}
		}
	}
	string ans = "Phew...";
	int d = 1;
	int x = 0, y = 0;

	for (int i = 0; i < Route.size(); i++) {
		switch (Route[i]) {
		case 'F': {
			int nx = x + Deltas[d][0];
			int ny = y + Deltas[d][1];
			if (inRange(nx, ny)) {
				x = nx;
				y = ny;
			}
			break;
		}
		case 'L':
			d = (d + 1) % 4;
			break;
		case 'R':
			d = (d - 1 + 4) % 4;
			break;
		}

		if (Zombie[x][y] > 0 && Light[x][y] == 0) {
			ans = "Aaaaaah!";
			break;
		}

		if (Light[x][y] == 2) {
			for (int j = 0; j < 8; j++) {
				int a = x + Deltas[j][0];
				int b = y + Deltas[j][1];
				if (inRange(a, b) && Light[a][b] == 0) {
					Light[a][b] = 1;
				}
			}
		}
		int temp[16][16] = { 0, };

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (Zombie[r][c] > 0) {
					int nr = r + Deltas[Zombie[r][c]][0];
					int nc = c + Deltas[Zombie[r][c]][1];

					if (inRange(nr, nc)) {
						temp[nr][nc] = Zombie[r][c];
						Zombie[r][c] = 0;
					}
					else {
						temp[r][c] = (Zombie[r][c] + 2) % 4;
					}
				}
				
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (temp[r][c] > 0) {
					Zombie[r][c] = temp[r][c];

					if (r == x && y == c && Light[r][c] == 0) {
						ans = "Aaaaaah!";
						break;
					}
				}
			}
		}
	}

	cout << ans;
}