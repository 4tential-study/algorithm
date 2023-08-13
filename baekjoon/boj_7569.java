import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_7569 {
    static BufferedReader br;
    static int[][][] board;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int h = Integer.parseInt(s[2]);
        board = new int[n][m][h];


        for(int z=0 ; z < h ; z++){
            for(int y=0 ; y < m ; y++){
                st = new StringTokenizer(br.readLine());
                for(int x= 0 ; x < n ; x ++){
                    board[z][y][x] = Integer.parseInt(st.nextToken());
                }
            }
        }
    }
}
