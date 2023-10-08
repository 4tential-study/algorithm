import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class swea_2112 {
    static BufferedReader in;
    static StringTokenizer st;
    static int D;
    static int W;
    static int K;
    static int[][] map, temp;
    static int min;
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
            temp = new int[D][W];
            min = Integer.MAX_VALUE;

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = temp[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            //pre : 약품 투입 필요 없는 경우 -> 0 return;
            if (isValid() || K==1) {
                sb.append(0 + "\n");
            }else {
                injection(0, 0);

                sb.append(min + "\n");
            }
        }
        System.out.println(sb.toString());
    }



    public static boolean isValid(){
        boolean flag = false;
        for(int j=0 ; j < W; j++){
            int cascade = 1;
            for(int i=1 ; i < D ; i++){
               if(temp[i][j] == temp[i-1][j]){
                   cascade++;
                   if(cascade >= K ) {
                       flag = true;
                       break;
                   }
               }else{
                   cascade = 1;
               }

            }
            if(cascade < K) return flag = false;
        }
        return flag;

    }

    public static void injection(int layer, int cnt){ //cnt : 주입하는층개수
        if(cnt >=  min) return;

        if(layer == D ){
            if(isValid()){
                min = Math.min(min, cnt);
            }
            return;
        }
        //주입 안 함
        injection(layer+1, cnt);
        //A주입
        for(int i=0 ; i < W ; i++) temp[layer][i] = 0;
        injection(layer+1, cnt+1);
        //B주입
        for(int i=0 ; i < W ; i++) temp[layer][i] = 1;
        injection(layer+1, cnt+1);

        // 되돌리기
        for(int i=0 ; i < W ; i++) temp[layer][i] = map[layer][i];
    }
}
