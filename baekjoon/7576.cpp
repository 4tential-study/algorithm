#include<iostream>
#include<queue>
#include<tuple>
using namespace std;
int N, M;
int Box[1001][1001];
int Visited[1001][1001]={{0,},};

queue<tuple<int,int,int>> tomato;

bool check(){
    for(int i=1; i<=M; ++i){
        for(int j=1; j<=N; ++j){
            if(Box[i][j] == 0)
                return false;
        }
    }
    return true;
}
bool inRange(int a, int b) {
    return a>=1 && b>=1 && a<=M && b<=N && Box[a][b] != -1;
}
int main(){
    cin >> N >> M;
    for(int i=1; i<=M; ++i){
        for(int j=1; j<=N; ++j){
            cin >> Box[i][j];
            if(Box[i][j] == 1){
                tomato.emplace(i,j,0);
            }
        }
    }
    int cnt;
    int deltas[4][2] = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    while(!tomato.empty()){
        int a, b;
        tie(a,b,cnt) = tomato.front();
        tomato.pop();
        for(int i=0; i<4; i++){
            int x = a+deltas[i][0];
            int y = b+deltas[i][1];
            
            if(inRange(x, y) && !Visited[x][y]) {
                Visited[x][y] = 1; 
                Box[x][y] = 1;
                tomato.emplace(x, y, cnt+1);
            }
        }
    }
    if(check()) 
        cout << cnt;
    else        
        cout << -1;
}