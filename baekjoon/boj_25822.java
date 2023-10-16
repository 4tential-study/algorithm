
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class boj_25822 {

    static StringTokenizer st;
    static BufferedReader in;
    static int N;
    static int[] dp;
    static int[] problems;
    static int strick;


    public static void main(String[] args) throws IOException {
        in= new BufferedReader(new InputStreamReader(System.in));

        Double C = Double.parseDouble(in.readLine());
        N = Integer.parseInt(in.readLine());
        st = new StringTokenizer(in.readLine());
        problems = new int[N+1];
        dp = new int[N+1];
        int max = 0;


        strick =(int) (C/0.99);
        strick = strick >= 2 ? 2 : strick;

        for(int i=1 ; i <= N ; i++) {
            problems[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, problems[i]);
        }

        for(int i=1 ; i <= N ; i++) {
            dp[i] = dp[i-1] + (problems[i]==0?1:0);
//			 dp[x]은 첫째날부터 x날까지 스트릭을 유지하기 위해 필요한 스트릭 프리즈의 개수
        }
        int st = 1, end = 1;
        int maxStrick = 0;
        while (end <= N) {
            int need = dp[end] - dp[st-1];	// [s, e]구간이 스트릭 유지를 하기 위해 필요한 스트릭 프리즈의 개수
            if (need <= strick) {
                maxStrick = Math.max(maxStrick, end-st+1);
                end++;
            } else {
                st++;
            }
        }


        System.out.println(maxStrick + "\n" +max);
    }
}