#include<iostream>
#include<string>
#include<algorithm>
#include<vector>
using namespace std;
int L, C;
char Alpha[16];
vector<char> Vowel, Consotant;
vector<vector<char>> Vset, Cset;

void comb(int cnt, int next, vector<char>& set, vector<char>& alpha, vector<vector<char>>& combSet) {
	if (cnt == set.size()) {
		combSet.push_back(set);
		return;
	}

	for (int i = next; i < alpha.size(); i++) {
		set[cnt] = alpha[i];
		comb(cnt + 1, i + 1, set, alpha, combSet);
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	cin >> L >> C;
	string s = "aeiou";
	for (int i = 0; i < C; i++) {
		cin >> Alpha[i];
		if (s.find(Alpha[i]) == string::npos)	Consotant.push_back(Alpha[i]);
		else									Vowel.push_back(Alpha[i]);
	}

	for (int i = 1; i <= L - 2; i++) {
		vector<char> temp;
		temp.resize(i);
		comb(0, 0, temp, Vowel, Vset);			//모음 조합
		temp.resize(L-i);
		comb(0, 0, temp, Consotant, Cset);		//자음 조합
	}

	vector<string> password;
	for (int i = 0; i < Vset.size(); i++) {		//조합 합치기
		for (int j = 0; j < Cset.size(); j++) {
			vector<char> temp{ Vset[i] };

			if (Vset[i].size() + Cset[j].size() == L) {
				temp.insert(temp.end(), Cset[j].begin(), Cset[j].end());
				sort(temp.begin(), temp.end());
				
				string s{ temp.begin(), temp.end() };
				password.push_back(s);
			}
		}
	}

	sort(password.begin(), password.end());
	for (string s : password) {
		cout << s << '\n';
	}
}