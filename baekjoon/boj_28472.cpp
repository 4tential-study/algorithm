#include <string>
#include <vector>
#include <string.h>
#include <iostream>
#include <algorithm>
#include <climits>
#include <cassert>
#include <cmath>
using namespace std;
#pragma warning(disable:4996)
#define endl '\n'
typedef pair<int, int> pii;
typedef pair<char, int> pci;

int N, R, L, Q;
vector<int> g[100001];
int arr[100001];

void init() {
	cin >> N >> R;
	for (int i = 0; i < N - 1; i++) {
		int u, v; cin >> u >> v;
		//양방향..!
		g[u].push_back(v);
		g[v].push_back(u);
	}
	memset(arr, -1, sizeof(arr));
	cin >> L;
	for (int i = 0; i < L; i++) {
		int k; cin >> k;
		cin >> arr[k];
	}
}

int solve(int cur, int depth,int parent) {
	//g[cur].size()==1 하면 root자식이 1개일 때 걸림
	//할거였으면 들어오는 간선 in[]==1 로 거르던가,
	//아니면 반환할때 초기값 그대로면 arr[cur]로 보내버리던가 했어야했다ㅠ
	if (arr[cur] != -1) return arr[cur];

	int minv = INT_MAX, maxv = INT_MIN;
	for (int i = 0; i < g[cur].size(); i++) {
		int next = g[cur][i];
		if (next != parent) {
			int ret = solve(next, depth + 1,cur);
			minv = min(minv, ret);
			maxv = max(maxv, ret);
		}
	}
	//홀수면 최소값
	//짝수면 최대값
	if (depth % 2 == 1) return arr[cur] = minv;
	return arr[cur] = maxv;
}

int main() {
	freopen("sample_input.txt", "r", stdin);
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	init();
	solve(R, 0,-1);
	cin >> Q;
	for (int i = 0; i < Q; i++) {
		int x; cin >> x;
		assert(arr[x] != INT_MAX);
		assert(arr[x] != INT_MIN);
		cout << arr[x] << endl;
	}

	fclose(stdin);
}