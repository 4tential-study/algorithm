import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class B1229 {
    private static final int INF = 9_999_999;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(in.readLine());

        List<Integer> hexList = new ArrayList<>();
        int i = 1;
        while(true){
            int value = i * (2*(i++) - 1);
            if(value > input) break;
            hexList.add(value);
        }
        Collections.sort(hexList);

        int[] DP = new int[input+1];
        Arrays.fill(DP, INF);
        DP[0] = 0;

        for(Integer a : hexList){
            for(int idx = a; idx <= input; idx++){
                DP[idx] = Math.min(DP[idx], DP[idx - a] + 1);
            }
        }
        System.out.println(DP[input]);
    }    
}
