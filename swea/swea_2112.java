import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_2112 {
    static BufferedReader in;
    static StringTokenizer st;
    static int D;
    static int W;
    static int K;

    static int[][] map;

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        for(int tc = 1 ; tc <= t ; tc++) {
            sb.append("#" + tc + " ");
            String[] s = in.readLine().split(" ");
            D = Integer.parseInt(s[0]);
            W = Integer.parseInt(s[1]);
            K = Integer.parseInt(s[2]);
            map = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            //pre : 약품 투입 필요 없는 경우 -> 0 return;
            if (isValid()) {
                sb.append(0);
                continue;
            }
            //1. 부분 집합 만들기 (2개 이상부터 D개 이하)
            subset();
            //2. 각 집합의 요소에 해당하는 셀 요소 변경하고

            //3. 유효한 보호필름인지 확인

            //4. 유효한 보호필름이 나오는 즉시 -> 집합의 개수 return;
        }
    }



    public static boolean isValid(){
        for(int j=0 ; j < W; j++){
            int cascade = 0;
            for(int i=1 ; i < D ; i++){
               if(map[i][j] == map[i-1][j]){
                   cascade++;
               }else{
                   cascade = 0;
               }
            }
            if(cascade < K) return false;
        }
        return true;

    }

    public static void subset(){

    }

}
