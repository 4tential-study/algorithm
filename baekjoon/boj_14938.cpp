#include <iostream>
#include <string.h>
#include <algorithm>
#include <climits>
#include <cmath>
#include <cassert>
#include <vector>
#define MAX 987654321
#pragma warning(disable:4996)
using namespace std;
typedef long long int ll;
typedef pair<int, int> pii;
#define endl '\n'


int N, M, R;
int arr[101];
int dist[101][101];

void init() {
	cin >> N >> M >> R;
	for (int i = 1; i <= N; i++) cin >> arr[i];

	for (int i = 1; i <= N; i++)for (int j = 1; j <= N; j++) dist[i][j] = (i==j)? 0:INT_MAX;
	for (int i = 0; i < R; i++) {
		int u, v, w; cin >> u >> v >> w;
		dist[u][v] = w;
		dist[v][u] = w;
	}
}

void solve() {
	//1. �÷��̵� ���ȷ� ��� u-v�� �Ÿ��� ����
	for (int k = 1; k <= N; k++)for (int i = 1; i <= N; i++)for (int j = 1; j <= N; j++) {
		if (dist[i][k] == INT_MAX || dist[k][j] == INT_MAX) continue;
		if (dist[i][k] + dist[k][j] < dist[i][j]) dist[i][j] = dist[i][k] + dist[k][j];
	}

	//2. ���� �� ����(u)�� ���ؼ� ���� �� �ִ� ������ ���ϱ�
	//�Ÿ��� M�� ������ �����۸� cnt�� ����
	//i�ڽŵ� �Ÿ��� 0�̴ϱ� �ڵ����� ������
	int ans = 0;
	for (int i = 1; i <= N; i++) {
		int cnt = 0;
		for (int j = 1; j <= N; j++) if (dist[i][j] <= M) cnt += arr[j];
		
		ans = max(ans, cnt);
	}

	cout << ans << endl;
}

int main() {
	freopen("sample_input.txt", "r", stdin);
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	init();
	solve();


	fclose(stdin);
}
