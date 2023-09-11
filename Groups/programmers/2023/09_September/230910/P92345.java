class Solution {
    private static final int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    boolean[][] visited;
    public int solution(int[][] board, int[] aloc, int[] bloc){
        visited = new boolean[board.length][board[0].length];

        return solve(board, aloc, bloc);
    }

    // 플레이어들이 최선을 다하였을 때의 이동 횟수
    // 짝수 : 플레이어의 패배, 홀수 : 플레이어의 승리
    public int solve(int[][] board, int[] current, int[] opponent){
        int cy = current[0], cx = current[1];
        if(visited[cy][cx]) return 0;

        int result = 0;
        for(int type = 0; type < 4; type++){
            int dy = cy + dir[type][0];
            int dx = cx + dir[type][1];

            if(!inBoard(board, dy, dx) || visited[dy][dx] || board[dy][dx] == 0) continue;
            
            visited[cy][cx] = true;
            int value = solve(board, opponent, new int[] {dy, dx}) + 1;
            visited[cy][cx] = false;

            if(result % 2 == 0 && value % 2 == 1) result = value;
            else if(result % 2 == 0 && value % 2 == 0) result = Math.max(result, value);
            else if(result % 2 == 1 && value % 2 == 1) result = Math.min(result, value);
        }

        return result;
    }

    static boolean inBoard(int[][] board, int y, int x) {
        int length_y = board.length, length_x = board[0].length;
        return (y >= 0 && x >= 0 && y < length_y && x < length_x);
    }

    static int[][] copyBoard(int [][] board){
        int length_y = board.length, length_x = board[0].length;
        int[][] temp = new int[length_y][length_x];
        for(int i = 0; i < board.length; i++)
            System.arraycopy(board[i], 0, temp[i], 0, length_x);

        return temp;
    }
}
