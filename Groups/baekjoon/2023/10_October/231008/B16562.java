import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16562 {
	private static int[] needs; // 각 학생이 원한느 친구비 배열
	private static int[] parents; // union-find를 활용할 배열
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 학생 수
		int M = Integer.parseInt(st.nextToken()); // 친구관계 수
		int money = Integer.parseInt(st.nextToken()); // 준석이가 가지고 있는 돈
		
		needs = new int[N+1];
		parents = new int[N+1];
		for(int i = 0; i <= N; i++)	parents[i] = i; // 자신의 부모로 자기 자신을 가리킬 수 있도록 초기화.
		
		// 각 학생이 원하는 친구비 입력
		st = new StringTokenizer(in.readLine());
		for(int i = 1; i <= N; i++) 
			needs[i] = Integer.parseInt(st.nextToken());
		
		// 친구관계 입력
		for(int ret = 0; ret < M; ret++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		
		int answer = 0;
		for(int i = 1; i <= N; i++) {
			int parent = find(parents[i]);
				answer += needs[parent];
				needs[parent] = 0; // 이미 계산한 그룹은 0으로 초기화하여 중복 계산을 막음.

			if(answer > money) {
				System.out.println("Oh no");
				return;
			}
		}
		
		System.out.println(answer);
	}
	
	static int find(int a) {
		if(parents[a] == a)	return a;
		else return find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int parent_a = find(a);
		int parent_b = find(b);
		
		if(parent_a == parent_b) return false;
		
		// 둘 중 더 적은 친구비를 요구하는 학생이 parent가 되게 설정.
		// 요구하는 학생비가 같다면 번호가 작은 학생이 parent.
		if(needs[parent_a] < needs[parent_b] || (needs[parent_a] == needs[parent_b] && parent_a < parent_b)) 
			parents[parent_b] = parent_a;
		else parents[parent_a] = parent_b;
		return true;
	}
}
