import java.util.*;




    class socar02 {
        static int tiles;
        static boolean[][] visited;
        static int[][] delta = {{-1,0},{0,-1},{1,0},{0,1}}; //상좌하우
        static int m;
        static int n;
        static String[][] board;
        public static String solution(int M, int N, String[] BOARD) {
            //m : y, n : x
            tiles = 0;
            visited = new boolean[m][n];
            m = M;
            n = N;
            board = new String[m][n];

            for(int i=0 ; i < m ; i++){
                for(int j=0 ; j < n ; j++){
                    board[i][j] = String.valueOf(BOARD[i].charAt(j));
                }
            }

            for(int i = 0; i < m ; i++){
                for(int j=0 ; j < n ; j++){
                    if(!board[i][j].equals(".") && !board[i][j].equals("*")) tiles++;
                }
            }
            int initial = tiles;
            ArrayList<String> ans = new ArrayList<>(tiles/2);
            while(tiles > 0){
                int  fail = 0;
                for(int i = 0; i < m ; i++){//y
                    for(int j = 0 ; j < n ; j++){//x
                        String tile = board[i][j];
                        if(!tile.equals(".") && !tile.equals("*")) {
                            visited = new boolean[m][n];
                            int possible = dfs(i,j,tile);
                            if(possible == 1) {
                                tiles -= 2;
                                System.out.println(tile);
                                ans.add(tile);
                            }else {
                                fail++;
                            }
                        }
                    }
                }
                if(fail >= initial/2){
                    return "IMPOSSIBLE";
                }

            }

            System.out.println(ans.toString());
            return ans.toString();
        }
        public static int dfs(int y, int x, String tile){
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{y,x,-1,0});
            visited[y][x] = true;

            while(!q.isEmpty()){
                int[] pop = q.poll();

                int dir = pop[2];
                int curve = pop[3];

                if((pop[0] != y || pop[1] != x) && board[pop[0]][pop[1]].equals(tile)){
                    System.out.println(tile);
                    if(curve < 2) {
                        board[pop[0]][pop[1]] = ".";
                        board[y][x] = ".";
                        return 1;
                    }else return -1;
                }
                for(int f = 0 ; f < 4 ; f++){
                    int ay = pop[0] + delta[f][0];
                    int ax = pop[1] + delta[f][1];
                    if(inRange(ay,ax)){
                        if(!visited[ay][ax] && (board[ay][ax].equals(".") || board[ay][ax].equals(tile)  )){
                            if(dir == -1){
                                q.offer(new int[]{ay, ax, f, curve});
                                visited[ay][ax] = true;
                            }
                            else if(dir == f){
                                q.offer(new int[]{ay, ax, f, curve});
                                visited[ay][ax] = true;
                            }else if( (dir+f) % 2 == 1){
                                q.offer(new int[]{ay, ax, f, curve+1});
                                visited[ay][ax] = true;
                            }
                        }
                    }
                }
            }
            return -1;
        }

        public static boolean inRange(int ay, int ax){
            return ay >= 0 && ax >= 0 && ay < m && ax < n;
        }

        public static void main(String[] args) {
            solution(3,3, new String[]{"DBA", "C*A", "CDB"});
        }
    }

