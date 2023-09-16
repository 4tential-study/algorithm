import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class B20437 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());

        for(int testcase = 0; testcase < T; testcase++){
            char[] input = in.readLine().toCharArray();
            int target_count = Integer.parseInt(in.readLine());

            // 각 알파벳이 해당 문자열에 존재하는 index들을 저장함.
            List<Integer>[] list = new ArrayList[26];
            for(int i = 0; i < 26; i++) list[i] = new ArrayList<>();

            int length = input.length;
            for(int idx = 0; idx < length; idx++){
                int value = input[idx] - 'a';
                list[value].add(idx);
            }
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for(int list_num = 0; list_num < 26; list_num++){
                if(list[list_num].size() < target_count) continue;
                int list_size = list[list_num].size();

                // 해당 알파벳을 정확히 target_count 개만큼 포함하는 문자열의 길이를 구한다.
                for(int end = target_count-1; end < list_size; end++){
                    int start = end - (target_count - 1);
                    int value = (list[list_num].get(end) - list[list_num].get(start)) + 1;
                    min = (min > value)? value : min;
                    max = (max < value)? value : max;
                }
            }

            if(min == Integer.MAX_VALUE){
                sb.append("-1").append("\n");
            }
            else {
                sb.append(min).append(" ").append(max).append("\n");
            }
        }

        System.out.println(sb);
    }
}
