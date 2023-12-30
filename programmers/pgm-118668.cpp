#include <string>
#include <vector>
#include <algorithm>
#include <iostream>
#include <climits>
#include <cassert>
#include <cmath>
#include <string.h>
#include <queue>
#include <unordered_map>
using namespace std;
typedef pair<int, int> pii;
typedef pair<int, pii> pip;

int my, mx;
int N;
//y<=my, x<=mx로 유지하는데, y+30,x+30인 경우가 있어서-> 150+30=180
int chked[181][181];
vector<vector<int>> arr;

//y, x가 1, 30인 경우-> 1*150, 30*150= 150, 4500이 되어서 배열 초과+탐색 범위 너무 많음
//y>=my, x<mx 인경우 -> [y][x], [my][x]인 상황과 다르지 않아서(x를 my만큼 더해야할 뿐)
//같은 케이스로 취급해도 되나 봄 -> 그러면서 엄청 빨라진 것 같은데
//솔직히 정해는 아닌 것 같다
int bfs(int sy, int sx, int st) {
    //다익스트라
    priority_queue<pip, vector<pip>, greater<pip>> q;
    q.push({ st,{sy,sx} });
    for (int i = 0; i <= 180; i++)for (int j = 0; j <= 180; j++) chked[i][j] = INT_MAX;
    chked[sy][sx] = st;

    int ans = INT_MAX;
    while (!q.empty()) {
        int y = q.top().second.first;
        int x = q.top().second.second;
        int t = q.top().first;
        q.pop();

        if (y >= my && x >= mx) {
            return t;
        }

        y = min(y, my);
        x = min(x, mx);

        if (t > chked[y][x]) continue;


        //1.
        int ny = y + 1, nx = x, nt = t + 1;
        if (y < my && nt < chked[ny][nx]) {
            q.push({ nt,{ny,nx} });
            chked[ny][nx] = nt;
        }
        //2.
        ny = y, nx = x + 1, nt = t + 1;
        if (x < mx && nt < chked[ny][nx]) {
            q.push({ nt,{ny,nx} });
            chked[ny][nx] = nt;
        }
        //3.
        for (int i = 0; i < N; i++) {
            if (arr[i][0] <= y && arr[i][1] <= x) {
                //시간이 y증가량+x증가량보다 크면
                //y+1, x+1해서 150,150만드는게 제일 빠름
                if (arr[i][4] >= arr[i][2] + arr[i][3]) continue;
                ny = y + arr[i][2], nx = x + arr[i][3], nt = t + arr[i][4];
                //nt>300 도 마찬가지
                if (nt < chked[ny][nx] && nt <= 300) {
                    q.push({ nt,{ny,nx} });
                    chked[ny][nx] = nt;
                }
            }
        }
    }

    return ans;
}

int solution(int alp, int cop, vector<vector<int>> problems) {
    arr = problems;
    N = arr.size();
    my = 0, mx = 0;
    for (int i = 0; i < N; i++) {
        my = max(my, arr[i][0]);
        mx = max(mx, arr[i][1]);
    }

    return bfs(alp, cop, 0);
    // return 0;
}
