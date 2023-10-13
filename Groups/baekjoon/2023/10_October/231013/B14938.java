// 플로이드-워셜
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B14938 {
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
		Floyd(road, vertex_count);
		for(int start = 1; start <= vertex_count; start++) {
			int value = 0;
			for(int end = 1; end <= vertex_count; end++) {
				if(road[start][end] <= range || start == end) value += items[end];
			}
			answer = Math.max(answer, value);
		}
		
		System.out.println(answer);
	}
		
	static void Floyd(int[][] road, int count) {
		for(int 경유지 = 1; 경유지 <= count; 경유지++) {
			for(int 출발지 = 1; 출발지 <= count; 출발지++) {
				if(경유지 == 출발지) continue;
				for(int 도착지 = 1; 도착지 <= count; 도착지++) {
					if(도착지 == 경유지 || 도착지 == 출발지) continue;
					road[출발지][도착지] = Math.min(road[출발지][도착지], road[출발지][경유지] + road[경유지][도착지]);
				}
			}
		}
	}
}
