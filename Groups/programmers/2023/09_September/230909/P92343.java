import java.util.ArrayList;
import java.util.List;

class Solution {
    public static int solution(int[] info, int[][] edges) {
        List<Integer>[] list = new ArrayList[info.length];
        for(int i = 0; i < info.length; i++)    list[i] = new ArrayList<>();

        for(int idx = 0; idx < edges.length; idx++){
            int from = edges[idx][0];
            int to = edges[idx][1];
            list[from].add(to);
        }

        int answer = 0;
        return answer;
    }

    public static void main(String[] args) {
        int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};

        int[][] edge = {{0,1}, {1,2}, {1,4}, {0,8}, 
                        {8,7}, {9,10}, {9,11}, {4,3}, 
                        {6,5}, {4,6}, {8,9}};
        System.out.println(solution(info, edge));
    }
}