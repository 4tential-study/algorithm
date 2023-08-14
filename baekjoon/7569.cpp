#include<iostream>
#include<queue>
#include<tuple>
using namespace std;
int N, M, H, Cnt;
int Box[101][101][101];
int Visited[101][101][101];
queue<tuple<int, int, int, int>> Tomatos;

bool isAvailable(int x, int y, int z) {
    return x>=0 && y>=0 && z>=0 && x<N && y<M && z<H && !Visited[x][y][z] && Box[x][y][z] == 0;
}
int check() {
    for(int i=0; i<H; i++){
        for(int j=0; j<N; j++){
            for(int k=0; k<M; k++){
                if(Box[j][k][i] == 0){
                    return -1;
                }
            }
        }
    }
    return Cnt;
}
int main() {
    cin >> M >> N >> H;
    for(int i=0; i<H; i++){
        for(int j=0; j<N; j++){
            for(int k=0; k<M; k++){
                cin >> Box[j][k][i];
                if(Box[j][k][i]==1) {
                    Tomatos.emplace(j, k, i, 0);
                }
            }
        }
    }
    int deltas[6][3] = {{1,0,0}, {0,1,0}, {-1,0,0}, {0,-1,0}, {0,0,1}, {0,0,-1}};
    while(!Tomatos.empty()){
        int x, y, z;
        tie(x, y, z, Cnt) = Tomatos.front();
        Tomatos.pop();
        for(int i=0; i<6; i++){
            int nx = x+deltas[i][0];
            int ny = y+deltas[i][1];
            int nz = z+deltas[i][2];
            
            if(isAvailable(nx, ny, nz)) {
                Visited[nx][ny][nz] = 1;
                Box[nx][ny][nz] = 1;
                Tomatos.emplace(nx, ny, nz, Cnt+1);
            }
        }
    }
    
    cout << check();
}