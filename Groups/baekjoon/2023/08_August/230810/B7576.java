import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class B7576 {
    static int M, N, count;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = in.readLine().split(" ");
        // 가로, 세로
        M = Integer.parseInt(inputs[0]); N = Integer.parseInt(inputs[1]);
        int[][] board = new int[N][M];
        Queue<int[] > queue = new ArrayDeque<>();
        boolean flag = false;
        for(int i = 0; i < N; i++){
            inputs = in.readLine().split(" ");
            for(int j = 0; j < M; j++){
                board[i][j] = Integer.parseInt(inputs[j]);
                if(board[i][j] == 1)    queue.offer(new int[] {i, j});
                else if(!flag && board[i][j] == 0)   flag = true;
            }
        }
        if(!flag){  // 익지 않은 토마토가 없으므로 바로 종료
            System.out.println(count);  return;
        }
        // BFS 실행
        int[][] dir = {{0,1}, {-1,0}, {0, -1}, {1,0}};
        while(!queue.isEmpty()){
            int current_size = queue.size();
            for(int i = 0; i < current_size; i++){
                int[] current = queue.poll();
                for(int types = 0; types < 4; types++){
                    int cy = current[0] + dir[types][0], cx = current[1] + dir[types][1];
                    if(inBoard(cy, cx) && board[cy][cx] == 0){
                        board[cy][cx] = 1;  queue.offer(new int[]{cy, cx});
                    }
                }
            }
            if(!queue.isEmpty()) count++;
        }

        // 아직 익지 않은 토마토가 있다면 -1 출력 후 main 메서드 종료
        for(int[] row : board){
            for(int cell : row){
                if(cell == 0){
                    System.out.println(-1);
                    return;                   
                }
            }
        }
        System.out.println(count);
    }   

    static boolean inBoard(int y, int x){
        return y >= 0 && x >= 0 && x < M && y < N;
    }
}