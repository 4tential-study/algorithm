#include<iostream>
#include<vector>
#include<queue>
#include<cstring>
#include<algorithm>
using namespace std;
int N, M, V;
vector<int> Edge[1001];
int Visited[1001];

void bfs() { 
    queue<int> bfs;
    bfs.push(V);
    Visited[V] = 1;
    while(!bfs.empty()){
        int curr = bfs.front();
        bfs.pop();
        cout << curr << ' ';
        for(int i=0; i<Edge[curr].size(); i++){
            if(!Visited[Edge[curr][i]]) {
                Visited[Edge[curr][i]] = 1;
                bfs.push(Edge[curr][i]);
            }
        }
    }
}
void dfs(int curr) {
    cout << curr << ' ';
    Visited[curr] = 1;
    sort(Edge[curr].begin(), Edge[curr].end());
    for(int i=0; i<Edge[curr].size(); i++) {
        if(!Visited[Edge[curr][i]]) {
            dfs(Edge[curr][i]);
        }
    }
}

int main() {
    cin >> N >> M >> V;
    for(int i=0; i<M; i++){
        int a, b;
        cin >> a >> b;
        Edge[a].push_back(b);
        Edge[b].push_back(a);
    }
    dfs(V);
    memset(Visited, 0, sizeof(Visited));
    cout << '\n';
    bfs();
}