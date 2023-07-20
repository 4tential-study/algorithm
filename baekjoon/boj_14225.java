

import java.util.*;
import java.io.*;

public class boj_14225 {
    static ArrayList<Integer> list = new ArrayList<>();
    static BufferedReader br ;
    static boolean[] visited;
    static int N;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        // [수열의 총합 구하기]
        int sum = 0;
        for( int i = 0 ; i < N ; i++) {
            int element = Integer.parseInt(st.nextToken());
            list.add(element);
            sum += element;
        }

        // [최악의 경우] "모든 수열의 합 + 1" 일때가 답이 되는 경우를 위해 [sum+1]번째 배열까지 생성.
        int[] result = new int[sum + 2];

        // [시간복잡도 개선을 위해 정렬 실행]
        //  684ms -> 648ms
        Collections.sort(list);

        visited = new boolean[list.size()];

        combination(0, 0, result);

        for(int i=1 ; i < result.length ; i++) {
            if (result[i] == 0) {
                System.out.println(i);
                break;
            }
        }
    }

    public static void combination(int idx, int sum, int[] result) {
        if(idx >= N) {
            return;
        }

        for(int i = idx ; i < list.size() ; i++) {
            Integer number = list.get(i);
            if(visited[i]) continue;

            visited[i] = true;
            result[sum+number] = 1;
            combination( i+1, sum+number , result );
            visited[i] = false;
        }
    }

}
