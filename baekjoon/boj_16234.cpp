#include <iostream>
#include <algorithm>
#include <string.h>
#include <climits>
#include <cassert>
#include <cmath>
#include <queue>
#pragma warning(disable:4996)
using namespace std;
typedef pair<int, int> pii;


int N, L, R;
int arr[51][51];
int chk[51][51];//덩어리
int way[4][2] = { {-1,0},{0,1},{1,0},{0,-1} };

void init() {
	cin >> N >> L >> R;
	for (int i = 0; i < N; i++)for (int j = 0; j < N; j++) cin >> arr[i][j];

}

queue<pii> q;
vector<pii> v;
int chk2[51][51];//큐용 체크
int clusterId;

bool noMove;
void findCluster(int sy, int sx,int cid) {
	
	while (!q.empty()) q.pop();
	memset(chk2, 0, sizeof(chk2));
	assert(v.empty());

	q.push({ sy,sx });
	chk2[sy][sx] = 1;

	int cnt = 0, sum = 0;
	while (!q.empty()) {
		int y = q.front().first;
		int x = q.front().second;
		q.pop();

		chk[y][x] = cid;//덩어리 표시
		sum += arr[y][x]; cnt++;
		v.push_back({ y,x });
		
		for (int w = 0; w < 4; w++) {
			int ny = y + way[w][0];
			int nx = x + way[w][1];
			if (ny >= 0 && nx >= 0 && ny < N&&nx < N&&chk2[ny][nx]==0&&chk[ny][nx]==0) {
				
				int gap = abs(arr[y][x] - arr[ny][nx]);
				if (L <= gap && gap <= R) {
					noMove = false;
					chk2[ny][nx] = 1;
					q.push({ ny,nx });
				}
			}
		}
	}

	//이동하기
	int k = floor(sum / cnt);
	while (!v.empty()) {
		int y = v.back().first;
		int x = v.back().second;
		v.pop_back();

		arr[y][x] = k;
	}

}

bool solve() {
	memset(chk, 0, sizeof(chk));
	clusterId = 1;

	noMove = true;
	for (int i = 0; i < N; i++)for (int j = 0; j < N; j++) {
		if (chk[i][j] != 0) continue;
		findCluster(i, j, clusterId++);
	}

	return noMove;
}


int main() {
	freopen("sample_input.txt", "r", stdin);
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	init();
	int i = 0;
	for (;; i++) {
		if (solve()) break;
	}
	cout << i << endl;

	fclose(stdin);
}