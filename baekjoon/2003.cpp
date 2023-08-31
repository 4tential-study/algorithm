#include<iostream>
#include<algorithm>
using namespace std;
int T, N, M;
int A[10001];

int main() {
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> A[i];
	}
	int cnt = 0;
	for (int i = 0; i < N; i++) {
		long long sum = 0;
		for (int j = i; j < N; j++) {
			sum += A[j];
			if (sum == M) {
				cnt++;
			}
			else if (sum > M) {
				break;
			}
		}
	}
	cout << cnt;
}