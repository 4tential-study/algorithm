package 백준_14621_나만안되는연애;

//크루스컬 알고리즘을 다 잊어서 다시 자료 찾아서 보면서 했습니다!

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


class Edge{ // 클래스 형태로 각 값을 저장할 것! 이 방식을 잘 익히자.
	int start;
	int end;
	int weight;
	
	Edge(int start,int end, int weight){
		this.start=start;
		this.end=end;
		this.weight=weight;
	}
}

public class 백준_14621_나만안되는연애 {
	public static int u, v, d;
	static ArrayList<Edge> edgeList; // Edge 클래스의 형태로 값을 저장하기 위한 ArrayList 선언
	static int[] parent; //public으로 하는 순간 parent의 값이 바뀌어버린다!
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]); // 학교의 개수(=정점의 개수) N
		int M = Integer.parseInt(line[1]); // 도로의 개수(=간선의 개수) M
		String[] univ= new String[N+1];
		String[] line3 = br.readLine().split(" ");
		for (int i=1;i<=N;i++) {
			univ[i]=line3[i-1];
		}
		edgeList = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			String[] line2 = br.readLine().split(" ");
			u = Integer.parseInt(line2[0]);
			v = Integer.parseInt(line2[1]);
			d = Integer.parseInt(line2[2]);
			edgeList.add(new Edge(u,v,d)); // edgeList에 값을 넣어준다.
		}
		parent = new int[N+1];
		for (int i=1;i<=N;i++) {
			parent[i]=i;
		}
		// 사심 경로를 기준으로 오름차순 정렬
		Collections.sort(edgeList,(e1,e2)->e1.weight-e2.weight);
		
		int cnt=0, ans=0;
		
		// 크루스칼 알고리즘 수행
		for (int i=0;i<edgeList.size();i++) {
			Edge edge = edgeList.get(i);
			
			if (find(edge.start) !=find(edge.end)) { //equals를 못잡아서 한시간동안 헤멤..string은 무조건 equals 비교!!
				if (!univ[edge.start].equals(univ[edge.end])) {
					cnt++;
					ans+=edge.weight;
					
					union(edge.start,edge.end);
				}
			}
		}
		
		//N-1개의 경로가 없으면 최소 신장 트리 만들 수 없음.
		if (cnt==N-1) System.out.println(ans);
		else System.out.println(-1);
	}
	public static int find(int x) {
		if (x==parent[x]) {
			return x;
		}
		
		return parent[x] = find(parent[x]);
	}
	
	public static void union(int x,int y) {
		x = find(x);
		y = find(y);
		
		if (x!=y) {
			parent[y]=x;
		}
	}
}
