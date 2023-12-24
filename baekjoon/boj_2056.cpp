#include <iostream>
#include <string.h>
#include <algorithm>
#include <climits>
#include <cmath>
#include <cassert>
#include <vector>
#include <stack>
#define MAX 987654321
#pragma warning(disable:4996)
using namespace std;
typedef long long int ll;
typedef pair<int, int> pii;
#define endl '\n'

int N;
ll st[10001]; //작업 시작시간= 선행작업의 끝나는시간들의 max값
ll et[10001]; //작업 끝나는 시간=st[]+arr[]
ll arr[10001]; //작업 걸리는 시간 <-주어짐
int out[10001]; //선행 작업 개수
vector<int> g[10001]; //[u]<-v, u를 선행으로 가지고 있는 노드들 

void init() {
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
		cin >> out[i]; 
		int u = i;
		for (int j = 0; j < out[i]; j++) {
			int v; cin >> v;
			g[v].push_back(u); // [v]를 선행으로 가지고 있는 u
		}
	}
}

void solve() {
	stack<int> s;
	for (int i = 1; i <= N; i++) if (out[i] == 0) s.push(i);

	while (!s.empty()) {
		int cur = s.top(); s.pop();

		et[cur] = st[cur] + arr[cur]; //지금까지 max값인 st로 최종 et 계산

		for (int i = 0; i < g[cur].size(); i++) {
			int next = g[cur][i];
			st[next] = max(st[next], et[cur]);//선행작업의 끝나는 시간들 중 최대값 갱신
			if (--out[next] == 0) {// next->cur이라서 next--;
				s.push(next);
			}
		}
	}

	ll ans = 0;
	for (int i = 1; i <= N; i++) ans = max(ans, et[i]);
	cout << ans << endl;
}

int main() {
	freopen("sample_input.txt", "r", stdin);

	init();
	solve();

	fclose(stdin);
}