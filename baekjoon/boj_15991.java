import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_15991 {
    static long[] duplicatedDp;
    static long[] duplicate;
    static final long mod = 1_000_000_009;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        duplicatedDp = new long[10001];
        duplicate = new long[10001];

        duplicatedDp[0] = 1;
        duplicatedDp[1] = 1;
        duplicatedDp[2] = 2;
        duplicatedDp[3] = 4;

        duplicate[0] = 0;
        duplicate[1] = 0;
        duplicate[2] = 1;
        duplicate[3] = 2;


        for(int i=4 ; i < 10000 ; i++){
            duplicatedDp[i] = (duplicatedDp[i-1] + duplicatedDp[i-2] + duplicatedDp[i-3]) % mod;
            duplicate[i] = (duplicate[i-1] + duplicate[i-2] + duplicate[i-3]) % mod;
        }

        for(int i=0 ; i < t ; i++){
            int n = Integer.parseInt(br.readLine());
            System.out.println(duplicatedDp[n] - duplicate[n]);
        }
    }
}
