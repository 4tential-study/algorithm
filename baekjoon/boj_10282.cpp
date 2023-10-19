#include <iostream>
#include <string.h>
#include <algorithm>
#include <climits>
#include <cmath>
#include <cassert>
#include <vector>
#include <queue>
#define MAX 987654321
#pragma warning(disable:4996)
using namespace std;
typedef long long int ll;
typedef pair<int, int> pii;
#define endl '\n'


vector<pii> g[10001];
priority_queue<pii, vector<pii>, greater<>> q;

int N, M, root;
int ans, cnt;

void init() {
    cin >> N >> M >> root;
    for (int i = 0; i < M; i++) {
        int a, b, w; cin >> a >> b >> w;
        //b가 감염되어야 a가 감염됨
        //b->a
        g[b].push_back({ a,w });
    }
}

//시간 순으로 감염되기 때문에
//깊이(dfs)순서가 아니라 시간(priority_queue)순으로 감염시켜야 했음
int dist[10001];
void solve(int src) {
    for (int i = 0; i <= N; i++) dist[i] = INT_MAX;
    
    q.push({ 0,src });
    dist[src] = 0;
    int ans = 0, cnt = 0;
    while (!q.empty()) {
        int u = q.top().second;
        int time = q.top().first;
        q.pop();

        if (dist[u]<time) continue;
        ans = max(ans, time);
        cnt++;

        for (int i = 0; i < g[u].size(); i++) {
            int v = g[u][i].first;
            int w = g[u][i].second;
            if (time+w<dist[v]) {
                dist[v] = dist[u] + w;
                q.push({ time + w,v });
            }
        }
    }
    cout << cnt << " " << ans << endl;
}

int main() {
    freopen("sample_input.txt", "r", stdin);
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

    int tc; cin >> tc;
    while (tc--) {
        init();
        solve(root);
        for (int i = 0; i <= N; i++) if (!g[i].empty()) g[i].clear();
    }
    

    fclose(stdin);
}
