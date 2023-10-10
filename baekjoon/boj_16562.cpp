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
#define endl '\n'

int N, M, K;
vector<int> g[10001];
int arr[10001];
int parent[10001];
priority_queue<pii,vector<pii>,greater<>> q;

int find(int u) {
	if (parent[u] == u) return u;
	return parent[u] = find(parent[u]);
}

void merge(int u, int v) {
	u = find(u), v = find(v);
	if (u == v) return;

	parent[u] = v;
}

void init() {
	cin >> N >> M >> K;
	for (int i = 0; i <= N; i++) {
		parent[i] = i;
	}
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
		q.push({ arr[i],i });
	}
	for (int i = 0; i < M; i++) {
		int u, v; cin >> u >> v;
		if (u == v) continue;
		merge(u, v);
	}
}

int solve() {
	int sum = 0;
	while (!q.empty()) {
		int price = q.top().first;
		int v = q.top().second;
		q.pop();

		if (find(0) == find(v)) continue;
		if (K - price < 0) return -1;
		K -= price, sum += price;
		merge(0, v);
	}

	return sum;
}

int main() {
	freopen("sample_input.txt","r",stdin);

	init();
	int ans=solve();
	if (ans == -1) cout << "Oh no" << endl;
	else cout << ans << endl;
	
	fclose(stdin);
}