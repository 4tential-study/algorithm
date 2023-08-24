#include<iostream>
#include<tuple>
#include<queue>
#include<cstring>
using namespace std;
int N, M, R, C, T, L;
int Ternel[51][51];
int Visited[51][51];
int Deltas[4][2] = { {-1, 0}, {0, -1}, {1, 0}, {0, 1} };
int Block[7][4] = {
    {1, 1, 1, 1},   // +
    {1, 0, 1, 0},   // |
    {0, 1, 0, 1},   // -
    {1, 0, 0, 1},   // ㄴ
    {0, 0, 1, 1},   // r
    {0, 1, 1, 0},   // ㄱ
    {1, 1, 0, 0}    // 
};
 
bool inRange(int x, int y) {
    return x >= 0 && y >= 0 && x < N && y < M;
}
 
bool isValid(int x, int y, int d) {
    int direct = (d + 2) % 4;
    int type = Ternel[x][y];
    return type != 0 && Block[type-1][direct];
}
int main() {
    cin >> T;
    for (int t = 1; t <= T; t++) {
        cin >> N >> M >> R >> C >> L;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cin >> Ternel[i][j];
            }
        }
        queue<tuple<int, int, int>> bfs;
        bfs.emplace(R, C, 1);
        Visited[R][C] = 1;
        int cnt = 1;
        while (!bfs.empty()) {
            int x, y, t;
            tie(x, y, t) = bfs.front();
            bfs.pop();
            if (t == L) {
                break;
            }
            int type = Ternel[x][y] - 1;
            for (int i = 0; i < 4; i++) {
                if (Block[type][i]) {
                    int nx = x + Deltas[i][0];
                    int ny = y + Deltas[i][1];
                    if (inRange(nx, ny) && !Visited[nx][ny] && isValid(nx, ny, i)) {
                        cnt++;
                        bfs.emplace(nx, ny, t + 1);
                        Visited[nx][ny] = 1;
 
                    }
                }
            }
 
        }
        memset(Visited, 0, sizeof(Visited));
        cout << '#' << t << ' ' << cnt << '\n';
    }   
}