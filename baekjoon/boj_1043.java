import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1043 {
    static BufferedReader in;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        String[] s = in.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        String[] s2 = in.readLine().split(" ");
        int  tcnt = Integer.parseInt(s2[0]);
        int[] tarray = new int[tcnt];

        for(int i=1; i <= tcnt ;i++){
            tarray[i-1]=Integer.parseInt(s2[i]);
        }

        for(int i=0 ; i < m  ; i++){
            String[] s1 = in.readLine().split(" ");
            int cnt = Integer.parseInt(s1[0]);
            for(int j=1 ; j <= cnt ; j++){

            }

        }
    }
}
