// 작년에 풀었던 코드도 같이 올려봅니다
#include <iostream>
using namespace std;
int Integer[3] = { 0, };
void NinthTree(int**, int, int, int);
int main()
{
	int N;
	cin >> N;
	int** board = new int*[N];
	for (int i = 0; i < N; i++) {
		board[i] = new int[N];
		for (int j = 0; j < N; j++)		cin >> board[i][j];
	}
	NinthTree(board, N, 0, 0);
	for (int i = 0; i < 3; i++)	cout << Integer[i] << endl;
}

void NinthTree(int** board, int size, int y, int x) {
	int check = board[y][x];
	for (int i = y; i < y + size; i++) {
		for (int j = x; j < x + size; j++) {
			if (check != board[i][j])	check = 2;
			if (check == 2) {
				int row = size / 3;
				for (int a = 0; a < 3; a++) {
					for (int b = 0; b < 3; b++) 
						NinthTree(board, row, y + row * a, x + row * b);	
				}
				return;
			}
		}
	}
	if (check != 2) {
		Integer[check + 1]++;
	}
	
}