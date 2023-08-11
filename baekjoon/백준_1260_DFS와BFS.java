package 백준_1260_DFS와BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

public class 백준_1260_DFS와BFS {
	//방문한 노드 확인을 위한 visited
	static boolean[] visited;
	//가변적인 크기를 위해 ArraList를 생성해준다.
	static ArrayList<Integer>[] li;
	//그냥 메모리 할당 효율을 위해 static으로 빼줌
	static int start;
	static int end;

	public static void main(String[] args) throws Exception {
		//입력받는 구간
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]); //노드 개수
		int M = Integer.parseInt(line[1]); //간선의 개수
		int V = Integer.parseInt(line[2]); //시작 노드의 값!
		//ArrayList의 크기를 N+1로 할당해준다.(i=0는 어차피 방문 x)
		li = new ArrayList[N + 1];
		//ArrayList 안에 ArrayList를 요소 넣어주어 파이썬의 2차원 배열 형태처럼 만들기! 손이 더 가지만 외우자.
		for (int i=1;i<=N;i++) {
			li[i]=new ArrayList<Integer>();
		}
		// li에 그래프 데이터 저장. start번째에는 end를, end번째에는 start를 넣어 정석적인 그래프 형태로 만든다.
		for (int i = 0; i < M; i++) {
			String[] line2 = br.readLine().split(" ");
			start = Integer.parseInt(line2[0]);
			end = Integer.parseInt(line2[1]);
			li[start].add(end);
			li[end].add(start);
		}
		// 탐색 이전에 정렬하기
		for (int i = 1; i <= N; i++) {
			Collections.sort(li[i]);
		}
		// visited 초기화 후 dfs 수행
		visited = new boolean[N + 1];
		dfs(V);
		System.out.println();
		// visited 초기화 후 bfs 수행
		visited = new boolean[N + 1];
		bfs(V);
		System.out.println();
	}

	//dfs 메소드. 방문한 노드를 now로 출력하고, now에 방문하였으므로 true로 바꿔준다. 이후 Arraylist의 now번째 Arraylist 안의 노드번호에 대해 방문했으면 패스, 방문하지 않았으면 그에 대해 dfs 실행
	public static void dfs(int now) {
		System.out.print(now + " ");
		visited[now] = true;
		for (int i : li[now]) {
			if (!visited[i]) {
				dfs(i);
			}
		}
	}

	//bfs 메소드. 어레이덱을 사용한다. 시작점인 now를 넣어주고, 방문표시한다. 이후 queue가 끝날 때까지, Arraylist의 now번째 Arraylist 안의 노드번호에 대해 방문했으면 패스, 방문하지 않았으면 queue에 넣어 bfs 실행.
	public static void bfs(int now) {
		Deque<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(now);
		visited[now] = true;

		while (!queue.isEmpty()) {
			int new_now = queue.poll();
			System.out.print(new_now + " ");
			for (int i : li[new_now]) {
				if (!visited[i]) {
					visited[i] = true;
					queue.add(i);
				}
			}
		}
	}
}
