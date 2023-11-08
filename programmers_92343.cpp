#include <string>
#include <vector>
#include <set>
using namespace std;

int N, answer;

vector<int> v[18];

void solve(int k, set<int> visited, int sheep, int wolf, vector<int> info){
    if(info[k] == 1) ++wolf;
    else ++sheep;
    
    if(wolf >= sheep) return;
    
    visited.erase(k);
    
    if(sheep > answer) answer = sheep;
    
    for(int i = 0; i < v[k].size(); ++i) 
        visited.insert(v[k][i]);
    
    for(auto iter = visited.begin(); iter != visited.end(); ++iter){
        solve(*iter, visited, sheep, wolf, info);
    }
}

int solution(vector<int> info, vector<vector<int>> edges) {
    
    N = info.size();
    
    for(int i = 0; i < edges.size(); ++i){
        v[edges[i][0]].push_back(edges[i][1]);
    }
    
    solve(0, {0}, 0, 0, info);
    
    return answer;
}