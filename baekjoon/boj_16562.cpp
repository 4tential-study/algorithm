#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int N, M, K;

// v[i] = {a, b};
// b와 친구하는 비용 a
vector<pair<int, int>> cost;

// 친구인지 확인(union)
int parent[10001];

int finds(int a) 
{
    if (a == parent[a]) return a;
    else return parent[a] = finds(parent[a]);
}

void unions(int a, int b) 
{
    int pa = finds(a);
    int pb = finds(b);

    if (pa != pb) {
        parent[pb] = pa;
    }
}

int main() 
{   
    // 학생 수, 관계 수, 머니
    cin >> N >> M >> K;

    int c;  
    for (int i = 1; i <= N; i++) 
    {
        // 친구비
        cin >> c;

        // i와 친구하는 비용 c
        cost.push_back({ c, i });

        // 초기화
        parent[i] = i;
    }

    // 친구비가 적은 순으로 정렬
    sort(cost.begin(), cost.end());

    int a, b;
    for (int i = 0; i < M; i++)
    {
        // a, b가 친구이다.
        cin >> a >> b;
        unions(a, b);
    }

    // 비용의 합
    int sum = 0;

    // 친구 사귀기 시작(크루스칼)
    for (int i = 0; i < cost.size(); i++) 
    {
        // 준석이와 친구가 아니면
        if (finds(cost[i].second) != 0) 
        {
            // 친구하기
            unions(0, cost[i].second);

            // 친구비 추가
            sum += cost[i].first;
        }
    }

    // cost 배열에 1 ~ N번까지의 친구가 전부 들어가 있으므로
    // 모든 친구를 사귀지 못하는 경우는 없다.

    if (sum > K) 
    {
        // 친구비가 모자르면
        cout << "Oh no" << '\n';
    }
    else 
    {
        cout << sum << '\n';
    }

    return 0;
}