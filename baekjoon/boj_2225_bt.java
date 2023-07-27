import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2225_bt {
    static int K;
    static int N;
    static int count = 0;
    static int listSum = 0;
//    static ArrayList<int[]> totalList= new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        permutation(0, 0);
        System.out.println(count % 1000000000);

    }

    public static void permutation(int level, int listSum){
        if (level == K){
            if (listSum == N){
                count+=1;
            }
            return;
        }

        for (int i=0 ; i<=N; i++){
            listSum+=i;
            permutation(level+1, listSum % 1000000000);
            listSum-=i;
        }
    }
}
