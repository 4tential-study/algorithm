import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class B2293 {
    static ArrayList<Integer> coins = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int coinTypes = Integer.parseInt(st.nextToken());   // 동전 종류 개수
        int target = Integer.parseInt(st.nextToken());      // 목표치

        for(int i = 0; i < coinTypes; i++)  coins.add(Integer.parseInt(in.readLine()));
        Collections.sort(coins);
        int[] DP = new int[target+1];
        DP[0] = 1;  // 0원을 표현? 아무것도 주지 않은 경우.

        /*
         * 사용하는 동전을 종류를 하나씩 추가할 때마다 i원을 표현할 수 있는 경우의 수가 달라질 것이다.
         * 추가할 때마다 달라지는 이 경우의 수를 주목해서 볼 것.
         */
        for(int coin : coins){
            for(int i = coin; i <= target; i++){
                DP[i] += DP[i-coin];
            }
        }
        System.out.println(DP[target]);
    }
}