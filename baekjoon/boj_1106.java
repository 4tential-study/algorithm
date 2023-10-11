import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj_1106 {
    static BufferedReader in;
    static int[][] map;
    static int[][] info;
    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));

        String[] ins = in.readLine().split(" ");
        int c = Integer.parseInt(ins[0]);
        int n = Integer.parseInt(ins[1]);

        map = new int[n+1][2000];
        info = new int[n+1][2];
        for(int i=0 ; i < n ; i++){
            String[] inputs = in.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            info[i+1][0] = a; // money
            info[i+1][1] = b; // person
        }
        //sorting
        Arrays.sort(info, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        //init
        for(int i=0 ; i <= n ; i++){
            map[i][0] = 99999999;
        }
        for(int j=0 ; j < 2000 ; j++){
            map[0][j] = 99999999;
        }

        //dp
        for(int i=1 ; i <= n ; i++){
            for(int j=1 ; j < 2000 ; j++){
                 if(info[i][1] > j){
                    map[i][j] = map[i-1][j];
                }else if(info[i][1] <= j){
                    map[i][j] = Math.min(info[i][0] + map[i][j-info[i][1]], map[i-1][j]);
                }
                if(j%info[i][1]==0){
                    map[i][j] = Math.min(j/info[i][1]*info[i][0], map[i][j]);
                }
            }
        }

        //min구하기
        int min = 99999999;
        for(int i=c ; i < 2000 ; i++){
                min = Math.min(min, map[n][i]);
        }
        System.out.println(min);
    }
}
