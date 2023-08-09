#include<iostream>
#include<tuple>
#include<queue>
using namespace std;
int N;
int l, x1, y1, x2, y2;
        
bool inRange(int x, int y) {
    return x>=0 && y>=0 && x<l  && y<l;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0), cout.tie(0);
    int deltas[8][2] = {{-2, -1}, {-1, -2},{2, 1},{1, 2},{2, -1},{-1, 2},{1, -2},{-2, 1}};
    cin >> N;
    for(int i=0; i<N; ++i){
        int visited[301][301]={{0,}};
        cin >> l >> x1 >> y1 >> x2 >> y2;

        queue<tuple<int,int,int>> track;
        track.emplace(x1,y1,0);
        int x, y, c;
            
        while(!track.empty()){
            tie(x, y, c) = track.front();
            track.pop();
            if(x == x2 && y == y2) 
                break;

            for(int j=0; j<8; j++){
                int a = x + deltas[j][0];
                int b = y + deltas[j][1];
                if(inRange(a, b) && !visited[a][b]) {
                    visited[a][b] = 1; 
                    track.emplace(a, b, c+1);
                }
            }
        }
        cout << c << '\n';
    }    
}