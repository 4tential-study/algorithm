import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B2624 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(in.readLine());

        int coinTypes = Integer.parseInt(in.readLine());
        int[] coinValues = new int[coinTypes];
        int[] coinCounts = new int[coinTypes]; 
        for (int i = 0; i < coinTypes; i++) {
            String[] str = in.readLine().split(" ");
            coinValues[i] = Integer.parseInt(str[0]);
            coinCounts[i] = Integer.parseInt(str[1]);
        }

        int[] DP = new int[target + 1];
        DP[0] = 1;

        for(int coin = 0; coin < coinTypes; coin++){
            for(int idx = target; idx >= 1; idx--){
                for(int count = 1; count <= coinCounts[coin]; count++){
                    int search = idx - (coinValues[coin] * count);
                    if(search >= 0) DP[idx] += DP[search];
                }
            }
        }

        System.out.println(DP[target]);
    }
}