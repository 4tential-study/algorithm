import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class B1260{
    static Set<Integer> visited = new LinkedHashSet<>();
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int node_count = Integer.parseInt(st.nextToken());
        int edge_count = Integer.parseInt(st.nextToken());
        int start_node = Integer.parseInt(st.nextToken());

        // 인접 행렬 사용
        boolean[][] matrix = new boolean[node_count+1][node_count+1];
        for(int i = 0; i < edge_count; i++){
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            matrix[start][end] = matrix[end][start] = true;
        }

        System.out.println(DFS(matrix, node_count+1, start_node));
        System.out.println(BFS(matrix, node_count+1, start_node));
    }

    static String BFS(boolean[][] matrix, int size, int start_node){
        visited.clear();

        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start_node);
        while(!queue.isEmpty()){
            int current_size = queue.size();
            for(int i = 0; i < current_size; i++){
                int Node = queue.poll();
                visited.add(Node);
                sb.append(Node).append(" ");
                for(int j = 0; j < size; j++){
                    if(!matrix[Node][j] || visited.contains(j))    continue;
                    visited.add(j); queue.offer(j);
                }
            }
        }
        return sb.toString();
    }

    static String DFS(boolean[][] matrix, int size, int start_node){
        visited.clear();

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        stack.push(start_node);
        while(!stack.isEmpty()){
            int Node = stack.pop();
            // 이미 탐색한 노드 번호가 pop 됐을 경우 skip
            if(visited.contains(Node)) continue; 
            visited.add(Node);
            // 작은 수부터 탐색할 수 있게 stack에 거꾸로 push
            for(int j = size-1; j >= 0; j--){ 
                if(!matrix[Node][j] || visited.contains(j)) continue;
                stack.push(j);
            }
            sb.append(Node).append(" ");
        }
        return sb.toString();
    }
}