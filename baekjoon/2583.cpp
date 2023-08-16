#include<iostream>
#include<stack>
#include<vector>
#include<algorithm>
using namespace std;
int M, N, K;
int Paper[101][101];

bool inRange(int x, int y) {
    return x>=0 && y>=0 && x<M && y<N;
}
int dfs(int i, int j) {
    stack<pair<int,int>> dfs;
    dfs.emplace(i, j);
    Paper[i][j] = 1;
    int deltas[4][2]={{1,0}, {0,1}, {-1,0}, {0,-1}};
    int cnt=0;

    while(!dfs.empty()){
        int x = dfs.top().first;
        int y = dfs.top().second;
        dfs.pop();  
        cnt++;

        for(int i=0; i<4; i++){
            int nx = x+deltas[i][0];
            int ny = y+deltas[i][1];
            
            if(inRange(nx, ny) && Paper[nx][ny] == 0){
                Paper[nx][ny] = 1;
                dfs.emplace(nx, ny);
            }
        }
    }
    return cnt;
}

int main(){
    cin >> M >> N >> K;
    for(int i=0; i<K; i++){
        int b1, a1, b2, a2;
        cin >> b1 >> a1 >> b2 >> a2;
        for(int j=a1; j<a2; j++){
            for(int k=b1; k<b2; k++){
                Paper[j][k] = 1;
            }
        }
    }
    vector<int> area;
    for(int i=0; i<M; i++){
        for(int j=0; j<N; j++){
            if(Paper[i][j] == 0) {
                area.push_back(dfs(i, j));
            }
        }
    }
    sort(area.begin(), area.end());
    cout << area.size() << '\n';
    for(int a : area) {
        cout << a << ' ';
    }
}