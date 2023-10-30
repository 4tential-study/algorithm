#include <iostream>
#include <vector>

using namespace std;

// 사람의 수, 파티 수
int N, M;

// 진실을 아는 사람의 수
int C;
vector<int> v;

int visitedParty[51];
bool visitedPerson[51];

// party[i] : i 파티에 참여하는 사람들
vector<int> party[51];
// person[i] : i가 참여하는 파티들의 번호
vector<int> person[51];

void enterPerson(int x);
void enterParty(int x);

// x 파티는 진실을 안다.
void enterParty(int x) {
	for (int i = 0; i < party[x].size(); i++) {
		if (!visitedPerson[party[x][i]]) {
			visitedPerson[party[x][i]] = true;
			enterPerson(party[x][i]);
		}
	}
}

// x가 진실을 안다.
void enterPerson(int x) {
	for (int i = 0; i < person[x].size(); i++) {
		if (visitedParty[person[x][i]]) {
			visitedParty[person[x][i]] = 0;
			enterParty(person[x][i]);
		}
	}
}

int main() {
	cin >> N >> M;

	for (int i = 1; i <= M; i++) {
		visitedParty[i] = i;
	}

	cin >> C;
	int tmp;
	for (int i = 0; i < C; i++) {
		cin >> tmp;
		// tmp는 비밀을 안다.
		v.push_back(tmp);
	}

	int a;
	for (int i = 1; i <= M; i++) {
		cin >> a;
		for (int j = 0; j < a; j++) {
			cin >> tmp;
			
			// i파티에는 tmp가 참여한다.
			party[i].push_back(tmp);

			// tmp는 i 파티에 참가한다.
			person[tmp].push_back(i);
		}
	}

	for (int i = 0; i < C; i++) {
		enterPerson(v[i]);
	}

	int cnt = 0;
	for (int i = 1; i <= M; i++) {
		if (visitedParty[i] != 0) ++cnt;
	}

	cout << cnt << '\n';

	return 0;
}