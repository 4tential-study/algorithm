#include<iostream>
#include<queue>
#include<vector>
using namespace std;
int R, C;
int Sheep = 0, Wolf = 0;
char Yard[251][251];
int Visited[251][251];
vector<pair<int,int>> SheepV;

bool inRange(int x, int y){
    return x >= 0 && y >= 0 && x < R && y < C;
}

void bfs(pair<int,int> p) {
    queue<pair<int,int>> q;
    q.push(p);
    int sheep = 1;
    int wolf = 0;
    Visited[p.first][p.second] = 1;

    int deltas[4][2] = {{1,0},{-1,0},{0,1},{0,-1}};
    while(!q.empty()) {
        pair<int,int> curr = q.front();
        q.pop();

        for(int i=0; i<4; i++){
            int x = curr.first + deltas[i][0];
            int y = curr.second + deltas[i][1];
            
            if(inRange(x, y) && !Visited[x][y] && Yard[x][y] != '#'){
                Visited[x][y] = 1;
                q.emplace(x, y);
                if(Yard[x][y] == 'o'){
                    sheep++;
                }
                else if(Yard[x][y] == 'v'){
                    wolf++;
                }
            }
        }
    }
    if(sheep > wolf){
        Wolf -= wolf;
    }
    else {
        Sheep -= sheep;
    }
}

int main() {
    cin >> R >> C;
    for(int i=0; i<R; i++){
        for(int j=0; j<C; j++){
            cin >> Yard[i][j];
            if(Yard[i][j] == 'o'){
                SheepV.emplace_back(i, j);
                Sheep++;
            }
            else if(Yard[i][j] == 'v'){
                Wolf++;
            }
        }
    }
    while(!SheepV.empty()){
        if(!Visited[SheepV.back().first][SheepV.back().second])
            bfs(SheepV.back());
        SheepV.pop_back();
    }
    cout << Sheep << ' ' << Wolf;
}
