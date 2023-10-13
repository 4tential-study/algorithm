// 다익스트라
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class B14938_2 {
	private static final int INF = 999;	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");
		
		int vertex_count = Integer.parseInt(input[0]);	// 지역 개수
		int range = Integer.parseInt(input[1]);			// 탐색 범위 (최대 15)
		int edge_count = Integer.parseInt(input[2]);	// 길의 개수
		
		int[] items = new int[vertex_count+1];
		input = in.readLine().split(" ");
		
		// 각 지역의 아이템 수 저장
		for(int i = 1; i <= vertex_count; i++)	items[i] = Integer.parseInt(input[i-1]);
		
		// 인접 행렬 생성
		int[][] road = new int[vertex_count+1][vertex_count+1];
		
		// 인접한다면 weight값, 그렇지 않다면 매우 큰 값(INF)으로 초기화
		for(int i = 0; i <= vertex_count; i++)	{
			Arrays.fill(road[i], INF);
			road[i][i] = 0;
		}
		
		// 인접 정보 입력 받음
		for(int i = 0; i < edge_count; i++) {
			input = in.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int weight = Integer.parseInt(input[2]);
			road[a][b] = road[b][a] = weight;
		}
		
		int answer = Integer.MIN_VALUE;
		for(int start = 1; start <= vertex_count; start++) {
			int[] dist = Dijkstra(road, start, vertex_count);
			int value = 0;
			for(int end = 1; end <= vertex_count; end++) {
				if(dist[end] <= range) value += items[end];
			}
			answer = Math.max(answer, value);
		}
		
		System.out.println(answer);
	}
	
	static int[] Dijkstra(int[][] road, int start, int count) {
		boolean[] visited = new boolean[count+1];
		int[] dist = new int[count+1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		Queue<Node> queue = new PriorityQueue<Node>();
		
		queue.offer(new Node(start));
		
		while(!queue.isEmpty()) {
			int current_index = queue.poll().index;
			if(visited[current_index])	continue;
			visited[current_index] = true;
			
			for(int i = 1; i <= count; i++) {
				if(road[current_index][i] >= INF) continue;
				else if(dist[i] > dist[current_index] + road[current_index][i]) {
					dist[i] = dist[current_index] + road[current_index][i];
					
					queue.offer(new Node(i, dist[i]));
				}
			}
		}
		
		return dist;
	}
}
class Node implements Comparable<Node> {
	int index, cost;
	
	public Node() {}
	
	public Node(int index) {
		this.index = index;
		cost = 0;
	}
	
	public Node(int index, int cost) {
		this.index = index;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
	
}