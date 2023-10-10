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
pii dst[32];//[i]����� ������ <- y,x
pii arr[17][17]; //{1,} ������, {-1, } ���̽�ķ��, second�� ����� ���� ���� �Ұ�
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

//ey,ex�� ���������� ���� bfs �ִܰŸ�
//�켱������ k, y, x ���̶� 
//ey,ex�� �����ص� �ٷ� break XX
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
	pii nextyx; //y,x���� 1ĭ �̵��� ���� ny,nx
	int k; //�̵� �Ÿ�
}Node;
queue<Node> q2;
//ey,ex�� �������ִ� bfs �ִܰŸ�
//�켱������ ���� ������
//ey,ex�� �����ϸ� �ٷ� break
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

	//1. ���ڿ� �ִ� ����� �ִܰŸ��� 1ĭ ������
	for (int i = 1; i <= min(time-1, M); i++) {
		int y = people[i].first, x = people[i].second;
		if (y == dst[i].first && x == dst[i].second) continue;

		pii yx = findNextYX(i, y, x);
		//���� ��ġ�� �ٷ� ���� �ʰ�, v�� ����(���� ó�� ���ؼ�)
		v.push_back({ i,yx });
	}

	//2. ������ �����ϸ� ���Ա��� ��Ű��(���ÿ�)
	for (int i = 0; i < v.size(); i++) {//num,{y,x}
		int num = v[i].first;
		int y = v[i].second.first, x = v[i].second.second;
		people[num] = { y,x };
		//��ǥ�� �ϴ� �������� �����ϸ�
		if (y == dst[num].first && x == dst[num].second) {
			dstCnt++;
			//second�� num(���)�� �־ ���Ա��� ��Ű��
			arr[y][x].second = num;
		}
	}
	
	//3. i��° ��� ���̽�ķ���� �̵�
	if (time <= M) {
		ipi ret = findBaseCamp(time);
		int y = ret.second.first, x = ret.second.second;
		assert(arr[y][x].first == -1);
		people[time] = { y,x };
		//i��° ��� 1�� ���������� �̵��ϴϱ� ���ù���X
		//�ٷ� second�� ����� ���� ���Ա��� ��Ű��
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