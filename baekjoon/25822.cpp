#include<iostream>
#include<algorithm>
using namespace std;
int N;
int Strick[3];
float C;

int main() {
	cin >> C >> N;
	int coin = C / 0.99;
	int maxProblem = 0, maxStrick = 0;
	for (int i = 0; i < N; i++) {
		int p;
		cin >> p;
		maxProblem = max(maxProblem, p);
		if (p) {
			Strick[0]++;
			Strick[1]++;
			Strick[2]++;
		}
		else {
			Strick[2] = Strick[1] + 1;
			Strick[1] = Strick[0] + 1;
			Strick[0] = 0;
		}

		switch (coin) {
		case 0:
			maxStrick = max(maxStrick, Strick[0]);
			break;
		case 1:
			maxStrick = max(maxStrick, Strick[1]);
			break;
		default:
			maxStrick = max(maxStrick, Strick[2]);
			break;
		}		
	}
	cout << maxStrick << '\n' << maxProblem ;
}