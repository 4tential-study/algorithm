/*
 * 상태 혹은 해당 노드를 탐색하였을 때의 최대 양의 수를 
 * 메모이제이션 해야한다고 생각하였지만,
 * 어떤 식으로 해야할 지가 관건이었다.
 * 참고 사이트 : https://blog.encrypted.gg/1029
 * 위의 사이트를 참고하여,
 * 상태를 비트 마스킹 + 메모이제이션하는 방식을 선택하였다.
 */
import java.util.ArrayList;
import java.util.List;

class Solution {
    static int vertex_count;
    static boolean[] states; // 여태까지 진입한 노드들의 집합들을 모아놓음.
    static List<Integer>[] list;
    static int answer = 1;
    // info : 각 노드에 있는 양 또는 늑대에 대한 정보가 담긴 배열
    // edges: 2진 트리의 각 노드들의 연결 관계를 담은 2차원 배열
    public static int solution(int[] info, int[][] edges) {
        vertex_count = info.length;
        states = new boolean[(1 << vertex_count) + 2];

        list = new ArrayList[vertex_count];
        for(int i = 0; i < vertex_count; i++)    list[i] = new ArrayList<>();

        // 인접 리스트 생성.
        for(int idx = 0; idx < edges.length; idx++){
            int from = edges[idx][0];
            int to = edges[idx][1];
            list[from].add(to);
        }

        solve(info, 1);        
        return answer;
    }

    static void solve(int[] info, int state) {
        if(states[state]) return; // 이미 방문하였던 상태 집합이므로 return.
        states[state] = true;

        // 탐색한 노드의 개수 및 탐색한 노드에서 늑대의 개수
        int visited_count = 0, wolf = 0;
        for(int idx = 0; idx < vertex_count; idx++){
            if((state & (1 << idx)) != 0){ // state에서 idx번째 노드를 방문하였다고 적혀있다면,
                visited_count++;
                wolf += info[idx];
            }
        }
        // 늑대가 양의 수보다 크거나 같다면 이는 방문할 수 없는는 상태이므로 return
        if(2*wolf >= visited_count) return;
        answer = Math.max(answer, visited_count - wolf);

        for(int idx = 0; idx < vertex_count; idx++){
            if((state & (1 << idx)) == 0)   continue; // 아직 해당 노드에 가지 않았으므로 continue

            for(int i = 0; i < list[idx].size(); i++){
                solve(info,  (state | (1 << list[idx].get(i)))); // idx 번째의 노드의 자식 노드를 상태 집합에 포함하여 탐색함.
            }
        }
    }

    // public static void main(String[] args) {
    //     int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};

    //     int[][] edge = {{0,1}, {1,2}, {1,4}, {0,8}, 
    //                     {8,7}, {9,10}, {9,11}, {4,3}, 
    //                     {6,5}, {4,6}, {8,9}};
    //     System.out.println(solution(info, edge));
    // }
}