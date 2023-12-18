#include <string>
#include <vector>
#include <algorithm>
#include <iostream>
#include <string.h>
#include <climits>
#include <cassert>
#include <cmath>
#include <queue>
#include <unordered_map>
#pragma warning(disable:4996)
using namespace std;
typedef pair<string, int> pii;
unordered_map<string, int> arr;
unordered_map<string, int> checked;



int bfs(string src, string dest) {
    queue<pii> q;
    q.push({ src,0 });
    checked[src] = 1;
    while (!q.empty()) {
        string cur = q.front().first;
        int cnt = q.front().second;
        q.pop();

        if (cur == dest) return cnt; //1부터 시작했으니까 cnt+1-1

        for (int i = 0; i < cur.length(); i++) {
            for (int j = 0; j < 26; j++) {
                string next = cur; next[i] = 'a' + j;
                if (arr[next] == 1 && checked[next] == 0) {
                    checked[next] = 1;
                    q.push({ next,cnt + 1 });
                }
            }
        }
    }
    return 0;
}

int solution(string begin, string target, vector<string> words) {
    for (auto word : words) {
        arr[word] = 1;
    }

    int answer = bfs(begin, target);
    return answer;
}