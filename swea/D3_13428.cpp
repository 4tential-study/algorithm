// SWEA 13428 숫자 조작 (D3)_완전 탐색
#include <iostream>
#include <string>
#include <climits>
#include <cmath>
using namespace std;

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int T;	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		string board;	cin >> board;
		int min = stoi(board), max = stoi(board);
		int size = board.length();
		if (size >= 2) {
			for (int i = 0; i < size; i++) {
				for (int j = i + 1; j < size; j++) {
					string value = board;
					value[i] = board[j];		value[j] = board[i];
					int result = stoi(value);
					if (result < pow(10, (size - 1)))	continue;
					min = (min > result) ? result : min;
					max = (max < result) ? result : max;
				}
			}
		}
		cout << "#" << tc << " ";
		cout << min << " " << max << endl;
	}
}