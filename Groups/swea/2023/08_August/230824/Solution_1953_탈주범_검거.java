import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_1953_탈주범_검거 {
    // 상 하 좌 우 기준으로 해당 파이프가 갈 수 있는 방향을 적는다.
    // 1 이면 해당 방향으로 갈 수 있으며 0이면 그 방향으로 진행할 수 없다.
    // pipe 0번은 존재하지 않으므로 -1.
    static final int[][] pipes = {{-1}, {1, 1, 1, 1}, {1, 1, 0, 0}, {0, 0, 1, 1},
                             {1, 0, 0, 1}, {0, 1, 0, 1}, {0, 1, 1, 0}, {1, 0, 1, 0}};
    static final int[][] dir = {{-1, 0},{1, 0},{0, -1},{0, 1}}; // 상 하 좌 우    
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine()); // 테스트 케이스 수
        for(int test_case = 1; test_case <= T; test_case++){
            sb.append("#").append(test_case).append(" ");
            String[] input = in.readLine().split(" ");
            int height = Integer.parseInt(input[0]); // 지하 터널 지도의 세로 크기
            int width = Integer.parseInt(input[1]); // 지하 터널 지도의 가로 크기
            int[] escape_coord = new int[]{Integer.parseInt(input[2]), Integer.parseInt(input[3])}; // 맨홀 좌표
            int spend_time = Integer.parseInt(input[4]); // 탈출 후 소요 시간

            int[][] map = new int[height][width]; // 지하 터널 지도
            for(int y = 0; y < height; y++){
                input = in.readLine().split(" ");
                for(int x = 0; x < width; x++)
                    map[y][x] = Integer.parseInt(input[x]);
            }

            Queue<int[]> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[height][width]; // 지도의 해당 위치를 점검하였는지 확인함.

            // 맨홀 위치로 부터 탐색 시작.
            // 맨홀의 위치는 항상 터널이 있는 곳에 위치하므로 터널 유무는 체크하지 않는다.
            queue.offer(escape_coord);  
            visited[escape_coord[0]][escape_coord[1]] = true;
            int time = 1, result = 1;

            while(!queue.isEmpty() && ++time <= spend_time){
                int size = queue.size();
                for(int i = 0; i < size; i++){
                    int cy = queue.peek()[0], cx = queue.poll()[1];
                    int pipe_type = map[cy][cx]; // 해당 위치에서 터널의 모양을 확인한다.
                    for(int type = 0; type < 4; type++){
                        int dy = cy + dir[type][0], dx = cx + dir[type][1];

                        // 현재 탐색 위치에서 해당 방향으로 갈 수 있는지 파악. 
                        if(dy >= 0 && dx >= 0 && dy < height && dx < width 
                        && !visited[dy][dx] && pipes[pipe_type][type] == 1 && map[dy][dx] != 0) {
                            int value = map[dy][dx];

                            // 반대 방향에 있는 터널도 현재 탐색 위치에 이동할 수 있다면 진행.
                            if((type < 2 && pipes[value][Math.abs(type-1)] == 1)
                            || (type >= 2 && pipes[value][5 - type] == 1)){ 
                                queue.offer(new int[] {dy, dx});
                                visited[dy][dx] = true; result++;
                            }
                        }
                    }
                }
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }   
}