#include<iostream>
#include<string>
using namespace std;

int main() {
	string s;
	cin >> s;
	int isPalin = 1;
	int isOneChar = 1;
	char c = s[0];
	for (int i = 0; i < s.size(); i++) {
		if (s[i] != s[s.size() - i - 1]) {
			isPalin = 0;
		}
		if (c != s[i]) {
			isOneChar = 0;
		}
	}
	int ans = -1;
	if (isPalin && !isOneChar) {
		ans = s.size() - 1;
	}
	else if(!isPalin) {
		ans = s.size();
	}
	cout << ans;
}