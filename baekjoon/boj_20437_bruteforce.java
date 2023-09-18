import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class boj_20437_bruteforce {
    static BufferedReader br;
    static List<Integer>[] list = new ArrayList[26];
    static int t;
    static int n;
    static String str="";
    static int max;
    static int min;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < 26; i++){
            list[i] = new ArrayList<>();
        }

        for(int tc = 0 ; tc < t ; tc++) {
            max = -1;
            min = 10001;
            for (int i = 0; i < 26; i++){
                list[i].clear();
            }

            str = br.readLine();
            if(str==null) break;
            n = str.length();

            for (int i = 0; i < str.length(); i++){
                int alphabet = str.charAt(i) - 'a';
                list[alphabet].add(i);
            }

            int k = Integer.parseInt(br.readLine());

            boolean flag = true;
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;


            for (int i = 0; i < 26; i++){
                if (list[i].size() >= k){
                    flag = false;
                    sol(k, i);
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

    public static void sol(int k, int idx){ //  개수, 끝인덱스, 해당 알파벳
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


