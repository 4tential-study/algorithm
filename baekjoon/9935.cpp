#include<string>
#include<iostream>
#include<deque>
using namespace std;
string Str, Bomb;

int main() {
	cin >> Str >> Bomb;
	
	deque<char> target;
	for (int i = 0; i < Str.size(); i++) {
		target.push_back(Str[i]);

		string tBomb = Bomb;
		deque<char> temp;
		while (!target.empty() && !tBomb.empty() && target.back() == tBomb.back()) {
			temp.push_back(target.back());
			target.pop_back(); 
			tBomb.pop_back();
		}
		if (!tBomb.empty()) {
			while (!temp.empty()) {
				target.push_back(temp.back());
				temp.pop_back();
			}
		}
	}

	if (target.empty()) {
		cout << "FRULA";
		return 0;
	}
	
	while (!target.empty()) {
		cout << target.front();
		target.pop_front();
	}
}