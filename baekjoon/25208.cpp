#include<iostream>
#include<tuple>
#include<queue>
using namespace std;
int N, M, Dx, Dy, Rx, Ry;
char Board[501][501];
int Visited[501][501][6];
int Deltas[4][2] = { {1,0}, {0,1}, {-1,0}, {0,-1} };
int Box[6][4] = { 
                { 4, 3, 5, 1 },
                { 1, 0, 1, 2 },
                { 5, 1, 4, 3 },
                { 3, 2, 3, 0 },
                { 2, 4, 0, 4 },
                { 0, 5, 2, 5 },
};

bool inRange(int x, int y) {
    return x >= 0 && y >= 0 && x < N && y < M;
}

bool canCatch(int x, int y, int box) {
    return !(x == Rx && y == Ry && box != 0);
}

int main() {
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> Board[i][j];
            if (Board[i][j] == 'D') {
                Dx = i;
                Dy = j;
            }
            else if (Board[i][j] == 'R') {
                Rx = i;
                Ry = j;
            }
        }
    }

    Visited[Dx][Dy][0] = 1;
    queue<tuple<int, int, int, int>> bfs;
    bfs.emplace(Dx, Dy, 0, 0);
    int ans = -1;

    while (!bfs.empty()) {
        int x, y, box, cnt;
        tie(x, y, box, cnt) = bfs.front();
        bfs.pop();

        if (x == Rx && y == Ry) {
            ans = cnt;
            break;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + Deltas[i][0];
            int ny = y + Deltas[i][1];
            int nBox = Box[box][i];
            
            if (inRange(nx, ny) && !Visited[nx][ny][nBox] && Board[nx][ny] != '#' && canCatch(nx, ny, nBox)) {
                Visited[nx][ny][nBox] = 1;
                bfs.emplace(nx, ny, nBox, cnt + 1);
            }
        }
    }

    cout << ans;
}