#include <string>
#include <vector>
#include<queue>
using namespace std;

int calDiff(string word1, string word2) {
    int cnt = 0;
    for(int i=0; i<word1.size(); i++) {
        if(word1[i] != word2[i]) {
            cnt++;
        }
    }
    return cnt;
}

int solution(string begin, string target, vector<string> words) {
    int answer = 0;
    vector<int> visited;
    queue<string> current;
    queue<int> cnt;
    current.push(begin);
    cnt.push(0);
    visited.resize(words.size(), 0);
    
    while(!current.empty()){
        string cur = current.front();
        current.pop();
        int c = cnt.front();
        cnt.pop();
        if(cur == target) {
            answer = c;
            break;
        }
        for(int i=0; i<words.size(); i++){
            if(visited[i] == 0 && calDiff(cur, words[i]) == 1) {
                current.push(words[i]);
                cnt.push(c+1);
                visited[i] = 1;
            }
        }
    }
    
    return answer;
}
