#include<iostream>
#include<string>
using namespace std;

string str1, str2, str3;

// i, j
bool visited[201][201];

// 만들 수 있는가?
bool res;

// DFS 탐색
void solve(int i, int j, int k) {
	if (res || k == str3.length()) {
		res = true;
		return;
	}

	// 문자가 일치하고, 이전에 탐색한지 없는지 확인
	if (i < str1.length() && str1[i] == str3[k] && !visited[i + 1][j]) {
		visited[i + 1][j] = true;
		solve(i + 1, j, k + 1);
	}
	if (j < str2.length() && str2[j] == str3[k] && !visited[i][j + 1]) {
		visited[i][j + 1] = true;
		solve(i, j + 1, k + 1);
	}
}

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	int T; cin >> T;

	for (int tc = 1; tc <= T; ++tc) {
		// input
		cin >> str1 >> str2 >> str3;

		// init
		for (int i = 0; i <= str1.length(); ++i) {
			for (int j = 0; j <= str2.length(); ++j) {
				visited[i][j] = false;
			}
		}

		// solve
		res = false;
		solve(0, 0, 0);

		// res말고 visited[str1.length()][str.length]로 체크해도 되겠네요

		// output
		if (res) cout << "Data set " << tc << ": yes\n";
		else cout << "Data set " << tc << ": no\n";
	}

	return 0;
}