import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_2382 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int t;
    static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
    static Micro[] micros;
    static int[][] map;

    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());


        for (int tc = 1; tc <= t; tc++) {
            String[] s = br.readLine().split(" ");
            n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            int k = Integer.parseInt(s[2]);
//            map = new int[n][m];
            micros = new Micro[k];
            sb.append("#").append(tc).append(" ");
            for (int i = 0; i < k; i++) {
                String[] st = br.readLine().split(" ");
                int y = Integer.parseInt(st[0]);
                int x = Integer.parseInt(st[1]);
                int cnt = Integer.parseInt(st[2]);
                int dir = Integer.parseInt(st[3]);
                micros[i] = new Micro(y, x, cnt, deltas[dir-1]);

            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < k; j++) {
                    Micro cur = micros[j];

                    cur.y += cur.dir[0];
                    cur.x += cur.dir[1];
                    if (cur.y >= 0 && cur.x >= 0 && cur.y < n && cur.x < n ) {
                        if (isOut(cur.y, cur.x)) {
                            //아웃되는경우
                            cur.dir = new int[]{cur.dir[0] * -1, cur.dir[1] * -1};
                            if (cur.cnt == 1) cur.cnt = 0;
                            else cur.cnt = (int) Math.ceil(cur.cnt / 2);
                            //                        System.out.println(j+" "+cur.cnt);
                        }
                    }
                }

                for (int j = 0; j < k; j++) {
                    Micro cur = micros[j];
                    int temp = 0;
                    for (int z = 0; z < k; z++) {
                        if (z == j) continue;
                        Micro compared = micros[z];
                        //겹치는 경우

                        if (cur.y == compared.y && cur.x == compared.x) {
                            //이동방향 바꾸기
                            if (cur.cnt < compared.cnt){
                                cur.dir = compared.dir;
                                temp += cur.cnt; //8+10
                                cur.cnt = compared.cnt;
                                compared.cnt = 0;
                            }
                        }
                    }
                    cur.cnt += temp;
                }

            }
            int count = 0;
            for (int i = 0; i < k; i++) {
                count += micros[i].cnt;
            }
            sb.append(count + "\n");
        }
        System.out.println(sb.toString());
    }


    public static boolean isOut(int y, int x){
        if (y >= 1 && x >= 1 && y < n-1 && x < n-1) return false;
        return true;
    }
    public static class Micro{
        int x;
        int y;
        int cnt;
        int[] dir;
        Micro(int y, int x, int cnt , int[] dir ){
            this.y=y;
            this.x=x;
            this.cnt=cnt;
            this.dir=dir;
        }
    }
}
