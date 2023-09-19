import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class boj_20437_slidingwindow {
    static List<Integer>[] list = new ArrayList[26];
    static int k;
    static int max;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 26; i++){
            list[i] = new ArrayList<>();
        }
        int t = Integer.parseInt(br.readLine());
        for(int tc = 0 ; tc < t ; tc++) {
            for (int i = 0; i < 26; i++){
                list[i].clear();
            }

            String str = br.readLine();

            for (int i = 0; i < str.length(); i++){
                int alphabet = str.charAt(i) - 'a';
                list[alphabet].add(i);
            }

            k = Integer.parseInt(br.readLine());

            boolean flag = true;
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;


            for (int i = 0; i < 26; i++){
                if (list[i].size() >= k){
                    flag = false;
                    sol(i);
                }
            }

            if (flag) {
                System.out.println(-1);
                continue;
            } else {
                System.out.println(min + " " + max);
            }
        }
    }

    public static void sol(int idx){ //  개수, 끝인덱스, 해당 알파벳
        List<Integer> arr  = list[idx];
        int left = 0;
        int right = k-1;

        while(right < arr.size()){
            min = Math.min(min, arr.get(right) - arr.get(left) + 1);
            max = Math.max(max, arr.get(right) - arr.get(left) + 1);
            left++;
            right++;

        }
    }
}
