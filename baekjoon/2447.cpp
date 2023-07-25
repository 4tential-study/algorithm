#include<iostream>
using namespace std;
int N;

void printPattern(int depth, int x, int y) {
	
	if (x/depth % 3 == 1 && y/depth % 3 == 1) {
		cout << ' ';
	}
	else {
		if (depth == 1) {
			cout << '*';
			return;
		}
		printPattern(depth / 3, x, y);
	}
}
int main(){
	cin >> N;
	
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			printPattern(N, i, j);
		}
		cout << '\n';
	}
}