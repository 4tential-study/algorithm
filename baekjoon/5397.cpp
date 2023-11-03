#include<iostream>
#include<list>
using namespace std;
int T;

int main() {
    cin >> T;
    for(int t=0; t<T; t++) {
        string s;
        cin >> s;
        //cout << s << endl;
        list<char> key;
        auto cursor = key.begin();
        for(char c : s) {
            switch (c){
            case '<':
                if(cursor != key.begin()) {
                    cursor--;
                }
                break;
            case '>':
                if(cursor != key.end()) {
                    cursor++;
                }
                break;
            case '-':
                if(!key.empty()) {
                    if(cursor != key.begin()) {
                        cursor--;
                        cursor = key.erase(cursor);
                    }
                }
                break;
            
            default:
                cursor = key.insert(cursor, c);
                cursor++;
                break;
            }
        }

        for(char c : key) {
            cout << c;
        }
        cout << '\n';
    }
}