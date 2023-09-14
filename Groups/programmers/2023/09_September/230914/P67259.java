import java.util.Arrays;

class Solution {
    // 우 하 좌 상
    private static final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static int min_cost = Integer.MAX_VALUE; // 최소 비용
    private static int[] finish; // 도착지
    private static int[][] weights; // 각 칸에 도달할 수 있는 최소 비용
    public static int solution(int[][] board) {
        finish = new int[] {board.length-1 , board[0].length-1};
        weights = new int[board.length][board[0].length];
        for(int i = 0; i < weights.length; i++) Arrays.fill(weights[i], Integer.MAX_VALUE);

        boolean[][] visited = new boolean[board.length][board[0].length];
        
        visited[0][0] = true;
        weights[0][0] = 0;

        for(int type = 0; type < 2; type++){
            int dy = dir[type][0], dx = dir[type][1];
            if(board[dy][dx] == 0){
                visited[dy][dx] = true;
                solve(board, new int[] {dy, dx}, type, 100, visited);
                visited[dy][dx] = false;
            }
        }
        return min_cost;
    }

    public static void solve(int[][] board, int[] current, int cur_dir, int cost, boolean[][] visited){
        int cy = current[0], cx = current[1];
         // 각 칸에 도달할 수 있는 최소 비용보다 현재 비용이 높을 경우 return.
        if(weights[cy][cx] < cost) return;
        else if(weights[cy][cx] > cost) weights[cy][cx] = cost;
        
        // 목적지에 도착하였으면, 최소 비용 저장
        if(cy == finish[0] && cx == finish[1]){
            min_cost = Math.min(min_cost, cost);
            return;
        }

        for(int type = 0; type < 4; type++){
            int dy = cy + dir[type][0];
            int dx = cx + dir[type][1];

            if(inBoard(board, dy, dx) && board[dy][dx] == 0 && !visited[dy][dx]){
                visited[dy][dx] = true;
                if(cur_dir != type) // 현재 이동하는 경로와 다른 경우엔 커브를 돌아야 하므로 cost에 600(100+500) 추가
                    solve(board, new int[] {dy, dx}, type, cost + 600, visited);
                
                    // 그렇지 않다면 cost에 100 추가
                else solve(board, new int[] {dy, dx}, type, cost + 100, visited);
                visited[dy][dx] = false;
            }
        }
    }

    public static boolean inBoard(int[][] board, int y, int x){
        int length_y = board.length, length_x = board[0].length;

        return !(y < 0 || x < 0 || y >= length_y || x >= length_x);
    }
}