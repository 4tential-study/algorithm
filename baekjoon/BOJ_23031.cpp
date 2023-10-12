#include <iostream>
#include <string>
#include <vector>
using namespace std;

// 좌측으로 회전 -> +1 : 상 좌 하 우
// 우측으로 회전 -> +3
constexpr int dx[4] = {-1,0,1,0};
constexpr int dy[4] = {0,-1,0,1};

// 8방향 탐색
constexpr int ddx[8] = { -1,0,1,0,1,1,-1,-1, };
constexpr int ddy[8] = { 0,-1,0,1,1,-1,1,-1, };

// 좌표, 방향
struct POS {
	int x, y;
	int dir;
};

// 크기
int N;

// 이동하는 문자열
string str;

// 맵 info
// 0 : 빈공간
// -1 : 좀비
int map[15][15];

// 1 : 스위치가 있는 칸
// 2 : 스위치가 켜진 칸
int light[15][15];

// 아리의 위치, 방향
POS Ari;

// 좀비들의 위치, 방향
vector<POS> zombi;

// 아리 이동
bool moveAri(char ch) {
	if (ch == 'F') {
		int nx = Ari.x + dx[Ari.dir];
		int ny = Ari.y + dy[Ari.dir];
		
		// 이동이 불가능하다면 종료
		if (nx < 0 || nx >= N || ny < 0 || ny >= N) return false;

		Ari.x = nx;
		Ari.y = ny;

		// 스위치를 만난 경우
		if (light[Ari.x][Ari.y] == 1) {
			// 내 위치 2
			light[Ari.x][Ari.y] = 2;

			// 8방향 2
			for (int i = 0; i < 8; i++) {
				int nnx = Ari.x + ddx[i];
				int nny = Ari.y + ddy[i];

				// 경계 체크
				if (nnx < 0 || nnx >= N || nny < 0 || nny >= N) continue;

				// 만약 스위치인 경우 그 스위치에 도착했을 때
				// 그 주변을 탐색해야하니까 패스 / 여기서 3Fail..
				if (light[nnx][nny] == 1) continue;

				light[nnx][nny] = 2;
			}
		}

		// 이동했다가 zombi를 만났고, 그 위치의 스위치는 off면
		if (map[Ari.x][Ari.y] == -1 && !light[Ari.x][Ari.y]) return true;
	}
	else if (ch == 'L') {
		Ari.dir = (Ari.dir + 1) % 4;
	}
	else { // 'R'
		Ari.dir = (Ari.dir + 3) % 4;
	}

	return false;
}

// 좀비 이동
bool moveZombi() {
	for (int i = 0; i < zombi.size(); i++) {
		int nx = zombi[i].x + dx[zombi[i].dir];
		int ny = zombi[i].y + dy[zombi[i].dir];

		// 좀비 지우기
		map[zombi[i].x][zombi[i].y] = 0;

		// 경계를 벗어난 경우 0 -> 2, 2 -> 0 방향만 바꿔주기
		if (nx < 0 || nx >= N) {
			zombi[i].dir = zombi[i].dir ? 0 : 2;
		}
		else {
			// 아니면 이동
			zombi[i].x = nx;
			zombi[i].y = ny;
		}
	}

	// 좀비가 곂치는 경우에 대한 조건이 없어서
	// 혹시나 하는 마음에 따로 처리
	for (int i = 0; i < zombi.size(); i++) {
		map[zombi[i].x][zombi[i].y] = -1;

		// 아리와 만나는 경우도 여기서 처리
		if (zombi[i].x == Ari.x && zombi[i].y == Ari.y
			&& !light[zombi[i].x][zombi[i].y]) return true;
	}

	return false;
}

void solve() {

	bool passOut = false;
	for (int i = 0; i < str.length(); i++) {
		// 아리 이동
		passOut = moveAri(str[i]);
		if (passOut) break;

		// 고양이 이동
		passOut = moveZombi();
		if (passOut) break;
	}

	if (passOut) cout << "Aaaaaah!" << '\n';
	else cout << "Phew..." << '\n';
}

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	cin >> N;
	cin >> str;

	char tmp[15];
	for (int i = 0; i < N; i++) {
		cin >> tmp;
		for (int j = 0; j < N; j++) {
			// 스위치
			if (tmp[j] == 'S') {
				light[i][j] = 1;
			}
			// 좀비
			else if (tmp[j] == 'Z') {
				map[i][j] = -1;
				zombi.push_back({i, j, 2});
			}
		}
	}

	// 아리 위치, 방향
	Ari = {0, 0, 2};

	solve();

	return 0;
}
