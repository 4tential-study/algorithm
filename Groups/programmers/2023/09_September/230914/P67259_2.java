// BFS
class Solution {
    // 직선도로 100원, 코너 : +500원
    private static final int ROAD = 100, CORNER = 500;
    // 우 하 좌 상
    // 우(0) + 좌(2) -> 짝수, 상(3) + 하(1) -> 짝수
    // 우(0) + 상(3) -> 홀수
    private static final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static int min_cost = Integer.MAX_VALUE; // 최소 비용
    private static int[] finish;
    public static int solution(int[][] board) {
        finish = new int[] {board.length-1 , board[0].length-1};

        boolean[][] visited = new boolean[board.length][board[0].length];
        visited[0][0] = true;
        return min_cost;
    }

    public static void main(String[] args) {
        int[][] board = {
            {0, 0, 0, 0, 0, 0, 0, 1}, 
            {0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 1, 0, 0}, 
            {0, 0, 0, 0, 1, 0, 0, 0}, 
            {0, 0, 0, 1, 0, 0, 0, 1}, 
            {0, 0, 1, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0}, 
            {1, 0, 0, 0, 0, 0, 0, 0}
        };
        System.out.println(solution(board));
    }

    public static boolean inBoard(int[][] board, int y, int x){
        int length_y = board.length, length_x = board[0].length;

        return !(y < 0 || x < 0 || y >= length_y || x >= length_x);
    }

    static void printArray(int[][] board, boolean[][] visited){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < visited.length; i++){
            for(int j = 0; j < visited[0].length; j++){
                if(board[i][j] == 1) sb.append("2 ");
                else if(visited[i][j]) sb.append("1 ");
                else sb.append("0 ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}