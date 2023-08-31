#include<iostream>
#include<algorithm>
using namespace std;
int N;
int Prev[2][3];
int Curr[2][3];
int main() {
	cin >> N;
	int num;
	for (int i = 1; i <= N; i++) {
		for (int j = 0; j < 3; j++) {
			cin >> num;
			switch (j) {
			case 0:
				Curr[0][0] = num + max(Prev[0][0], Prev[0][1]);
				Curr[1][0] = num + min(Prev[1][0], Prev[1][1]);
				break;
			case 1:
				Curr[0][1] = num + max(max(Prev[0][0], Prev[0][1]), Prev[0][2]);
				Curr[1][1] = num + min(min(Prev[1][0], Prev[1][1]), Prev[1][2]);
				break;
			case 2:
				Curr[0][2] = num + max(Prev[0][1], Prev[0][2]);
				Curr[1][2] = num + min(Prev[1][1], Prev[1][2]);
				break;
			}
		}
		for (int j = 0; j < 3; j++) {
			Prev[0][j] = Curr[0][j];
			Prev[1][j] = Curr[1][j];
		}
	}

	int maxScore = max(max(Prev[0][0], Prev[0][1]), Prev[0][2]);
	int minScore = min(min(Prev[1][0], Prev[1][1]), Prev[1][2]);
	
	cout << maxScore << ' ' << minScore;
}