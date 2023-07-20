#include<iostream>
#include<algorithm>
#include<vector>
#include<set>
using namespace std;
int N;
int S[21];

int main() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> S[i];
	}

	set<int> nums;						//부분수열의 합을 set에 저장(중복제거를 위해)
	for (int i = 1; i <= N; i++) {		//조합으로 완탐
		vector<int> temp;
		temp.resize(N, 0);
		for (int j = 0; j < i; j++) temp[N-j-1] = 1;
		do {
			int num = 0;
			for (int k = 0; k < N; k++) {
				if (temp[k]) {
					num += S[k];
				}
			}
			nums.insert(num);
		} while (next_permutation(temp.begin(), temp.end()));
	}
	
	int ans = 1;

	for(int temp : nums) { //1부터 하나씩 증가시키면서 부분수열의 합과 비교하여 작은 값 찾기
		if(ans != temp) {
			break;
		}
		ans++;
	}
	cout << ans;
	return 0;
}