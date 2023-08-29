#include<iostream>
#include<algorithm>
using namespace std;
int N, M;
int Lecture[100001];

int count(int m) {
	int cnt = 1, sum = 0;
	for (int i = 0; i < N; i++) {
		sum += Lecture[i];
		if (sum > m) {
			cnt++;
			sum = Lecture[i];
		}
	}
	return cnt;
}

int main() {
	cin >> N >> M;
	int total = 0;
	for (int i = 0; i < N; i++) {	
		cin >> Lecture[i];
		total += Lecture[i];
	}
	int begin = *max_element(Lecture, Lecture + N) - 1;
    int end = total + 1;

	while (begin + 1 < end) {
		int mid = (begin + end) / 2;
		if (count(mid) > M) {
			begin = mid;
		}
		else {
			end = mid;
		}
	}
	cout << end;
}