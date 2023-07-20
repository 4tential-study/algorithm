#include<iostream>
#include<algorithm>
using namespace std;
int N, D, K, C;
int Sushi[300001];
int Eat[3001] = {0, };

int main() {
	cin >> N >> D >> K >> C;
	for (int i = 0; i < N; i++) {
		cin >> Sushi[i];
	}
	
	int ans = 1;
	int begin = 0, end = K - 1;				//첫번째 연속초밥
	for (int i = 0; i <= end; i++) {
		Eat[Sushi[i]]++;
	}

	int n = 0;
	while (n++ < N) {
		Eat[C]++; 							//bonus
		int num = 0;
		for (int i = 0; i <= D; i++) {		//초밥 먹었다면(0이상) 카운트해서 집계
			if (Eat[i] > 0)
				num++;
		}
		ans = max(ans, num);				//최댓값 갱신
		Eat[Sushi[begin]]--;				//첫번째 초밥 제거
		begin = begin + 1 < N ? begin + 1 : begin + 1 - N;
		end = end + 1 < N ? end + 1 : end + 1 - N;
		Eat[Sushi[end]]++;					//다음 초밥 추가
	}
	cout << ans;
}