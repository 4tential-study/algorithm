#include<iostream>

using namespace std;

int len;
char pan[2190][2190];

void set_star(int sy, int sx, int now_len) {
	//cout << sy <<" "<< sx <<" "<< now_len << endl;
	if (now_len == 1) {
		pan[sy][sx] = '*';
		return;
	}
	int i, k, next_len = now_len/3;
	for (i = sy; i < sy + now_len; i+=next_len) {
		for (k = sx; k < sx + now_len; k += next_len) {
			if (!(i == sy + next_len && k == sx + next_len)) {
				set_star(i, k, next_len);
			}
		}
	}
}

void solve() {

	int i, k;
	for (i = 1; i <= len; ++i) {
		for (k = 1; k <= len; ++k) {
			pan[i][k] = ' ';
		}
	}
	set_star(1, 1, len);
	for (i = 1; i <= len; ++i) {
		for (k = 1; k <= len; ++k) {
			cout << pan[i][k];
		}
		cout << "\n";
	}
}

int main(void)
{
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	cin >> len;
	solve();
	return 0;
}