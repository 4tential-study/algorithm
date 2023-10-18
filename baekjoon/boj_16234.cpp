#include <iostream>
#include <queue>

using namespace std;

// 4방향 탐색
const int dx[4] = {0,0,1,-1};
const int dy[4] = {1,-1,0,0};

// N : 인구수, L ~ R명 사이
int N, L, R;

// map
int arr[51][51];

// 방문배열
bool visited[51][51];

void solve() {
	// 이동이 발생하였는지 체크하는 변수
	bool isMoved = true;

	// bfs 탐색 큐
	queue<pair<int, int>> q;

	// 국경연결 후 인구분배를 위한 큐
	queue<pair<int, int>> qq;

	int time = -1;
	// 시간 초마다 반복
	while(isMoved) {
		// 시간초마다 초기화
		++time; isMoved = false;

		for (int i = 0; i < N; ++i)
			for (int j = 0; j < N; ++j)
				visited[i][j] = false;

		// 탐색 -> 모두 위치를 방문한 경우 끝나게 된다.
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				// 이미 체크한 곳이면 패스
				if (visited[i][j]) continue;

				// 인구의 총합
				int sum = 0;

				// bfs 탐색
				q.push({ i, j });
				qq.push({ i, j });
				visited[i][j] = true;
				sum += arr[i][j];

				while (!q.empty()) {
					int x = q.front().first;
					int y = q.front().second;
					q.pop();

					for (int k = 0; k < 4; ++k) {
						int nx = x + dx[k];
						int ny = y + dy[k];

						// 경계, 방문
						if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;

						int tmp = abs(arr[x][y] - arr[nx][ny]);
						if (tmp >= L && tmp <= R) {
							visited[nx][ny] = true;
							sum += arr[nx][ny];
							q.push({ nx,ny });
							qq.push({ nx, ny });
						}
					}
				}

				// 국경이 열린 경우
				if (qq.size() != 1) {
					// 이번 턴에 끝나면 안돼용
					isMoved = true;
				}

				// 분배하기
				int cur = sum / qq.size();
				while (!qq.empty()) {
					int x = qq.front().first;
					int y = qq.front().second;
					qq.pop();

					arr[x][y] = cur;
				}
			}
		}
	}

	// 출력
	cout << time << '\n';
}


int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	// input
	cin >> N >> L >> R;
	for (int i = 0; i < N; ++i) {
		for (int j = 0; j < N; ++j) {
			cin >> arr[i][j];
		}
	}

	// solve
	solve();

	return 0;
}