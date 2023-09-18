import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_3085 {
    static int n;
    static char[][] map;
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        // map 초기화
        for(int i=0 ; i < n ; i++){
            String str = br.readLine();
            for(int j=0 ; j < n ; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // 가로 순회, 스왑, 길이 갱신
        int max =0;

        for(int i=0 ; i < n ; i++){
            int len = 1;
            char prev = map[i][0];
            for(int j=1 ; j < n ; j++){
                if (prev != map[i][j]) {
                    swap( new int[]{i,j-1} , new int[]{i,j});
                    len = findLongest();
                    max = Math.max(len, max);
                    len = 1;
                    swap( new int[]{i,j-1} , new int[]{i,j});
                } else len++;
                prev = map[i][j];
            }
            max = Math.max(len, max);
        }


        // 세로 순회, 스왑 ,길ㅇ ㅣ갱신

        for(int j=0 ; j < n ; j++){
            int len = 1;
            char prev = map[0][j];
            for(int i=1 ; i < n ; i++){
                if( prev != map[i][j]) {
                    swap(new int[]{i-1,j}, new int[]{i,j});
                    len = findLongest();
                    max = Math.max(len, max);
                    len = 1;
                    swap(new int[]{i-1,j}, new int[]{i,j});
                }else len++;
                prev = map[i][j];
            }
            max = Math.max(len, max);
        }

        System.out.println(max);
    }

    public static void swap(int[] from , int[] to){
        char temp = map[from[0]][from[1]];
        map[from[0]][from[1]] = map[to[0]][to[1]];
        map[to[0]][to[1]] = temp;
    }

    public static int findLongest(){
        int max = 0;
        for(int i=0 ; i < n ; i++){
            char prev = map[i][0];
            int len = 1;
            for(int j=1 ; j < n ; j++){
                if (prev == map[i][j]){
                    len++;
                } else {
                    max = Math.max(len, max);
                    len = 1;
                }
                prev = map[i][j];
            }
            max = Math.max(len, max);

        }


        // 세로 순회, 스왑 ,길ㅇ ㅣ갱신
        for(int j=0 ; j < n ; j++){
            char prev = map[0][j];
            int len = 1;
            for(int i=1 ; i < n ; i++){
                if( prev == map[i][j]) {
                    len++;
                } else {
                    max = Math.max(len, max);
                    len = 1;
                }
                prev = map[i][j];
            }
//            max = Math.max(len, max);
        }
        return max;
    }

}
