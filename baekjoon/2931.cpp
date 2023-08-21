#include<iostream>
#include<unordered_map>
using namespace std;
int Block[7][4][2] = {
	{{-1, 0}, {1, 0}, {0,  0}, {0, 0}},	// |
	{{ 0, 0}, {0, 0}, {0, -1}, {0, 1}},	// -
	{{-1, 0}, {1, 0}, {0, -1}, {0, 1}},	// +
	{{ 0, 0}, {1, 0}, {0,  0}, {0, 1}},	// 1
	{{-1, 0}, {0, 0}, {0,  0}, {0, 1}},	// 2
	{{-1, 0}, {0, 0}, {0, -1}, {0, 0}},	// 3
	{{ 0, 0}, {1, 0}, {0, -1}, {0, 0}}	// 4
};
unordered_map<char, int> Type{
	{'|', 0},
	{'-', 1},
	{'+', 2},
	{'1', 3},
	{'2', 4},
	{'3', 5},
	{'4', 6}
};
unordered_map<int, char> ReType{
	{3,  '|'},
	{12, '-'},
	{15, '+'},
	{5,  '1'},
	{6,  '2'},
	{10, '3'},
	{9,  '4'}
};
int R, C, X, Y;
char Map[26][26];
int Status = 0;

bool inRange(int x, int y) {
	return x >= 0 && y >= 0 && x < R && y < C;
}

void checkBlock(int x, int y, int type) {
	for (int i = 0; i < 4; i++) {
		int a = x + Block[type][i][0];
		int b = y + Block[type][i][1];

		if (inRange(a, b) && Map[a][b] == '.') {
			Status |= (1 << i);
			X = a;
			Y = b;
		}
	}
}

int main() {
	cin >> R >> C;
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			cin >> Map[i][j];
		}
	}
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if (Type.count(Map[i][j]) > 0) {
				int type = Type[Map[i][j]];
				checkBlock(i, j, type);
			}
		}
	}
	
	cout << X + 1 << ' ' << Y + 1 << ' ' << ReType[Status];
}