#include <iostream>
#include <vector>
#include <set>
using namespace std;

set<int> Myset;
void recursion(int, int, vector<int>);

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int size;	 cin >> size;
	vector<int> seq;
	for (int i = 0; i < size; i++) {
		int value;	cin >> value;
		seq.push_back(value);
	}

	recursion(0, 0, seq);

	int result = 1;
	while (true) {
		if (Myset.find(result) != Myset.end())	result++;
		else break;
	}

	cout << result;
}

void recursion(int index, int sum, vector<int> v) {
	if (index == v.size()) {
		Myset.insert(sum);	return;
	}

	recursion(index + 1, sum + v[index], v);
	recursion(index + 1, sum, v);
}