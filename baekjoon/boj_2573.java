import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_2573 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int m;
    static int[][] delta = {{-1,0}, {0,-1}, {1,0}, {0,1}};
    static Queue<int[]> dq = new ArrayDeque<>();
    static int[][] years;
    static int year;
    static int[][] map;
    static boolean[][] visited;

    static final int SEA = -10;
    static int cnt = 0;
    static int MELT = 0;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]); //y
        m = Integer.parseInt(s[1]); //x
        years = new int[n][m];
        map = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0; i < n ; i++){
            st  = new StringTokenizer(br.readLine());
            for(int j=0 ; j < m ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0){
                    map[i][j] = SEA;
                }
            }
        }

        while(cnt < 2) {
            year++;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(map[i][j] > 0) dq.offer(new int[]{i,j});
                }
            }
            System.out.println(cnt+ " " + "bfs");
            bfs();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            int ice = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] != SEA && map[i][j] != MELT) ice++;
                }
            }
            System.out.println(ice);
            if (ice == 0) {
                System.out.println(0);
                return;
            }

            visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] > 0 && !visited[i][j]) {
                        System.out.println(i + " " + j);
                        System.out.println("??");
                        dfs(i, j);
                        cnt++;
                    }
                }
            }
            System.out.println("cmt "+cnt);
        }
        System.out.println("year "+year);

    }

    public static void bfs(){
        while(!dq.isEmpty()){
            int[] poll = dq.poll();
            for(int f=0 ; f < 4; f++){
                int y = poll[0] + delta[f][0];
                int x = poll[1] + delta[f][1];
                if(y >= 0 && y < n && x >= 0 && x < m && map[y][x] <= 0 ){
//                    dq.offer(new int[]{y,x});
                    map[poll[0]][poll[1]]--;
                    years[y][x] = years[poll[0]][poll[1]] + 1;
                    map[poll[0]][poll[1]] = Math.max(map[poll[0]][poll[1]], 0);

                }
            }
        }

    }

    public static void dfs(int y, int x){
        Stack<int[]> stack = new Stack<>();
        stack.add(new int[] {y,x});

        while(!stack.isEmpty()){
            int[] pop = stack.pop();
            for(int i=0 ; i < 4 ; i++){
                int ay = pop[0] + delta[i][0];
                int ax = pop[1] + delta[i][1];
                if(ay >= 0 && ay < n && ax >= 0 && ax < m && map[ay][ax] > 0 && !visited[ay][ax]){
                    stack.add(new int[]{ay,ax});
                    visited[ay][ax] = true;

                }

            }
        }
    }
}
