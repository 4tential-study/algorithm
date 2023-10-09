#include <iostream>
#include <vector>
#include <queue>
#include <string.h>
using namespace std;

// 상, 좌, 우, 하 순서로
constexpr int dx[4] = { -1, 0, 0, 1 };
constexpr int dy[4] = { 0, -1, 1, 0 };

// 격자의 크기, 사람의 수
int N, M;

// 맵
int map[17][17];

// 방문체크 배열
bool visited[17][17];

// 편의점 찾아가는 사람들
struct Person {
	// 현재 위치
	int x, y;

	// 목표 위치
	int ex, ey;
};

int pCnt;
Person NODE[200];
vector<Person*> person;

// 
bool move() {
	// 도착한 사람의 수
	int cnt = 0;

	for (int i = 0; i < person.size(); i++) {
		// 도착 지점이 -1인 경우 => 도착한 경우
		if (map[person[i]->ex][person[i]->ey] == -1) { 
			cnt++;  
			continue; 
		}

		// 최단 거리, 그때의 방향
		int minDist = 999999, dir = -1;

		// bfs 탐색을 4번 할까?
		// 상, 좌, 우, 하로 탐색하기 때문에 같은 거리일 경우 우선순위가 지켜짐.
		for (int k = 0; k < 4; k++) {
			memset(visited, false, sizeof(visited));

			int x = person[i]->x + dx[k];
			int y = person[i]->y + dy[k];

			// 경계, 지나가지 못하는 곳 체크
			if (x <= 0 || x > N || y <= 0 || y > N || map[x][y] == -1) continue;

			// bfs 탐색해서 거리 구하기
			// x, y, dist
			queue<pair<pair<int, int>, int>> q;
			q.push({ { x, y } , 1 });
			visited[x][y] = true;

			while (!q.empty()) {
				int cx = q.front().first.first;
				int cy = q.front().first.second;
				int d = q.front().second;
				q.pop();

				// 도착한 경우
				if (cx == person[i]->ex && cy == person[i]->ey) {
					if (d < minDist) {
						minDist = d;
						dir = k;
					}
					break;
				}

				for (int j = 0; j < 4; j++) {
					int nx = cx + dx[j];
					int ny = cy + dy[j];

					if (nx <= 0 || nx > N || ny <= 0 || ny > N || map[nx][ny] == -1 || visited[nx][ny]) continue;

					visited[nx][ny] = true;
					q.push({ { nx,ny } , d + 1 });
				}
			}
		}

		// 바로 도착하는 경우
		if (minDist == 1) {
			map[person[i]->ex][person[i]->ey] = -1;
			cnt++;
		}
		else {
			// 이동하기
			person[i]->x += dx[dir];
			person[i]->y += dy[dir];
		}
	}

	// M명이 도착한 경우 true
	if (cnt == M) return true;
	else return false;
}

// a, b에서 가장 가까운 베이스 캠프 찾기
void findBaseCamp(int a, int b) {
	// 상, 좌, 우, 하로 탐색할 경우 가장 먼저 만나는 캠프가 우선순위가 제일 높음.

	// 방문 배열 초기화
	memset(visited, false, sizeof(visited));

	queue<pair<int, int>> q;
	q.push({ a, b });
	visited[a][b] = true;

	int bx, by;
	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		// 베이스 캠프를 찾으면
		if (map[x][y] == 1) {
			bx = x;
			by = y;
			break;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			// 경계, 방문, 지나가지 못하는 곳 체크
			if (nx <= 0 || nx > N || ny <= 0 || ny > N) continue;
			if (map[nx][ny] == -1 || visited[nx][ny]) continue;

			visited[nx][ny] = true;
			q.push({ nx , ny });
		}
	}

	// 해당 베이스 캠프에 배정하기.
	map[bx][by] = -1;

	// x, y 위치에서 a, b 위치로 가고자 한다.
	NODE[pCnt] = { bx, by, a, b };
	person.push_back(&NODE[pCnt++]);
}

void solve() {

	int t = 0;
	int a, b;
	while (++t) {
		// 이동하기.
		if (move()) {
			break;
		}

		// 베이스 캠프 배정하기
		if (t <= M) {
			cin >> a >> b;

			findBaseCamp(a, b);
		}
	}

	cout << t << '\n';
}

void input() {
	cin >> N >> M;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> map[i][j];
		}
	}
}

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	input();
	solve();

	return 0;
}