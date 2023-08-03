import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2293 {
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //3동전개수
        int k = Integer.parseInt(st.nextToken());//10합
        int[] coins = new int[101];
        int[] dp = new int[10001];
        Integer coin;
        for(int i=1 ; i <= n ; i++){
            coin = Integer.parseInt(br.readLine());
            coins[i] = coin;
        }
        
        dp[0] = 1;
      
        for(int i=1 ; i <= n ; i++){
        	for(int j=coins[i] ; j <= k ; j++){ 
                	dp[j] += dp[j - coins[i]]; 
            }
        }
        System.out.println(dp[k]);
        }

        
    }

