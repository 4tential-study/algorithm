import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_16234 {
    static BufferedReader in;
    static StringTokenizer st;
    static int n;
    static int l;
    static int r;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<int[]> unionXY = new ArrayList<>();
    static int ans;
    static boolean isMove;
    static int[][] delta = {{-1,0},{0,-1},{1,0},{0,1}};

    public static void main(String[] args) throws IOException{
        init();

        move();

        System.out.println(ans);
    }

    public static void move(){

        while(true) {
            isMove = false;
            visited = new boolean[n][n];
            for(int i=0 ; i < n ; i++) {
                for(int j=0 ; j < n ; j++) {
                    if(!visited[i][j] ) {
                        bfs(i,j);
                    }
                }
            }
            if(!isMove) break;
            else ans++;
        }

    }

    public static void bfs(int y, int x) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {y,x});
        visited[y][x] = true;
        unionXY.add(new int[]{y,x});

        while(!q.isEmpty()) {
            int[] poll = q.poll();

            for(int f=0; f < 4 ;f++) {
                int ay = poll[0] + delta[f][0];
                int ax = poll[1] + delta[f][1];
                if(inRange(ay,ax) && !visited[ay][ax]  ) {
                    int diff = Math.abs(map[poll[0]][poll[1]] - map[ay][ax]);
                    if(diff >= l && diff <= r) {
                        visited[ay][ax] = true;
                        isMove = true;
                        unionXY.add(new int[]{ay,ax});
                        q.offer(new int[] {ay,ax});
                    }
                }
            }

        }
        migrate();
    }

    private static void init() throws IOException{
        in = new BufferedReader(new InputStreamReader(System.in));
        String[] ins = in.readLine().split(" ");
        n = Integer.parseInt(ins[0]);
        l = Integer.parseInt(ins[1]);
        r = Integer.parseInt(ins[2]);
        map = new int[n][n];
        visited = new boolean[n][n];
        for(int i=0 ; i < n ; i++) {
            st = new StringTokenizer(in.readLine());
            for(int j=0 ; j < n ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void migrate(){
        int sum = 0;
        for(int i=0; i<unionXY.size(); i++) {
            int[] p = unionXY.get(i);
            sum += map[p[0]][p[1]];
        }
        for(int i=0; i<unionXY.size(); i++) {
            int[] p = unionXY.get(i);
            map[p[0]][p[1]] =  sum / unionXY.size();
        }
        unionXY.removeAll(unionXY);
    }

    private static boolean inRange(int y , int x) {
        return y >=0 && x>=0 && y <n && x< n;
    }
}