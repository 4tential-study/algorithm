import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_2156 {
    static BufferedReader in;
    static int n;
    static int[] array;
    static int[][] dp;
    public static void main(String[] args) throws IOException{
        in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        array = new int[n+1];
        dp = new int[n+1][3];
        for(int i=1 ; i <= n ; i++) {
            int l = Integer.parseInt(in.readLine());
            array[i] = l;
        }

        if(n==1) {
            System.out.println(array[1]);
            return;
        }

        dp[1][1] = array[1];
        dp[2][1] = array[2];
        dp[2][0] = array[1];
        dp[2][2] = array[2] + array[1];
        if(n == 2) {
            System.out.println(Math.max(Math.max(dp[2][1], dp[2][2]),dp[2][0]));
            return;
        }
        dp[3][1] = array[1] + array[3];
        dp[3][2] = array[3] + array[2];
        dp[3][0] = array[1] + array[2];
        if(n == 3) {
            System.out.println(Math.max(Math.max(dp[3][1], dp[3][2]), dp[3][0]));
            return;
        }

        for(int i = 4 ; i <= n ; i++) {
            dp[i][1] = Math.max(Math.max(dp[i-2][1] , dp[i-2][2]),dp[i-2][0]) + array[i];
            dp[i][2] = Math.max(Math.max(dp[i-3][1] , dp[i-3][2]),dp[i-3][0]) + array[i] + array[i-1];
            dp[i][0] = Math.max(Math.max(dp[i-1][1], dp[i-1][2]), dp[i-1][0]);
        }
//		
//		for(int i=1 ; i < dp.length ; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//
//		}
        System.out.println( Math.max(Math.max(dp[n][1] , dp[n][2]), dp[n][0]));
//		[0, 6, 10, 13, 9, 8, 1, 1, 2, 4]
    }

}