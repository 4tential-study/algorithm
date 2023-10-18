#include<iostream>
#include<queue>

using namespace std;

// 감옥 정보
struct CUBE {
	int x, y;
	int view;

	CUBE(int x, int y, int view) : x(x), y(y), view(view) {}
}; 

// 상, 하, 좌, 우
constexpr int dx[4] = {-1,1,0,0};
constexpr int dy[4] = {0,0,-1,1};

// 면,, 0 : 밑, 1 : 위, 2 : 좌, 3 : 우, 4 : 앞, 5 : 뒤
constexpr int DOWN = 0, UP = 1, LEFT = 2, RIGHT = 3, FRONT = 4, BACK = 5;

/* 펼쳤다고 생각한다면
   뒤
좌 밑 우 위
   앞
*/

// 세로, 가로
int N, M;

// 출발점, 도착점
int sx, sy, ex, ey;

// 맵
int arr[501][501];

// 거리(방문) : 각 위치, 각 면에 대해서
int dist[501][501][6];

// k면을, dir 방향으로 굴렸을 때
int rotate(int k, int dir) {
	// dir 순서 : 상, 하, 좌, 우

	// 밑을 보고 있을 때
	if (k == DOWN) {
		if (dir == 0) return FRONT;
		else if (dir == 1) return BACK;
		else if (dir == 2) return RIGHT;
		else return LEFT;
	}
	// 위를 보고 있을 때
	else if (k == UP) {
		if (dir == 0) return BACK;
		else if (dir == 1) return FRONT;
		else if (dir == 2) return LEFT;
		else return RIGHT;
	}
	// 왼쪽을 보고 있을 때
	else if (k == LEFT) {
		if (dir == 0) return LEFT;
		else if (dir == 1) return LEFT;
		else if (dir == 2) return DOWN;
		else return UP;
	}
	// 오른쪽을 보고 있을 때
	else if (k == RIGHT) {
		if (dir == 0) return RIGHT;
		else if (dir == 1) return RIGHT;
		else if (dir == 2) return UP;
		else return DOWN;
	}
	// 앞을 보고 있을 때
	else if (k == FRONT) {
		if (dir == 0) return UP;
		else if (dir == 1) return DOWN;
		else if (dir == 2) return FRONT;
		else return FRONT;
	}
	// 뒤를 보고 있을 때
	else if (k == BACK) {
		if (dir == 0) return DOWN;
		else if (dir == 1) return UP;
		else if (dir == 2) return BACK;
		else return BACK;
	}
}

int solve() {

	// CUBE : x, y위치, 현재 면 k
	queue<CUBE> q;
	q.push({sx, sy, 0});
	dist[sx][sy][0] = 1;

	while (!q.empty()) {
		CUBE cur = q.front();
		q.pop();

		// 도둑을 잡으면
		if (cur.x == ex && cur.y == ey && cur.view == 0) {
			break;
		}

		for (int dir = 0; dir < 4; ++dir) {
			int nx = cur.x + dx[dir];
			int ny = cur.y + dy[dir];
			int nView = rotate(cur.view, dir);
			
			// 경계 체크
			if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

			// 벽, 방문체크
			if (arr[nx][ny] == -1 || dist[nx][ny][nView] != 0) continue;

			// 막힌 면이 바닥을 향할 때 그 위치에 도둑이 있다면 패배함 -> 이동 불가
			if (nView != 0 && arr[nx][ny] == 2) continue;
			
			dist[nx][ny][nView] = dist[cur.x][cur.y][cur.view] + 1;
			q.push({nx, ny, nView});
		}
	}

	// 초기값 1이기 때문에 dist - 1
	// 방문하지 않은 경우 dist = 0이기 때문에 return -1;
	return dist[ex][ey][0] - 1;
}

int main() {
	cin >> N >> M;
	char tmp;
	for (int i = 0; i < N; ++i) {
		for (int j = 0; j < M; ++j) {
			cin >> tmp;
			if (tmp == '#') arr[i][j] = -1;
			else if (tmp == 'D') { arr[i][j] = 1; sx = i; sy = j; }
			else if (tmp == 'R') { arr[i][j] = 2; ex = i; ey = j; }
		}
	}

	cout << solve() << '\n';

	return 0;
}