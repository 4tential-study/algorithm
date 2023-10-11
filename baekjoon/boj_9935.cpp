#include <iostream>
#include <string>
#include <stack>

using namespace std;

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	string str, key;
	cin >> str;
	cin >> key;

	// { 문자, idx }
	// k == -1 : 문자열을 만들 수 없음.
	// 0 ~ k번 까지의 key값이 st에 연속적으로 들어가 있음.
	stack<pair<char, int>> st;

	for (int i = 0; i < str.length(); i++) {
		// 첫 삽입의 경우
		if (st.empty()) {
			if (str[i] == key[0]) st.push({ str[i], 0 });
			else st.push({ str[i], -1 });
		}
		// key의 첫 문자인 경우
		else if (str[i] == key[0]) {
			st.push({ str[i], 0 });
		}
		// 문자열이 이어지는 경우
		else if (str[i] == key[st.top().second + 1]) {
			st.push({ str[i], st.top().second + 1 });
		}
		// 상관 없는 문자열
		else {
			st.push({ str[i], -1 });
		}

		// 문자열이 완성된 경우 key의 길이만큼 pop해주기
		if (st.top().second == key.length() - 1) {
			for (int i = 0; i < key.length(); i++) st.pop();
		}
	}

	// st에 반대로 들어가 있으므로 다시 정리
	string answer;
	while (!st.empty()) {
		answer += st.top().first;
		st.pop();
	}

	// 정답 출력
	if (answer.length() == 0) cout << "FRULA" << '\n';
	else {
		for (int i = answer.length() - 1; i >= 0; i--) {
			cout << answer[i];
		}
		cout << '\n';
	}

	return 0;
}

// 여담 cpp
/*
	stack을 사용한 경우 문자열의 반대 순서로 stack에 들어가 있으므로
	반대로 출력해야합니다.
	
	1. 불가능(시간초과)
	while (!st.empty()) {
		answer = st.top().first + answer;
		st.pop();
	}
	
	처음에 이렇게 했는데 시간초과가 났습니다.
	
	answer += 'A'로 문자열을 만드는 경우 
	마지막에 문자를 추가하는 방식이므로 시간복잡도는 O(N)입니다.

	answer = 'A' + answer로 문자열을 만드는 경우 
	매번 새로운 문자열을 만들기 때문에 최악의 경우 시간복잡도는 O(N^2)입니다.
 	(= 문자열의 길이만큼 밀어주고 삽입하기 때문에)

	2. 가능
	처음부터 문자열을 반대로 탐색하기.

	3. 가능(제가 한거)
	stack은 뒤에서 부터 접근이 안되기 때문에
	string에 넣고 반대로 출력하기
*/

// java
/*
	메모리초과가 나는 경우 고려해볼 것.
 
	java의 경우 string은 상수풀에 저장이 됩니다.
 	이 때 +연산으로 string이 변경된 경우 기존 문자열을 변경하는 것이 아니라
  	새로운 문자열을 생성하여 메모리를 할당하기 때문에 메모리초과가 납니다.

   	++
	stringbuffer의 경우 기존 문자열을 변경하기 때문에 메모리 초과는 나지 않습니다.
*/
