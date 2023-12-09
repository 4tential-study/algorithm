#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <string.h>
#include <climits>
#include <cmath>
#include <cassert>
#pragma warning(disable:4996)
using namespace std;
typedef pair<int, int> pii;

//기본 유니온파인드인데 y,x좌표<-부모y,x좌표
pii parent[51][51];
//y,x좌표<-string값
string arr[51][51];
vector<string> answer;

//문자열을 " "로 자르기
vector<string> split(string str) {
    vector<string> ret;
    char* p = strtok((char*)str.c_str(), " ");
    while (p) {
        ret.push_back(p);
        p = strtok(NULL, " ");
    }
    return ret;
}

//기본 유니온파인드인데 int u대신 pii u
pii find(pii u) {
    if (u.first == parent[u.first][u.second].first && u.second == parent[u.first][u.second].second) return u;
    return find(parent[u.first][u.second]);
}

void merge(pii u, pii v) {
    u = find(u);
    v = find(v);
    if (u == v) return;
    //기본적으로 u의 값을 가지지만
    //u비어있고 v가 값이 있으면 v값으로
    if (arr[u.first][u.second] == "EMPTY" && arr[v.first][v.second] != "EMPTY") {
        parent[u.first][u.second] = v;
        arr[u.first][u.second] = "EMPTY";
    }
    else {
        parent[v.first][v.second] = u;
        arr[v.first][v.second] = "EMPTY";
    }
}

vector<string> solution(vector<string> commands) {
    for (int i = 1; i <= 50; i++)for (int j = 1; j <= 50; j++) {
        parent[i][j] = { i,j };
        arr[i][j] = "EMPTY";
    }

    for (auto command : commands) {
        auto sv = split(command);
        // cout<<endl;
        if (sv[0] == "UPDATE") {
            if (sv.size() == 4) {// r c val
                // cout<<sv[1]<<" "<<sv[2]<<" "<<sv[3]<<endl;
                int r = stoi(sv[1]);
                int c = stoi(sv[2]);
                string val = sv[3];
                //부모 좌표 가져오고
                pii p = find({ r,c });
                //부모 좌표 값을 update
                arr[p.first][p.second] = val;
            }
            else {//val1 val2
                string val1 = sv[1], val2 = sv[2];
                //값을 가지고 있으면 걔가 root일거니까 그냥 변경만
                for (int i = 1; i <= 50; i++)for (int j = 1; j <= 50; j++) {           
                    if (arr[i][j] == val1) {
                        arr[i][j] = val2;
                    }
                }
            }
        }
        else if (sv[0] == "MERGE") {
            int r1 = stoi(sv[1]);
            int c1 = stoi(sv[2]);
            int r2 = stoi(sv[3]);
            int c2 = stoi(sv[4]);
            //단순 merge
            merge({ r1,c1 }, { r2,c2 });
        }
        else if (sv[0] == "UNMERGE") {
            int r = stoi(sv[1]);
            int c = stoi(sv[2]);
            //부모 좌표 가져오고
            //미리 해당 셀값 저장
            pii p = find({ r,c });
            string val = arr[p.first][p.second];
            vector<pii> v;
            //동시 문제때문에(중간에 parent관계를 변경하면 자식을 전부 가져오지못함)
            //미리 자식들을 다 찾아서 vector에 넣고 한꺼번에 처리
            for (int i = 1; i <= 50; i++)for (int j = 1; j <= 50; j++) {
                pii pp = find({ i,j });
                //find(i,j)==find(r,c)
                if (pp.first == p.first && pp.second == p.second) {
                    v.push_back({ i,j });
                }
            }
            for (pii yx : v) {
                int y = yx.first, x = yx.second;
                //초기화해줌
                parent[y][x] = { y,x };
                arr[y][x] = "EMPTY";
            }
            arr[r][c] = val;
        }
        else {
            assert(sv[0] == "PRINT");
            //해당 셀값을 출력해야하니까
            //root의 값을 넣기
            int r = stoi(sv[1]);
            int c = stoi(sv[2]);
            pii p = find({ r,c });
            answer.push_back(arr[p.first][p.second]);
        }

    }

    // for(int i=1;i<=4;i++){
    //     for(int j=1;j<=4;j++){
    //         pii p=find({i,j});
    //         cout<<arr[p.first][p.second]<<" ";
    //     }cout<<endl;
    // }

    return answer;
}