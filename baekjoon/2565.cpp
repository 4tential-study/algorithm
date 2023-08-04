#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
int N, K;
vector<pair<int, int>> E;
int Lis[101];

int main(){
	cin >> N;
	for (int i = 1; i <= N; i++) {
		int a, b;
		cin >> a >> b;
		E.emplace_back(a, b);
	}
	sort(E.begin(), E.end());
	
	int m = 1;
	for (int i = 0; i < N; i++) {
		Lis[i] = 1;
		for (int j = 0; j < i; j++) {
			if (E[i].second > E[j].second) {
				Lis[i] = max(Lis[i], Lis[j] + 1);
			}
		}
		m = max(m, Lis[i]);
	}
	cout << N - m;
}