package 백준_1991_트리순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 전위순회 : V->L->R
// 중위순회 : L->V->R
// 후위순회 : L->R->V
// 해당 코드는 DOIT! 알고리즘 코딩테스트 서적을 참고하여 작성되었음을 알려드립니다.

public class 백준_1991_트리순회 {
	public static int[][] tree;
	public static int node;
	public static char left, right;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		tree = new int[n][2];
		for (int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			node = line[0].charAt(0) - 'A'; // index로 변환하기 위해 A문자 빼기 ->이렇게 하면 A:0, B:1, C:2...이렇게 변환된다.
			left = line[1].charAt(0); // 평소라면 그냥 String 형태로 했겠지만, 메모리 효율을 고려해 char에 저장하자!
			right = line[2].charAt(0);
			if (left == '.') { // Left 자식 노드가 존재하지 않으면
				tree[node][0] = -1; // 해당하는 자리에 -1을 넣어줌
			} else {
				tree[node][0] = left - 'A'; // tree는 int 배열이므로 A를 빼서 숫자로 저장해줌
			}
			if (right == '.') {
				tree[node][1] = -1;
			} else {
				tree[node][1] = right - 'A';
			}
		}

		preOrder(0); // 전위순회 실행
		System.out.println();
		inOrder(0); // 중위순회 실행
		System.out.println();
		postOrder(0); // 후위순회 실행
		System.out.println();

	}

	public static void preOrder(int now) {
		if (now == -1)
			return;
		System.out.print((char) (now + 'A')); // 1. 현재 노드. A를 더해 char로 변환해줌으로서 알파벳 형태로 출력해준다.
		preOrder(tree[now][0]); // 2. 왼쪽 탐색하기
		preOrder(tree[now][1]); // 3. 오른쪽 탐색하기
	}

	public static void inOrder(int now) {
		if (now == -1)
			return;
		inOrder(tree[now][0]); // 1. 왼쪽 탐색하기
		System.out.print((char) (now + 'A')); // 2. 현재 노드
		inOrder(tree[now][1]); // 3. 오른쪽 탐색하기
	}

	public static void postOrder(int now) {
		if (now == -1)
			return;
		postOrder(tree[now][0]); // 1. 왼쪽 탐색하기
		postOrder(tree[now][1]); // 2. 오른쪽 탐색하기
		System.out.print((char) (now + 'A'));// 3. 현재 노드
	}

}
