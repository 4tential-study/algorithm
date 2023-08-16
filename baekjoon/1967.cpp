#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;
int N, Ans;
vector<pair<int,int>> Edge[10001];
int Visited[10001];

void dfs(int r, int v) {
    if (Edge[v].size() == 0) {
        Ans = max(Ans, r);
        return;
    }
    for (int i = 0; i < Edge[v].size(); i++) {
        int child = Edge[v][i].first;
        int nr = Edge[v][i].second;
        if (!Visited[child]) {
            Visited[child] = 1;
            dfs(r + nr, child);
            //Visited[child] = 0;
        }
    }
}

int main() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        Edge[a].emplace_back(b, c);
    }
    dfs(0, 1);
    cout << Ans;
}