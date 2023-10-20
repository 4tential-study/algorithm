#include <iostream>
#include <set>
#include <queue>

using namespace std;

struct bottle {
	int a, b, c;
};

bool visited[201][201][201];

set<int> s;

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	int A, B, C;
	cin >> A >> B >> C;

	queue<bottle> q;
	q.push({ 0,0,C });

	while (!q.empty()) {
		bottle cur = q.front();
		q.pop();

		if (visited[cur.a][cur.b][cur.c]) continue;
		visited[cur.a][cur.b][cur.c] = true;

		// A에 담긴 물의 양이 0일 때 insert
		if (cur.a == 0) s.insert(cur.c);

		/*
			A물통에서 B물통으로 물을 옮기는 경우
			
			1. B 물통에 물이 넘치는 경우
				a -> a + b - B, b -> B, c -> c
			2. B 물통에 모두 담기는 경우
				a -> 0, b -> a + b, c -> c
		*/

		// A -> B
		if (cur.a + cur.b > B) q.push({ cur.a + cur.b - B, B, cur.c});
		else q.push({0, cur.a + cur.b, cur.c});

		// A -> C	
		if (cur.a + cur.c > C) q.push({ cur.a + cur.c - C, cur.b, C });
		else q.push({ 0, cur.b, cur.a + cur.c });

		// B -> A		
		if (cur.b + cur.a > A) q.push({ A, cur.b + cur.a - A, cur.c });
		else q.push({ cur.b + cur.a, 0, cur.c });

		// B -> C		
		if (cur.b + cur.c > C) q.push({ cur.a, cur.b + cur.c - C, C });
		else q.push({ cur.a, 0, cur.b + cur.c });

		// C -> A			
		if (cur.c + cur.a > A) q.push({ A, cur.b, cur.c + cur.a - A });
		else q.push({ cur.c + cur.a , cur.b, 0 });

		// C -> B		
		if (cur.c + cur.b > B) q.push({ cur.a, B, cur.c + cur.b - B });
		else q.push({ cur.a, cur.b + cur.c, 0 });
	}
	
	// set은 자동 정렬이므로 순서대로 출력
	for (auto iter = s.begin(); iter != s.end(); ++iter) {
		cout << *iter;
		if (iter != --s.end()) cout << ' ';
		else cout << '\n';
	}

	// 아.. 생각해보니까 어차피 중복이 존재하지 않으니까 set을 사용할 필요가 없네요

	return 0;
}