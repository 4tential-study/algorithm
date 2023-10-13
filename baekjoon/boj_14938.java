import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_14938 {
    static BufferedReader in;
    static StringTokenizer st;
    static int[][] dp;
    static int[] items;
    static final int INF = 99999999;
    
    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        String[] s = in.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]); 
        int r = Integer.parseInt(s[2]); 
        
        dp = new int[n+1][n+1];
        items = new int[n+1];
        st = new StringTokenizer(in.readLine());
        
        int[] ans = new int[n+1];
        inputReader(n,r);
       
        floydWarshal(n);
        
        for(int i=1 ; i <= n ; i++){     
            for(int j=1 ; j <= n ; j++){
            	//각 출발점에서, 거리가 m이하인 범위의 아이템 개수 합 구하기
                if(dp[i][j] <= m ){
                	ans[i] += items[j];
                }                          
            }
        }
        System.out.println(Arrays.stream(ans).max().getAsInt());
    }
    
    public static void floydWarshal(int n) {
    	 for(int k=1 ; k <= n ; k++){
             for(int i=1 ; i <= n ; i++){
                 for(int j=1 ; j <= n ; j++){
                     dp[i][j] = Math.min(dp[i][j] , dp[i][k]+dp[k][j]);     
                 }
             }
         }
    }
    
    public static void inputReader(int n, int r) throws IOException{
    	for(int i=1 ; i <= n ; i++) {
        	items[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i=0 ; i <= n ; i++){
            for(int j=0; j <= n ; j++) {
            	if(i==j) dp[i][j] = 0;
            	else dp[i][j] = INF;
            }
        }
        
        for(int i=0 ; i < r ; i++){
            String[] s1 = in.readLine().split(" ");
            int a = Integer.parseInt(s1[0]);
            int b = Integer.parseInt(s1[1]);
            int v = Integer.parseInt(s1[2]);
            dp[a][b] = v;
            dp[b][a] = v; 
        }
       
    }
    
}
