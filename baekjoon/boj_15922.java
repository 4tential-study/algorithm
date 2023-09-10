import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_15922 {
    static BufferedReader br;
    static int n;
    static int s ;
    static int e ;
    static int dist;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i=0 ; i < n ; i ++) {
            String[] splits = br.readLine().split(" ");
            int a = Integer.parseInt(splits[0]);
            int b = Integer.parseInt(splits[1]);
            //처음 길이 설정
            if (i == 0) {
                s = a;
                e = b;
                dist += e - s;
            } else {
                //선분이 겹치는 경
                if (a <= e) {
                    if (a <= s) {
                        dist += a - s;
                        s=a;
                    }
                    if (b >= e) {
                        dist += b - e;
                        e=b;
                    }
                }
                //선분이 안 겹치는 경우
                else if (a > e) {
                    dist += b - a;
                    e=b;
                    s=a;
                }
            }

        }
        System.out.println(dist);
    }
}
