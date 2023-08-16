#include<iostream>
#include<vector>
#include<queue>
using namespace std;
int N, M, Cnt;
int Iceberg[301][301];
int Exist[301][301];
vector<pair<int,int>> Ice;

bool inRange(int x, int y) {
    return x>=0 && y>=0 && x<N && y<M;
}

void melt() {
    int deltas[4][2]={{1,0}, {0,1}, {-1,0}, {0,-1}};
    int s = Ice.size();
    for(int i=s-1; i>=0; i--){
        int cnt=0;
        pair<int,int>& p = Ice[i];
        for(int j=0; j<4; j++){
            int x = p.first + deltas[j][0];
            int y = p.second + deltas[j][1];
            
            if(inRange(x, y) && Exist[x][y]){
                cnt++;
            }
        }
        Iceberg[p.first][p.second] -= cnt;
        if(Iceberg[p.first][p.second] == 0){
            Ice.pop_back();
            Exist[p.first][p.second] = 0;
        }
    }
}

int bfs() {
    int cnt=0;
    int Visited[301][301] = {{0,},};
    int deltas[4][2]={{1,0}, {0,1}, {-1,0}, {0,-1}};
    
    for(pair<int,int>& p: Ice) {
        if(!Visited[p.first][p.second]){
            cnt++;
            queue<pair<int,int>> bfs;
            bfs.push(p);
            Visited[p.first][p.second] = 1;
            while(!bfs.empty()) {
                int x = bfs.front().first;
                int y = bfs.front().second;
                bfs.pop();

                for(int j=0; j<4; j++){
                    int nx = x + deltas[j][0];
                    int ny = y + deltas[j][1];
                    
                    if(inRange(nx, ny) && Iceberg[nx][ny] > 0 && !Visited[nx][ny]){
                        Visited[nx][ny] = 1;
                        bfs.emplace(nx, ny);
                    }
                }
            }
        }    
    }
    return cnt;
}



int main() {
    int N, M;
    cin >> N >> M;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cin >> Iceberg[i][j];
            if(Iceberg[i][j] > 0){
                Exist[i][j] = 1;
                Ice.emplace_back(i, j);
            }
        }
    }
    int ans = 0;
    while(!(bfs() > 2)){
        melt();
        ans++;
    }
    cout << ans;
}