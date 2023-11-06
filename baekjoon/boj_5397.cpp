#include <iostream>
#include <string>
using namespace std;

struct NODE {
	char c;
	NODE* prev;
	NODE* next;
};

NODE HEAD, TAIL;
int pcnt;
NODE POOL[1000010];

string str;

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	int T; cin >> T;

	for (int tc = 1; tc <= T; ++tc) {
		// 초기화
		pcnt = 0;
		HEAD = { -1, &TAIL, &TAIL };
		TAIL = { -1, &HEAD, &HEAD };

		cin >> str;

		NODE* ptr = &HEAD;
		for (int i = 0; i < str.length(); ++i) {
			if (str[i] == '<') {
				if (ptr != &HEAD) {
					ptr = ptr->prev;
				}
			}
			else if (str[i] == '>') {
				if (ptr->next != &TAIL) {
					ptr = ptr->next;
				}
			}
			else if (str[i] == '-') {
				if (ptr != &HEAD) {
					ptr->prev->next = ptr->next;
					ptr->next->prev = ptr->prev;
					ptr = ptr->prev;
				}
			}
			else {
				POOL[pcnt] = { str[i], ptr, ptr->next };
				ptr->next->prev = &POOL[pcnt];
				ptr->next = &POOL[pcnt];
				++pcnt;
				ptr = ptr->next;
			}
		}

		NODE* res = HEAD.next;
		while (res != &TAIL) {
			cout << res->c;
			res = res->next;
		}
		cout << '\n';
	}

	return 0;
}

