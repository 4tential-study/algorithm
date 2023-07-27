import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj_15686 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        int[][] board = new int[N][N];
        ArrayList<int[]> chickens = new ArrayList<>();
        ArrayList<int[] > homes = new ArrayList<>();
        int chickenCount = 0;
        int homeCount = 0;
        for(int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j =0 ; j < N ; j++ ) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1){
                    homes.add(new int[]{i,j});
                }
                if (board[i][j] == 2){
                    chickens.add(new int[]{i,j});
                }
            }
        }

        search( homes, chickens, M);

//        System.out.println(answer);
    }

    private static int calculateDistance(int x, int y, int x1, int y1){
        return Math.abs(x-x1) + Math.abs(y- y1);
    }

    public static void search( ArrayList<int[]> homes, ArrayList<int[]> chickens , int m){
        int result = 0;
        int answer = 0;

        for(int[] home : homes){
            int x = home[0];
            int y = home[1];
            //집마다 치킨거리 구하기
            int minDist = 10000;
            for(int[] chicken : chickens){
//                if (cur == m) {
//                    break;
//                }
                int x1 = chicken[0];
                int y1 = chicken[1];

                int distance = calculateDistance(x, y, x1, y1);
                System.out.println("distance"+distance);
                if (minDist > distance) {
                    minDist = distance;
                }

                System.out.println(": "+ minDist); //

            }
            result+=minDist;
            System.out.println(result);
        }

    }


}
