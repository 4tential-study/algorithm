#include <string>
#include <vector>
#include <string.h>
#include <iostream>
#include <algorithm>
#include <climits>
#include <cassert>
#include <cmath>
using namespace std;
#pragma warning(disable:4996)
#define endl '\n'
typedef pair<int, int> pii;
typedef pair<char, int> pci;

vector<vector<int>> board;
int N, M;
int way[4][2] = { {-1,0},{0,1},{1,0},{0,-1} };

//백트래킹으로 이길 수 있으면 최소 이동 수, 질 수 밖에 없으면 최대 이동 수 반환
//p가 움직일 차례일 때 A가 a위치, B가 b위치 일때 (cnt는 경우의 수에는 필요없고 그냥 디버깅용)
// return {이기는 사람('a' or 'b'), 이동 수}
pci solve(char p, pii a, pii b, int cnt) {
    //y,x 세팅
    int y, x;
    if (p == 'a') y = a.first, x = a.second;
    else y = b.first, x = b.second;

    bool canMove = false; //4방향으로 이동 가능한지 여부
    char winner = 'a' + 'b' - p; //이길 수 있으면 나, 질 수 밖에 없으면 상대방(일단 상대로 초기화)
    int maxLen = INT_MIN, minLen = INT_MAX; //이동 수
    //4방향에 따라 처리
    for (int w = 0; w < 4; w++) {
        int ny = y + way[w][0], nx = x + way[w][1];
        //w방향으로 이동할 수 있으면
        if (ny >= 0 && nx >= 0 && ny < N && nx < M && board[ny][nx] == 1) {
            canMove = true;
            //같은 자리에 있었으면 내가 먼저 움직여서 남은애가 패배
            //*패배하는 경우2때문에
            if (a.first == b.first && a.second == b.second) return { p,1 };

            board[y][x] = 0; //발판 없애놓고 실행
            pci ret;
            if (p == 'a') ret = solve('b', { ny,nx }, b, cnt + 1);
            else ret = solve('a', a, { ny,nx }, cnt + 1);
            board[y][x] = 1; //돌아왔으니까 발판 다시 원래대로

            if (ret.first == p) {//이길 수 있으면
                minLen = min(minLen, ret.second);//이동 수 최소값 갱신
                winner = p;
            }
            //여태까지 이기지 못했고, 이번 선택도 이기 못했을 때-> 최대값 갱신
            else if (winner != p) {//ret.first!=p&&winner!=p
                maxLen = max(maxLen, ret.second);
            }
        }//if
    }//for문
    //패배하는 경우1: 움직일 차례인데 4칸 모두 이동불가
    if (canMove == false) {
        assert(winner != p);
        return { winner,0 };//안움직였으니까 0
    }
    //내가 이긴 경우
    if (winner == p) return { winner,minLen + 1 };
    //내가 진 경우
    return { winner,maxLen + 1 };
}

int solution(vector<vector<int>> _board, vector<int> aloc, vector<int> bloc) {
    board = _board;
    int ay = aloc[0], ax = aloc[1], by = bloc[0], bx = bloc[1];
    N = board.size(), M = board[0].size();

    auto ret = solve('a', { ay,ax }, { by,bx }, 0);
    int answer = ret.second;
    return answer;
}