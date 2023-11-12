import java.util.*;

class prog_17679 {
    static int[][] delta ={{-1,0},{0,-1},{1,0},{0,1}};
    static int m;
    static int n;
    static char[][] boards;
    static boolean[][] visited;//bfs 내부에서 쓰임+for문 지나쳐야할때 사용
    static boolean[][] v; //겹치는 경우 & 삐져나오는 경우 때문에 필요
    public int solution(int M, int N, String[] board) {//m==y
        boards = new char[M][N];
        m = M;
        n = N;
        for(int i=0 ; i < m ; i++){
            String str = board[i];
            for(int j=0 ; j < n ; j++){
                boards[i][j] = str.charAt(j);
            }
        }
        int answer = 0;

        boolean flag = true;
        while(flag){
            visited = new boolean[m][n];
            v = new boolean[M][N];
            for(int i=0 ; i < m-1 ; i++){
                for(int j=0 ; j < n-1 ; j++){
                    if (!visited[i][j] && boards[i][j] == boards[i+1][j] && boards[i+1][j] == boards[i][j+1] && boards[i][j+1] == boards[i+1][j+1] && (boards[i][j] != '0')) {
                        v[i][j] = true;
                        v[i+1][j] = true;
                        v[i][j+1] = true;
                        v[i+1][j+1] = true;
                    }
                }
            }
            for(int i=0 ; i < m-1 ; i++){
                for(int j=0 ; j < n-1 ; j++){
                    if (!visited[i][j] && v[i][j] && (boards[i][j] != '0')) {
                        int count = bfs(i,j,boards[i+1][j+1]);
                        boards[i][j] = '0';
                        answer += count;
                    }
                }
            }
            flag = compaction(boards);
        }
        return answer;
    }

    public int bfs(int y ,int x, char letter){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{y, x});
        visited[y][x] = true;
        int cnt = 1;
        while(!q.isEmpty()){
            int[] poll = q.poll();
            for(int f = 0 ; f < 4 ; f++){
                int ay = poll[0] + delta[f][0];
                int ax = poll[1] + delta[f][1];
                if(inRange(ay,ax) && !visited[ay][ax] && boards[ay][ax] == letter && v[ay][ax]){

                    visited[ay][ax] = true;
                    boards[ay][ax] = '0';
                    cnt++;
                    q.offer(new int[]{ay, ax});
                }
            }
        }
        return cnt;

    }

    public boolean inRange(int y, int x){
        return y >= 0 && x >= 0 && y < m && x < n;
    }

    public boolean compaction(char[][] board){
        boolean drag = false;
        for(int j=0 ; j < n  ; j++){
            int cnt = 0;
            for(int i=m-1 ; i >= 0 ; i--){
                if(boards[i][j] == 'X') continue;
                if(boards[i][j] == '0'){
                    cnt++; //2
                }else if(cnt > 0){
                    //swap
                    char temp = boards[i+cnt][j];
                    boards[i+cnt][j] = board[i][j];
                    boards[i][j] = temp;
                    drag = true;
                }
            }
        }
        return drag;

    }
}