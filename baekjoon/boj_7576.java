import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_7576 {
    static BufferedReader br;
    static int[][] board;

    static StringTokenizer st;



    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int x = Integer.parseInt(s[0]);
        int y = Integer.parseInt(s[1]);

        board = new int[y][x];

        for(int i=0 ; i < y ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0 ; j < x ; j++){
                board[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        int zero = 0;
        boolean bool = false;
        for(int i=0 ; i < y ; i++){
            for(int j=0 ; j < x ; j++){
                if( board[i][j] == 0 ) zero++;
                if(board[i][j] == )
            }
        }

        if(zero==0) {
            System.out.println(-1);
        }

    }

    public static void bfs(){

    }
}
