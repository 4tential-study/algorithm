#include <string>
#include <vector>
#include <algorithm>
#include <string.h>
#include <cassert>
#include <climits>
#include <cmath>
#include <iostream>
#include <queue>
using namespace std;
typedef pair<int,int> pii;

int N,S,A,B;
vector<pii> g[201];

//����� ���ͽ�Ʈ��
vector<int> dijkstra(int src){
    vector<int> dist(N+1,INT_MAX);
    priority_queue<pii> q;
    q.push({0,src});
    dist[src]=0;
    
    while(!q.empty()){
        int u=q.top().second;
        int src_u=q.top().first;
        q.pop();
        
        for(int i=0;i<g[u].size();i++){
            int v=g[u][i].first;
            int u_v=g[u][i].second;
            if(src_u==INT_MAX) continue;
            
            if(src_u+u_v<dist[v]){
                dist[v]=src_u+u_v;
                q.push({dist[v],v});
            }
        }
    }
    return dist;
}

int solution(int n, int s, int a, int b, vector<vector<int>> _fares) {
    
    N=n, S=s,A=a,B=b;
    //�׳� ���� �ƴ� �׷����� ������ֱ�
    for(auto e:_fares){
        int u=e[0], v=e[1], w=e[2];
        g[u].push_back({v,w});
        g[v].push_back({u,w});
    }
    
    auto distS=dijkstra(S);
    auto distA=dijkstra(A);
    auto distB=dijkstra(B);
    
    //S->i->A
    //S->i->B �̷��� ���� �� S->i, i->A, i->B �� �ִܰŸ� �հ�
    
    //S->A, S->B�� ���� �ǵ�
    
    //i=S�� S-S + S->A + S->B�� ���� ���� ��쵵 for���� ���Ե�
    int answer = INT_MAX;
    for(int i=1;i<=N;i++){
        int s_i=distS[i];
        int i_A=distA[i];
        int i_B=distB[i];
        int sum=s_i+i_A+i_B;
        answer=min(answer,sum);
    }
    return answer;
}