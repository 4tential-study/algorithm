#include <iostream>
#include <string>
#include <vector>

using namespace std;

int N, M;

int check[1001][1001];

int solution(vector<vector<int>> board, vector<vector<int>> skill) {
    int answer = 0;
    
    N = board.size();
    M = board[0].size();
    
    // 사각형 범위에 일정한 값을 더할 때
    // x2,y2 왼쪽위 +, x1의 위쪽 -, y1의 왼쪽 -, x1,y1 왼쪽위 +
    // 백준에 비슷한 문제 있어요
    int x1, y1, x2, y2, deg;
    for(int i = 0; i < skill.size(); ++i){
        x1 = skill[i][1];
        y1 = skill[i][2];
        x2 = skill[i][3];
        y2 = skill[i][4];
        deg = skill[i][0] == 1 ? -skill[i][5] : skill[i][5];
        
        check[x2][y2] += deg;
        if (x1 != 0) check[x1 - 1][y2] -= deg;
        if (y1 != 0) check[x2][y1 - 1] -= deg;
        if (x1 != 0 && y1 != 0) check[x1 - 1][y1 - 1] += deg;
    }
    
    // 반대에서부터 누적합 식으로 계산하기
    // 좀 더 간단하게 작성할 수 있을 것 같은데 일단 이렇게...
    for(int i = N - 1; i >= 0; --i){
        int tmp = 0;
        for(int j = M - 1; j >= 0; --j){
			tmp += check[i][j];

			board[i][j] += tmp;

			if (i != 0) check[i - 1][j] += check[i][j];
        }
    }
    
    // 정답체크
    for(int i = 0; i < N; ++i){
        for(int j = 0; j < M; ++j){
            if(board[i][j] > 0) ++answer;
        }
    }
    
    return answer;
}