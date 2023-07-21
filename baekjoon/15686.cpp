#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;
int N, M, Min;
int City[51][51];
vector<pair<int, int>> Chickens, Houses;
vector<int> Valid;


int getDistance(pair<int, int> h, pair<int, int> c) {
	return abs(c.first - h.first) + abs(c.second - h.second);
}
int getTotalDistance() {
	int total = 0;
	for (int i = 0; i < Houses.size(); i++) {
		int dist = 2 * N;
		for (int j = 0; j < Chickens.size(); j++) {
			if (Valid[j]) {
				dist = min(dist, getDistance(Houses[i], Chickens[j]));
			}
		}
		total += dist;
	}
	return total;
}


void shutdown(int cnt, int next) {
	if (cnt == Chickens.size()-M){
 		Min = min(Min, getTotalDistance());
		return;
	}
	for (int i = next; i < Chickens.size(); i++) {
		Valid[i] = 0;
		shutdown(cnt + 1, i+1);
		Valid[i] = 1;
	}
}

int main() {
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> City[i][j];
			if (City[i][j] == 1) Houses.emplace_back(i, j);
			else if (City[i][j] == 2) Chickens.emplace_back(i, j);
		}
	}

	Valid.resize(Chickens.size(), 1);
	Min = 2 * N * 2 * N;

	shutdown(0, 0);

	cout << Min;
}