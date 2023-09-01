#include<iostream>
#include<algorithm>
#include<queue>
#include<vector>
using namespace std;
int N, E;
int U, V;
int Dist[801];
vector<pair<int, int>> Edges[801];

void djistra(int start) {
	priority_queue<pair<int, int>, vector<pair<int,int>>, greater<pair<int,int>>> pq;
	Dist[start] = 0;
	pq.emplace(Dist[start], start);

	while (!pq.empty()) {
		int cost = pq.top().first;
		int curr = pq.top().second;
		pq.pop();

		for (pair<int,int>& v : Edges[curr]) {
			if (cost + v.first < Dist[v.second]) {
				Dist[v.second] = cost + v.first;
				pq.emplace(Dist[v.second], v.second);
			}
		}
	}
}


int main() {
	cin >> N >> E;
	for (int i = 0; i < E; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		Edges[a].emplace_back(c, b);
		Edges[b].emplace_back(c, a);
	}
	cin >> U >> V;

	fill(Dist, Dist + N + 1, 200000000);
	djistra(1);
	int sToU = Dist[U];
	int sToV = Dist[V];

	fill(Dist, Dist + N + 1, 200000000);
	djistra(U);
	int uToV = Dist[V];
	int uToN = Dist[N];

	fill(Dist, Dist + N + 1, 200000000);
	djistra(V);
	int vToU = Dist[U];
	int vToN = Dist[N];

	int cand1 = sToU + uToV + vToN;
	int cand2 = sToV + vToU + uToN;

	int minCost = min(cand1, cand2);
	if (minCost >= 200000000) {
		minCost = -1;
	}

	cout << minCost;
}