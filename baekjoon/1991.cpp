#include<iostream>
#include<unordered_map>
using namespace std;

struct Node {
	char c;
	Node *left, *right;
	Node(char c, Node *left=nullptr, Node* right=nullptr) : c(c), left(left), right(right) {}
};

int N;
unordered_map<char, Node*> Tree;

void preOrder(Node* n){
	if (n->c != '.') {
		cout << n->c;
		preOrder(n->left);
		preOrder(n->right);
	}
}

void inOrder(Node* n){
	if (n->c != '.') {
		inOrder(n->left);
		cout << n->c;
		inOrder(n->right);
	}

}

void postOrder(Node* n){
	if (n->c != '.') {
		postOrder(n->left);
		postOrder(n->right);
		cout << n->c;
	}
}

int main() {
	cin >> N;
	char a, b, c;

	Tree['A'] = new Node('A');
	for (int i = 0; i < N; i++) {
		cin >> a >> b >> c;
		Node* n = Tree[a];
		Tree[b] = n->left = new Node(b);
		Tree[c] = n->right = new Node(c);
	}
	
	preOrder(Tree['A']);
	cout << '\n';
	inOrder(Tree['A']);
	cout << '\n';
	postOrder(Tree['A']);
}