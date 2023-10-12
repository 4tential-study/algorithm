import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14938 {
    static BufferedReader in;
    static StringTokenizer st;
    static int[][] map;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        String[] s = in.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]); // 범위
        int r = Integer.parseInt(s[2]); //길의 개수
        dp = new int[n+1][n+1];
        map = new int[n+1][n+1];
        in.readLine().split(" ");

        for(int i=0 ; i < r ; i++){
            String[] s1 = in.readLine().split(" ");
            int a = Integer.parseInt(s1[0]);
            int b = Integer.parseInt(s1[1]);
            int v = Integer.parseInt(s1[2]);
            dp[a][b]=v;
            dp[b][a]=v;
        }
        for(int i=0 ; i < n ; i++){
            //dp초기화 INF
        }
        //-----------------------
        for(int k=0 ; k < n ; k++){
            for(int i=0 ; i < n ; i++){
                for(int j=0 ; j < n ; j++){
                    dp[i][j] = Math.min(dp[i][j] , dp[i][k]+dp[k][j])
                }
            }
        }


    }
}
