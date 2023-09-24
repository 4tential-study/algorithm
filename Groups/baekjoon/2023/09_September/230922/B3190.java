import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class B3190 {
    private static class changeDir{
        int second;
        char LD;

        changeDir(int second, char LD){
            this.second = second;
            this.LD = LD;
        }
    }
    // 우 하 좌 상
    private static final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int APPLE = 9;
    private static List<int[]> snake = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(in.readLine());
        int apple_count = Integer.parseInt(in.readLine());

        // 사과 위치 표시
        int[][] board = new int[length][length];
        for(int apple = 0; apple < apple_count; apple++){
            String[] input = in.readLine().split(" ");
            int y = Integer.parseInt(input[0]), x = Integer.parseInt(input[1]);
            board[y-1][x-1] = APPLE;
        } 

        int change_count = Integer.parseInt(in.readLine());
        Queue<changeDir> queue = new ArrayDeque<>();
        for(int count = 0; count < change_count; count++){
            String[] input = in.readLine().split(" ");
            int s = Integer.parseInt(input[0]);
            char ld = input[1].charAt(0);

            queue.offer(new changeDir(s, ld));
        }

        // deque의 제일 앞에 있는 좌표는 머리의 좌표.
        // 가장 뒤에 있는 좌표는 꼬리의 좌표.
        snake.add(new int[] {0, 0});

        int cy = 0, cx = 0;
        int second = 0, direction = 0;
        Search:
        while(true){
            second++;
            cy +=  dir[direction][0];
            cx +=  dir[direction][1];

            // 현재 머리가 이동해야 할 곳이 board 바깥으로 벗어나거나 몸통이라면 중지.
            if(!(cy >= 0 && cx >= 0 && cy < length && cx < length)) break;
            else {
                for(int i = 0; i < snake.size(); i++){
                    int[] arr = snake.get(i);
                    if(arr[0] == cy && arr[1] == cx) break Search;
                }
            }
            if(board[cy][cx] != APPLE)  snake.remove(0);
            else board[cy][cx] = 0;
            snake.add(new int[] {cy, cx});

            if(!queue.isEmpty() && second == queue.peek().second){
                if(queue.poll().LD == 'D') direction = (direction + 1) % 4;
                else direction = 3 - direction;
            }
        }
        System.out.println(second);
    }
}