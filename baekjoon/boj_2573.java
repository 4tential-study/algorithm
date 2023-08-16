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
    static final int MELT = 0;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]); 
        m = Integer.parseInt(s[1]); 
        years = new int[n][m];
        map = new int[n][m];
        visited = new boolean[n][m];

        //초기화 하면서, 녹은 바다(MELT)와 원래 바다(SEA)를 구분하기 위해 다른 파이널 변수를 사용
        for(int i=0; i < n ; i++){
            st  = new StringTokenizer(br.readLine());
            for(int j=0 ; j < m ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0){
                    map[i][j] = SEA;
                }
            }
        }
        
        //dfs수행 횟수가 2일 경우 멈춤
        while(cnt < 2) {
            year++;
            //매년 남은 빙산을 큐에 담는다
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(map[i][j] > 0) dq.offer(new int[]{i,j});
                }
            }
            bfs();
            //녹은 바다를 다음해 수행전에 원래바다처럼 변경해준다.
            //두 바다를 퉁치면, bfs수행중인 같은해에도 빙산에 영향을 줘버린다. 
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(map[i][j] == MELT) map[i][j] = SEA;
                }             
            }
            
            int ice = 0;            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] != SEA && map[i][j] != MELT) ice++;
                }
            }
                        
            if (ice == 0) {
                System.out.println(0);
                return;
            }
            
            visited = new boolean[n][m];
            cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] > 0 && !visited[i][j]) {
                        dfs(i, j);
                        cnt++; //dfs수행횟수==덩어리 개수
                    }
                }
            }
        }
        System.out.println(year);

    }

    public static void bfs(){
        while(!dq.isEmpty()){
            int[] poll = dq.poll();
            for(int f=0 ; f < 4; f++){
                int y = poll[0] + delta[f][0];
                int x = poll[1] + delta[f][1];
                if(y >= 0 && y < n && x >= 0 && x < m && map[y][x] == SEA ){
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
