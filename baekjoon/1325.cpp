#include<iostream>
#include<vector>
#include<cstring>
using namespace std;
int N, M, Cnt;
vector<int> Trust[10001];
int Visited[10001];
vector<int> Temp;
void dfs(int curr) {
	Visited[curr] = 1;
	Temp.push_back(curr);
	for (int i = 0; i < Trust[curr].size(); i++) {
		if (!Visited[Trust[curr][i]]) {
			dfs(Trust[curr][i]);
		}
	}
}

int main() {
	cin >> N >> M;
	for (int i = 0; i < M; i++) {
		int a, b;
		cin >> a >> b;
		Trust[b].push_back(a);
	}
	vector<int> v;
	int size = 0;
	for (int i = 1; i <= N; i++) {
		Temp.clear();
		memset(Visited, 0, sizeof(Visited));
		dfs(i);
		if (Temp.size() > size) {
			size = Temp.size();
			v.clear();
			v.push_back(i);
		}
		else if (Temp.size() == size) {
			v.push_back(i);
		}
	}
	for (int t : v) {
		cout << t << ' ';
	}
}