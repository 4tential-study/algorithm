#include<iostream>
#include<queue>
#include<tuple>
using namespace std;
int N, M;
int Map[1001][1001];
int Visited[2][1001][1001];

bool inRange(int x, int y) {
	return x > 0 && y > 0 && x <= N && y <= M;
}

int bfs() {
	queue < tuple<int, int, int, int>> bfs;
	bfs.emplace(1, 1, 0, 1);
	Visited[0][1][1] = 1;
	int cnt = -1;
	int deltas[4][2] = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	while (!bfs.empty()) {
		int a, b, w, c;
		tie(a, b, w, c) = bfs.front();
		bfs.pop();

		if (a == N && b == M) {
			cnt = c;
			break;
		}

		for (int i = 0; i < 4; i++) {
			int x = a + deltas[i][0];
			int y = b + deltas[i][1];
            if(inRange(x, y)){
                if(!Visited[w][x][y]){
                    if(Map[x][y]==0){
                        Visited[w][x][y] = 1;
                        bfs.emplace(x, y, w, c+1);
                    }
                    else if(w==0){
                        Visited[1][x][y] = 1;
                        bfs.emplace(x, y, 1, c+1);
                    }
                }
            }
			
		}
	}
	return cnt;
}


int main() {
	cin >> N >> M;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			char c;
			cin >> c;
			Map[i][j] = c - '0';
		}
	}

	cout << bfs();
}