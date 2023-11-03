#include <string>
#include <vector>
#include <iostream>
using namespace std;
int R, C;
int Temp[1001][1001];

bool inRange(int x, int y) {
    return x>=0 && y>=0 && x<R && y<C;
}

int solution(vector<vector<int>> board, vector<vector<int>> skill) {
    int answer = 0;
    R = board.size();
    C = board[0].size();
    for(int i=0; i<skill.size(); i++) {
        int type = skill[i][0];
        int r1 = skill[i][1];
        int c1 = skill[i][2];
        int r2 = skill[i][3];
        int c2 = skill[i][4];
        int degree = type == 1 ? -1*skill[i][5] : skill[i][5];
        
        int point[4][2] = {{r1-1, c1-1}, {r1-1, c2}, {r2, c2}, {r2, c1-1}};
        for(int j=0; j<4; j++) {
            int x = point[j][0];
            int y = point[j][1];
            if(inRange(x, y)) {
                if(j%2 == 0) {
                    Temp[x][y] += degree; 
                }
                else {
                    Temp[x][y] -= degree; 
                }
            }
        }
    }
    for(int i=R-1; i>=0; i--) {
        for(int j=C-1; j>=0; j--) {
            int temp = Temp[i][j];
            if(inRange(i+1, j))   temp += Temp[i+1][j];
            if(inRange(i, j+1))   temp += Temp[i][j+1];
            if(inRange(i+1, j+1)) temp -= Temp[i+1][j+1];
            Temp[i][j] = temp;
            if(board[i][j] + temp > 0) {
                answer++;
            } 
        }
    }
    return answer;
}