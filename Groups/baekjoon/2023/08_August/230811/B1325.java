import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class B1325 {
    static ArrayList<Integer>[] graph;
    static int[] hackedCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B); // B가 A를 신뢰한다면 A를 해킹하면 B도 해킹할 수 있다.
        }

        hackedCount = new int[N + 1];

        for (int i = 1; i <= N; i++) DFS(i, N+1);
        

        int max = 0;
        for (int i = 1; i <= N; i++) 
            max = Math.max(max, hackedCount[i]);
        

        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (hackedCount[i] == max) {
                result.append(i).append(" ");
            }
        }

        System.out.println(result);
    }

    static void DFS(int startNode, int length) {
        boolean[] visited = new boolean[length];
        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);
        visited[startNode] = true;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            for (int next : graph[node]) {
                if (!visited[next]) {
                    stack.push(next);
                    visited[next] = true;
                    hackedCount[next]++;
                }
            }
        }
    }
}