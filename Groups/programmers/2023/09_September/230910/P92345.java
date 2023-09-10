// 아직 푸는 중...
// 문제의 size를 생각하면 완전 탐색으로도 풀릴 것 같다.
class Solution {
    // 상 하 좌 우
    static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int answer = Integer.MAX_VALUE;
    // 매개변수 : 
    // 게임판( 1: 발판이 있음, 0: 발판이 없음 ), 
    // 플레이어 A의 좌표, 플레이어 B의 좌표 (y, x)
    public static int solution(int[][] board, int[] aloc, int[] bloc) {
        Search(board, aloc, bloc, 0);
        return answer;
    }

    static void Search(int[][] board, int[] current, int[] opponent, int count){
        int cy = current[0], cx = current[1];
        
        // 현재 탐색을 시작한 위치에 발판이 없다면 탐색 중단.
        if(board[cy][cx] == 0){
            answer = Math.min(answer, count); return;
        }
 
        // 주변을 탐색
        int[] around_state = new int[4];
        boolean none_player = false, canMove = false;
        for(int type = 0; type < 4; type++){
            int dy = cy + dir[type][0];
            int dx = cx + dir[type][1];

            // 해당 위치로 갈 수 없다면 -1
            if(!inBoard(board, dy, dx) || board[dy][dx] == 0) around_state[type] = -1;

            // 해당 위치에 상대 플레이어가 있다면 2
            else if(dy == opponent[0] && dx == opponent[1]) {
                canMove = true;
                around_state[type] = 2;
            }
            // 아무도 없는 발판이라면 1
            else {
                canMove = true;
                none_player = true;
                around_state[type] = board[dy][dx];
            }
        }

        if(!canMove){
            answer = Math.min(answer, count); return;
        }
        // 이동할 수 있는 발판 중 상대 플레이어가 없는 발판이 없다면,
        // 이동할 수 있는 발판이 그 곳밖에 없다는 의미이므로 그 발판으로 이동한다.
        else if(!none_player){
            int[][] temp = copyBoard(board);
            temp[cy][cx] = 0;  Search(temp, opponent, opponent, count+1);
        }
        // 이동할 수 있는 발판 중 상대 플레이어가 없는 발판이 있다면,
        // 그 발판으로 우선 이동한다.
        else {
            for(int type = 0; type < 4; type++){
                if(around_state[type] != 1) continue;
                int dy = cy + dir[type][0];
                int dx = cx + dir[type][1];

                int[][] temp = copyBoard(board);
                temp[cy][cx] = 0;
                Search(temp, opponent, new int[] {dy, dx}, count+1);
            }
        }
    }

    static int[][] copyBoard(int [][] board){
        int length_y = board.length, length_x = board[0].length;
        int[][] temp = new int[length_y][length_x];
        for(int i = 0; i < board.length; i++)
            System.arraycopy(board[i], 0, temp[i], 0, length_x);

        return temp;
    }

    static boolean inBoard(int[][] board, int y, int x) {
        int length_y = board.length, length_x = board[0].length;
        return (y >= 0 && x >= 0 && y < length_y && x < length_x);
    }

    // public static void main(String[] args) {
    //     int[][] board = {{1}};
    //     int[] Aloc = {0, 0}, Bloc = {0, 0};
    //     System.out.println(solution(board, Aloc, Bloc));
    // }
}
