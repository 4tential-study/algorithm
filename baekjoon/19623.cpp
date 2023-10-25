#include<iostream>
#include<vector>
#include<tuple>
#include<algorithm>
using namespace std;
int N;
int People[100001];
vector<tuple<int, int, int>> MeetingRooms;

int binarySearch(int target) {
	int begin = 0, end = target;
	while (begin + 1 < end) {
		int mid = (begin + end) / 2;
		if (get<1>(MeetingRooms[target]) >= get<0>(MeetingRooms[mid])) {
			begin = mid;
		}
		else {
			end = mid;
		}
	}
	return begin;
}

int main() {
	cin >> N;

	MeetingRooms.emplace_back(0, 0, 0);
	for (int i = 1; i <= N; i++) {
		int b, e, p;
		cin >> b >> e >> p;
		MeetingRooms.emplace_back(e, b, p);
	}
	sort(MeetingRooms.begin(), MeetingRooms.end());

	for (int i = 1; i <= N; i++) {
		int target = binarySearch(i);
		People[i] = get<2>(MeetingRooms[i]) + People[target];
		People[i] = max(People[i], People[i - 1]);
	}
	cout << People[N];
}