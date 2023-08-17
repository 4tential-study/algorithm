import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;

public class B1967 {
  static List<Map<Integer,Integer>>[] list;
  static List<Integer> results;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int node_count = Integer.parseInt(in.readLine()); // 1 ~ node_count 만큼 노드가 존재함.
        if(node_count == 1) {
          System.out.println(0);  return;
        }

        results = new ArrayList<>(node_count+1); // 결과를 담을 list

        // 인접 리스트 생성.
        list = new ArrayList[node_count+1];
        for(int i = 1; i < node_count+1; i++)  list[i] = new ArrayList<>();


        // 간선 정보 입력받음.
        for(int i = 0; i < node_count-1; i++){
            st = new StringTokenizer(in.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            Map<Integer, Integer> map = new HashMap<>();
            map.put(child, weight);
            list[parent].add(map);
        }

        solve(1);
        results.sort(Collections.reverseOrder());
        System.out.println(results.get(0));
    }

    static int solve(int parent){
      List<Integer> distanceList = new ArrayList<>();
      int max_distance = 0, size = list[parent].size();
      for(int i = 0; i < size; i++){
        Set<Entry<Integer, Integer>> set = list[parent].get(i).entrySet();
        for(Map.Entry<Integer, Integer> entry : set){
          int distance = solve(entry.getKey()) + entry.getValue();
          max_distance = Math.max(max_distance, distance);
          distanceList.add(distance);
        }
      }
      if(distanceList.size() == 1) results.add(max_distance);
      else if(distanceList.size() >= 2){
        distanceList.sort(Collections.reverseOrder());
        results.add(distanceList.get(0) + distanceList.get(1));
      }
      return max_distance;
    }
}