import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_3190 {
    static BufferedReader in;
    static StringTokenizer st;
    static int n;
    static int k;
    static int l;
    static int[][] map;
    static ArrayList<int[]> snake = new ArrayList<>();
    static final int APPLE = 1;
    static int[][] delta = {{-1,0},{0,-1},{1,0},{0,1}}; //상좌하우
    static boolean[][] visited;
    static int D = 3;
    static int[][] minute;
    static int time = 0;
    static PriorityQueue<Dir> pq = new PriorityQueue<Dir>();

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(in.readLine());
        k = Integer.parseInt(in.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];
        minute = new int[n][n];
        for(int i=0 ; i < k ; i++){
            String[] ins = in.readLine().split(" ");
            int y = Integer.parseInt(ins[0])-1;
            int x = Integer.parseInt(ins[1])-1;
            map[y][x] = APPLE;

        }
        l = Integer.parseInt(in.readLine());
        for(int i=0 ; i < l ; i++){
            String[] ins = in.readLine().split(" ");
            int x = Integer.parseInt(ins[0]);
            String d = ins[1];
            pq.offer(new Dir(x, d));
        }
        //입력 받기
        bfs(0,0);
        //출력 하기
        System.out.println(time);
    }

    public static class Dir implements Comparable<Dir>{
        int sec;
        String dir;
        Dir(int sec, String dir){
            this.sec = sec;
            this.dir = dir;
        }

        @Override
        public int compareTo(Dir o) {
            return this.sec - o.sec; //오름차순
        }
    }

    public static void bfs(int y, int x){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{y,x});
        snake.add(new int[]{y,x});

        while(!q.isEmpty()){
            int[] poll = q.poll();
            //현재 시간에 방향 전환 여부 확인
            if(!pq.isEmpty()) {
                if (minute[poll[0]][poll[1]] == pq.peek().sec) {
                    Dir d = pq.poll();
                    if (d.dir.equals("L")) {
                        D = (D + 1) % 4;
                    } else {
                        D = (D - 1) % 4;
                    }
                }
            }
            //지정 방향으로 이동 할때
            int ay = poll[0] + delta[D][0];
            int ax = poll[1] + delta[D][1];
            //종료되는지 확인
            if(!inRange(ay,ax) || inSnake(ay,ax) ) {
                break;
            }
            //진행되는 경우 시간 추가
            minute[ay][ax] = minute[poll[0]][poll[1]] + 1;
            //사과 여부 확인, 먹기
            eatApple(ay,ax);
            //이동하기
            q.offer(new int[]{ay,ax});
            //최근 플레이 시간을 업뎃하기
            time = minute[ay][ax] + 1;
            }

    }

    private static boolean inRange(int ay, int ax) {
        return ay >= 0 && ax >= 0 && ay < n && ax < n;
    }

    private static boolean inSnake(int ay ,int ax){
        for(int i=0 ; i < snake.size() ; i++){
            if(snake.get(i)[0] == ay && snake.get(i)[1] == ax) return true;
        }
        return false;
    }

    private static void eatApple(int ay ,int ax){
        //이동 하는 칸에 사과가 있는 경우, 길이 연장
        if(map[ay][ax] == APPLE){
            map[ay][ax] = 0;
            snake.add(new int[]{ay,ax});
        }
        //사과없는 경우, 그냥 이동
        else if(map[ay][ax] == 0){
            snake.add(new int[]{ay,ax});
            snake.remove(0);
        }
    }

}
