import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int N, R, L, Q;
    static ArrayList<Integer>[] adjList;
    static int[] value;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] str = br.readLine().split(" " );

        N = Integer.parseInt(str[0]);
        R = Integer.parseInt(str[1]);

        value = new int[N + 1];

        adjList = new ArrayList[N + 1];
        for(int i = 1; i <= N; ++i){
            adjList[i] = new ArrayList<>();
            value[i] = -1;
        }

        // 노드 연결 정보 입력 받기
        for(int i = 0; i < N - 1; ++i){
            str = br.readLine().split(" ");
            int u = Integer.parseInt(str[0]);
            int v = Integer.parseInt(str[1]);

            adjList[u].add(v);
            adjList[v].add(u);
        }

        // 리프 노드의 value 값 받기
        L = Integer.parseInt(br.readLine());
        for(int i = 0; i < L; ++i){
            str = br.readLine().split(" ");
            int k = Integer.parseInt(str[0]);
            int t = Integer.parseInt(str[1]);

            value[k] = t;
        }

        // 트리 만들기
        makeTree(R, 0, true);

        // 구해야 하는 정보 sb에 추가
        Q = Integer.parseInt(br.readLine());
        for(int i = 0; i < Q; ++i){
            int q = Integer.parseInt(br.readLine());
            sb.append(value[q] + "\n");
        }

        System.out.println(sb.toString());
    }

    /*
        각 노드마다 모든 간선을 체크하면서 Max or Min값을 구한다.
        완완탐이구나..

        각 노드에서 가지고 있는 자식 노드 수 n, 트리의 최대 깊이 d라고 한다면
        시간복잡도 : O(n^d) 아마도?
     */
    private static int makeTree(int now, int parent, boolean isMax) {
        // 리프 노드는 원래 값 리턴
        if(value[now] != -1) return value[now];

        int nowValue = isMax ? 0 : Integer.MAX_VALUE;

        for(int i = 0; i < adjList[now].size(); ++i){
            int next = adjList[now].get(i);
            if(next != parent){
                nowValue = isMax ? Math.max(nowValue, makeTree(next, now, !isMax))
                        : Math.min(nowValue, makeTree(next, now, !isMax));
            }
        }

        return value[now] = nowValue;
    }
}
