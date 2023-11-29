#include <iostream>
#include <string.h>
#include <algorithm>
#include <climits>
#include <cmath>
#include <cassert>
#define MAX 987654321
#pragma warning(disable:4996)
using namespace std;

int N, M, K;
//sum[0][0]~sum[y][x]의 누적합=sum2[y][x]=board[y][x]에 총 더해야하는 값
//sum2[y][x]+board[y][x]=최종 맵[y][x]의 결과 값
int sum[1001][1001];
int sum2[1001][1001];
int solution(vector<vector<int>> board, vector<vector<int>> skill) {
	N = board.size(), M = board[0].size(), K = skill.size();

	for (int i = 0; i < K; i++) {
		int type = (skill[i][0] == 1) ? -1 : 1;
		int r1 = skill[i][1], c1 = skill[i][2], r2 = skill[i][3], c2 = skill[i][4], degree = skill[i][5];
		sum[r1][c1] += degree * type;
		if (c2 + 1 < M) sum[r1][c2 + 1] -= degree * type;
		if (r2 + 1 < N) sum[r2 + 1][c1] -= degree * type;
		if (c2 + 1 < M&&r2 + 1 < N) sum[r2 + 1][c2 + 1] += degree * type;
	}


	int cnt = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			sum2[i][j] = sum[i][j];
			if (i - 1 >= 0) sum2[i][j] += sum2[i - 1][j];
			if (j - 1 >= 0) sum2[i][j] += sum2[i][j - 1];
			if (i - 1 >= 0 && j - 1 >= 0) sum2[i][j] -= sum2[i - 1][j - 1];
			if (sum2[i][j] + board[i][j] > 0) cnt++;
		}
	}
	return cnt;

}
