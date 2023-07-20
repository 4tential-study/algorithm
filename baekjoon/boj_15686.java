import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj_15686 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static HashMap<Integer, Home> homes = new HashMap<>();
    static ArrayList<Home> homeArrays = new ArrayList<>();
    static ArrayList<Chicken> chickenArrays = new ArrayList<>();

    static HashMap<Integer, Chicken> chickens = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        int[][] board = new int[N][N];
        int chickenCount = 0;
        int homeCount = 0;
        for(int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j =0 ; j < N ; j++ ) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1){

                    Home home = new Home(i,j);
                    homes.put(++homeCount,home);
                }
                if (board[i][j] == 2){
//                    chickenCount++;
                    Chicken chicken = new Chicken(i,j);
                    chickens.put(++chickenCount, chicken);
                }
            }
        }

        for(Home home : homeArrays){
            int x = home.x;
            int y = home.y;
            //집마다 치킨거리 구하기
            int minDist = 10000;
            for(Chicken chicken : chickenArrays){
                int x1 = chicken.x;
                int y1 = chicken.y;
                int distance = calculateDistance(x, y, x1, y1);
                if (minDist > distance) {
                    minDist = distance;
                }
            }
            // 치킨거리 저장
            home.chickenDistance = minDist;
        }





    }

    public static class Home{
        int y;
        int x;
        int chickenDistance;

        Home(int y, int x){
            this.y=y;
            this.x=x;
        }
    }

    public static class Chicken{
        int y;
        int x;
        Chicken(int y, int x){
            this.y=y;
            this.x=x;
        }
    }

    private static int calculateDistance(int x, int y, int x1, int y1){
        return Math.abs(x-x1) + Math.abs(y- y1);
    }

    public static void backtracking(int x, int y, int m, ArrayList<int[]> mlist){
        if (mlist.size() == m){

            return;
        }


    }

}
