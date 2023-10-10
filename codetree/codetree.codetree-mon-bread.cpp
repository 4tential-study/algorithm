#include <iostream>
#include <string.h>
#include <algorithm>
#include <climits>
#include <cmath>
#include <cassert>
#include <queue>
#define MAX 987654321
#pragma warning(disable:4996)
using namespace std;
typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<pii, int> pip;
typedef pair<int, pii> ipi;
#define endl '\n'

int way[4][2] = { {-1,0},{0,-1},{0,1},{1,0} };

int N, M;
pii dst[32];//[i]사람의 목적지 <- y,x
pii arr[17][17]; //{1,} 편의점, {-1, } 베이스캠프, second가 양수면 이제 접근 불가
pii people[32];
queue<pip> q;
int dstCnt;
int chk[16][16];

void init() {
	memset(dst, 0, sizeof(dst));
	memset(arr, 0, sizeof(arr));
	memset(people, 0, sizeof(people));

	dstCnt = 0;
	cin >> N >> M;
	for (int i = 0; i < N; i++)for (int j = 0; j < N; j++) {
		cin >> arr[i][j].first;
		if (arr[i][j].first == 1) arr[i][j].first = -1;
	}
	for (int i = 1; i <= M; i++) {
		int y, x; cin >> y >> x; --y, --x;
		dst[i] = { y,x };
		arr[y][x].first = 1;
	}
}

//ey,ex가 정해져있지 않은 bfs 최단거리
//우선순위가 k, y, x 순이라서 
//ey,ex에 도착해도 바로 break XX
ipi findBaseCamp(int i) {
	while (!q.empty()) q.pop();
	memset(chk, 0, sizeof(chk));

	int sy = dst[i].first, sx = dst[i].second;
	ipi ret = { INT_MAX,{INT_MAX,INT_MAX} };//k,{y,x}
	q.push({ {sy,sx},0 });
	chk[sy][sx] = 1;
	while (!q.empty()) {
		int y = q.front().first.first;
		int x = q.front().first.second;
		int k = q.front().second;
		q.pop();
		
		if (k > ret.first) break;
		if (arr[y][x].first == -1) {
			assert(arr[y][x].second == 0);
			if (ret.first > k) {
				ret = { k,{y,x} };
			}
			else if (ret.first == k) {
				if (ret.second.first > y) {
					ret = { k,{y,x} };
				}
				else if (ret.second.first == y) {
					if (ret.second.second > x) {
						ret = { k,{y,x} };
					}
				}
			}
		}
		if (k == ret.first) continue;

		for (int w = 0; w < 4; w++) {
			int ny = y + way[w][0];
			int nx = x + way[w][1];
			int nk = k + 1;
			if (ny >= 0 && nx >= 0 && ny < N && nx < N && arr[ny][nx].second == 0&& chk[ny][nx]==0) {
				q.push({ {ny,nx},nk });
				chk[ny][nx] = 1;
			}
		}
	}
	assert(ret.first != INT_MAX);
	return ret;
}


typedef struct Node {
	int y, x;
	pii nextyx; //y,x에서 1칸 이동한 다음 ny,nx
	int k; //이동 거리
}Node;
queue<Node> q2;
//ey,ex가 정해져있는 bfs 최단거리
//우선순위가 방향 순서라서
//ey,ex에 도착하면 바로 break
pii findNextYX(int num,int sy,int sx) {
	memset(chk, 0, sizeof(chk));
	while (!q2.empty()) q2.pop();//pip

	q2.push({ sy,sx,{0,0},0 });
	chk[sy][sx] = 1;
	while (!q2.empty()) {
		int y = q2.front().y;
		int x = q2.front().x;
		pii nextyx = q2.front().nextyx;
		int k = q2.front().k;
		q2.pop();

		if (y == dst[num].first && x == dst[num].second) {
			return nextyx;
		}

		for (int w = 0; w < 4; w++) {
			int ny = y + way[w][0];
			int nx = x + way[w][1];
			if (ny >= 0 && nx >= 0 && ny < N && nx < N && arr[ny][nx].second == 0&& chk[ny][nx]==0) {
				chk[ny][nx] = 1;
				if (k == 0) q2.push({ ny,nx,{ny,nx},k + 1 });
				else q2.push({ ny,nx,nextyx,k + 1 });
			}
		}
	}
	assert(0);
}

vector<ipi> v;

bool solve(int time) {

	v.clear();

	//1. 격자에 있는 사람들 최단거리로 1칸 움직임
	for (int i = 1; i <= min(time-1, M); i++) {
		int y = people[i].first, x = people[i].second;
		if (y == dst[i].first && x == dst[i].second) continue;

		pii yx = findNextYX(i, y, x);
		//다음 위치에 바로 가지 않고, v에 담음(동시 처리 위해서)
		v.push_back({ i,yx });
	}

	//2. 편의점 도착하면 출입금지 시키기(동시에)
	for (int i = 0; i < v.size(); i++) {//num,{y,x}
		int num = v[i].first;
		int y = v[i].second.first, x = v[i].second.second;
		people[num] = { y,x };
		//목표로 하는 편의점에 도착하면
		if (y == dst[num].first && x == dst[num].second) {
			dstCnt++;
			//second에 num(양수)를 넣어서 출입금지 시키기
			arr[y][x].second = num;
		}
	}
	
	//3. i번째 사람 베이스캠프로 이동
	if (time <= M) {
		ipi ret = findBaseCamp(time);
		int y = ret.second.first, x = ret.second.second;
		assert(arr[y][x].first == -1);
		people[time] = { y,x };
		//i번째 사람 1명만 마지막으로 이동하니까 동시문제X
		//바로 second를 양수로 만들어서 출입금지 시키기
		arr[y][x].second = time;
	}

	if (dstCnt >= M) return true;
	return false;
}

int main() {
	freopen("sample_input.txt","r",stdin);
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	init();
	int t;
	for (t = 1;; t++) {
		if (solve(t)) break;
	}
	cout << t << endl;
	
	fclose(stdin);
}