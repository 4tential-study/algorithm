#include<iostream>
#include<vector>
using namespace std;
int Sedoku[9][9];
int Found = 0;
vector<pair<int, int>> Blank;

bool isPromising(int idx) {
	int x = Blank[idx].first;
	int y = Blank[idx].second;
	int nx = x / 3 * 3;
	int ny = y / 3 * 3;

	for (int i = 0; i < 9; ++i) {
		int a = nx + i / 3;
		int b = ny + i % 3;
		if (Sedoku[x][y] == Sedoku[i][y] && i != x)
			return false;
		if (Sedoku[x][y] == Sedoku[x][i] && i != y)
			return false;	
		if (Sedoku[a][b] == Sedoku[x][y] && a != x && b != y)
			return false;
	}
	return true;
}
void solve(int idx) {
	if (Found) 
		return;
	if (idx == Blank.size()) {
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j)
				cout << Sedoku[i][j];
			cout << '\n';
		}
		Found = 1;
		return;
	}

	for (int i = 1; i <= 9; ++i) {
		Sedoku[Blank[idx].first][Blank[idx].second] = i;
		if (isPromising(idx)) solve(idx + 1);
	}
	Sedoku[Blank[idx].first][Blank[idx].second] = 0;
}
int main() {
	char c;
	for (int i = 0; i < 9; i++) {
		for (int j = 0; j < 9; j++) {
			cin >> c;
			Sedoku[i][j] = c - '0';
			if (Sedoku[i][j] == 0)
				Blank.emplace_back(make_pair(i, j));
		}
	}
	solve(0);
}