#include <iostream>
#include <algorithm>
#include <string.h>
#include <climits>
#include <cassert>
#include <cmath>
#include <vector>
#pragma warning(disable:4996)
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;

int N, NN;
int box[26][4];
int boxofCnt[26];//[cnt]<-num: [자리]<-상자번호
int picked[26];//[boxnum]=rotateCnt//[상자]<-상자가 시계방향 회전한 횟수
bool flag = false;

void init() {
	cin >> N;
	NN = N * N;
	for (int i = 0; i < NN; i++) {
		int n; cin >> n;
		for (int j = 0; j < 4; j++) cin >> box[n][j];
	}
	memset(picked, -1, sizeof(picked));
	memset(boxofCnt, -1, sizeof(boxofCnt));
}

int nofB(int num, int w) {
	int x = (w - picked[num] + 4) % 4;
	return box[num][x];
}

bool valid(int cnt, int num) {//cnt자리에 num상자 놓아도 되는지 확인
	//1. 모서리면 0조건 확인
	if (cnt / N == 0) if (nofB(num, 0) != 0) return false;
	if (cnt % N == 0) if (nofB(num, 3) != 0) return false;
	if (cnt % N == N-1) if (nofB(num, 1) != 0) return false;
	if (cnt / N == N-1) if (nofB(num, 2) != 0) return false;


	//2. 위, 왼쪽 상자랑 맞닿는 면 확인
	int topNum = boxofCnt[cnt - N];
	int leftNum = boxofCnt[cnt - 1];
	if (topNum >= 0) {
		int tboxofBottom = nofB(topNum, 2);
		int boxofTop = nofB(num, 0);
		if (tboxofBottom != boxofTop) return false;
	}
	if (leftNum >= 0) {
		int lboxofRight = nofB(leftNum, 1);
		int boxofLeft = nofB(num, 3);
		if (lboxofRight != boxofLeft) return false;
	}
	return true;
}

void solve(int cnt) {//cnt자리에 놓을 상자를 정함
	if (cnt == NN) {
		for (int i = 0; i < NN; i++) {
			cout << boxofCnt[i] << " ";
			if (i % N == N - 1) cout << endl;
		}
		for (int i = 0; i < NN; i++) {
			int num = boxofCnt[i];
			cout << picked[num] << " ";//상자의 회전횟수
			if (i % N == N - 1) cout << endl;
		}
		exit(0);
		/*return;*/
	}
	
	for (int i = 1; i <= NN; i++) {//num=1~NN 상자 고르기
		if (picked[i] == -1) {
			int num = i;
			for (int j = 0; j < 4; j++) {
				int rcnt = j;
				picked[num] = rcnt;
				if (valid(cnt, num)) {
					boxofCnt[cnt] = num;
					solve(cnt + 1);
					boxofCnt[cnt] = -1;
				}
				picked[num] = -1;
			}
		}
	}

}

int main() {
	freopen("sample_input.txt", "r", stdin);
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	init();
	solve(0);

	fclose(stdin);
}