#include<iostream>
#include<vector>
#include<queue>
using namespace std;

int N;

int time[501];

int inDegree[501];

int cTime[501];

vector<int> arr[501];

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	cin >> N;

	int k, tmp;
	for (int i = 1; i <= N; ++i) {
		cin >> time[i];
		
		while (true) {
			cin >> tmp;
			if (tmp == -1) break;

			// tmp 이후에 i 설치
			arr[tmp].push_back(i);
			++inDegree[i];
		}
	}

	queue<int> q;

	for (int i = 1; i <= N; ++i) {
		if (inDegree[i] == 0) {
			q.push(i);
			cTime[i] = time[i];
		}
	}

	while (!q.empty()) {
		int cur = q.front();
		q.pop();

		for (int i = 0; i < arr[cur].size(); ++i) {
			int next = arr[cur][i];
			int nc = cTime[cur] + time[next];

			if (inDegree[next] == 0) continue;
			
			if (cTime[next] < nc) cTime[next] = nc;

			if (--inDegree[next] == 0) {
				q.push(next);
			}
		}
	}

	for (int i = 1; i <= N; ++i) {
		cout << cTime[i] << '\n';
	}

	return 0;
}