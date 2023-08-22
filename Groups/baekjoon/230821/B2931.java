import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class B2931 {
    // 상 우 하 좌
    static final byte[][] dir = {{-1, 0},{0, 1},{1, 0},{0, -1}};

    // 1 : Γ 2 : ㄴ : 3 : 」 : 4 : ㄱ
    // UP : 아래에서 위로 갈 수 있는 블럭, DOWN : 위에서 아래로 갈 수 있는 블럭
    static final String UP = "|14", DOWN = "|23"; 
    // LEFT : 오른쪽에서 왼쪽으로 갈 수 있는 블럭, RIGHT : 왼쪽에서 오른쪽으로 갈 수 있는 블럭
    static final String LEFT = "-12", RIGHT = "-34";
    static final char[] pipes= {'|', '-', '1', '2', '3', '4'};

    static final String[] dirString = {UP, RIGHT, DOWN, LEFT};
    static final char EMPTY = '.', START = 'M', FINISH = 'Z';
    static char[][] board;
    static int[][] visited; // 해당 블럭에 들어갔을 때 진행해야 할 경로. 아직 판단하지 않은 블럭은 -1로 초기화.
    static int R, C; // 보드의 세로, 가로
    static int start_dir, finish_dir;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] RCinput = in.readLine().split(" ");
        R = Integer.parseInt(RCinput[0]); C = Integer.parseInt(RCinput[1]);

        board = new char[R][C];
        visited = new int[R][C];

        int[] start_coord   = new int[2];
        int[] finish_coord  = new int[2];

        for(int y = 0; y < R; y++){ // 보드 입력받기
            String input = in.readLine();
            for(int x = 0; x < C; x++){
                char value = input.charAt(x);
                if(value == START)  { // 출발지 좌표 입력 받기
                start_coord[0] = y; start_coord[1] = x;
                }
                else if(value == FINISH){ // 도착지 좌표 입력 받기
                finish_coord[0] = y;  finish_coord[1] = x;
                }
                board[y][x] = value;
            }
        } // 보드 입력 완료

        // 1. 시작점에서 파이프로 갈 수 있는 방향을 구한다.
        for(int i = 0; i < R; i++)  Arrays.fill(visited[i], -1);
        for(int type = 0; type < 4; type++){
            int dy = start_coord[0] + dir[type][0];
            int dx = start_coord[1] + dir[type][1];
            // 탐색 범위를 벗어났다면 재탐색
            if(!(dy >= 0 && dx >= 0 && dy < R && dx < C))   continue;

            // 시작 블럭에서 출발하여 해당 방향으로 갈 수 있는 블럭이라면, 탐색을 종료함.
            if(dirString[type].indexOf(board[dy][dx]) != -1 || board[dy][dx] == '+'){
                visited[start_coord[0]][start_coord[1]] = type;
                break;
            }
        } // 시작점에서의 탐색 끝

        // 2. 보드의 시작점에서부터 경로를 이동하여 빈 칸을 찾는다. 
        // 그리고 빈 칸에 도달 했을 때, 진행 방향을 저장한다.
        int[] EMPTY_coord = findEMPTY(start_coord, true);

        // 3. 빈 칸에 어떤 것을 넣어야 하는지 판단한다.
        // 빈 칸을 기준으로 4방 탐색을 진행.
        List<Integer> goList = new ArrayList<>();
        for(int type = 0; type < 4; type++){
            int dy = EMPTY_coord[0] + dir[type][0];
            int dx = EMPTY_coord[1] + dir[type][1];
            // 탐색할 곳이 보드 안에 있는 범위 인지 판단.
            if(dy >= 0 && dx >= 0 && dy < R && dx < C){
                if(dirString[type].indexOf(board[dy][dx]) != -1 || board[dy][dx] == '+'){
                	int enter_dir;
                	if(type % 2 == 0) enter_dir = Math.abs(type - 2);
                	else enter_dir = 4 - type;
                    goList.add(enter_dir); 
                }
            }
        } // 4방 탐색 종료.
        char result = EMPTY;
        if(goList.size() == 4){
            result = '+';
        }
        else{
            Search:
            for(int i = 0; i < pipes.length; i++){
                char pipe = pipes[i];
                for(int j = 0; j < goList.size(); j++){
                	int idx = goList.get(j);
                    if(dirString[idx].indexOf(pipe) == -1)    continue Search;
                }
                result = pipe;  break;
            }
        }
        System.out.println((EMPTY_coord[0]+1) + " " + (EMPTY_coord[1] + 1) + " " + result);
    }

    static int[] findEMPTY(int[] coord, boolean isStart){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(coord);
        int intoDir = -1;
        int[] EMPTY_coord = new int[2];
        Search_EMPTY:
        while(!queue.isEmpty()){
            int cy = queue.peek()[0], cx = queue.poll()[1];
            // 진입 하였던 방향이 단방향으로 이동할 경우
            if(visited[cy][cx] >= 0 && visited[cy][cx] < 4){
                int type = visited[cy][cx];
                int dy = cy + dir[type][0], dx = cx + dir[type][1]; // 해당 방향으로 탐색할 블럭 이동

                // 아직 방문하지 않은 좌표일 경우
                if(dy >= 0 && dx >= 0 && dy < R && dx < C && visited[dy][dx] == -1){
                    char road = board[dy][dx]; // 탐색할 칸에 어떤 파이프가 있는지 확인.

                    // 휘어져 있는 파이브인 경우
                    if("1234".indexOf(road) != -1){
                        // 이전 진행 방향에 따라 진행해야 할 방향을 정한다.
                        switch(road){
                            case '1': 
                                // 위쪽으로 이동하여 만났을 경우, 오른쪽으로 이동.
                                if(type == 0) visited[dy][dx] = 1; 
                                // 왼쪽으로 이동하여 만났을 경우, 아래쪽으로 이동.
                                else if(type == 3)  visited[dy][dx] = 2;
                                break;
                            case '2': 
                                // 아래쪽으로 이동하여 만났을 경우, 오른쪽으로 이동.
                                if(type == 2)   visited[dy][dx] = 1;
                                // 왼쪽으로 이동하여 만났을 경우, 위쪽으로 이동.
                                else if(type == 3) visited[dy][dx] = 0;
                                break;
                            case '3': 
                                // 오른쪽으로 이동하여 만났을 경우, 위쪽으로 이동.
                                if(type == 1) visited[dy][dx] = 0;
                                // 아래쪽으로 이동하여 만났을 경우, 왼쪽으로 이동.
                                else if(type == 2) visited[dy][dx] = 3;
                                break;
                            case '4': 
                                // 오른쪽으로 이동하여 만났을 경우, 아래쪽으로 이동.
                                if(type == 1) visited[dy][dx] = 2;
                                // 위쪽으로 이동하여 만났을 경우, 왼쪽으로 이동.
                                else if(type == 0) visited[dy][dx] = 3;
                                break;
                        }
                    }
                    // 일자형 파이프인 경우 가던 방향 그대로 진행.
                    else if("-|".indexOf(road) != -1)    visited[dy][dx] = visited[cy][cx];
                    
                    // +자형 파이프인 경우 진입한 경로를 제외한 나머지 경로값을 더한 값을 저장한다.
                    else if(road == '+'){
                        visited[dy][dx] = (6 - type) + 1; // 최소값을 4로 두어 기존 벡터값과의 중복을 피한다.
                    }
                    // EMPTY인 경우
                    else {
                        intoDir = type;
                        EMPTY_coord = new int[] {dy, dx}; break Search_EMPTY;
                    }
                    queue.offer(new int[] {dy, dx});
                } // 해당 칸 탐색 완료.
            }
            else{ // board[cy][cx]가 '+' 일 경우
                int enter_dir = (7 - visited[cy][cx]);
                for(int type = 0; type < 4; type++){
                    if(type == enter_dir) continue; // 진입하였던 방향인 경우 탐색하지 않음.
                    int dy = cy + dir[type][0], dx = cx + dir[type][1];
                    
                    // 아직 방문하지 않은 좌표일 경우
                    if(dy >= 0 && dx >= 0 && dy < R && dx < C && visited[dy][dx] == -1){
                        char road = board[dy][dx]; // 탐색할 칸에 어떤 파이프가 있는지 확인.

                        // 휘어져 있는 파이브인 경우
                        if("1234".indexOf(road) != -1){
                            // 이전 진행 방향에 따라 진행해야 할 방향을 정한다.
                            switch(road){
                                case '1': 
                                    // 위쪽으로 이동하여 만났을 경우, 오른쪽으로 이동.
                                    if(type == 0) visited[dy][dx] = 1; 
                                    // 왼쪽으로 이동하여 만났을 경우, 아래쪽으로 이동.
                                    else if(type == 3)  visited[dy][dx] = 2;
                                    break;
                                case '2': 
                                    // 아래쪽으로 이동하여 만났을 경우, 오른쪽으로 이동.
                                    if(type == 2)   visited[dy][dx] = 1;
                                    // 왼쪽으로 이동하여 만났을 경우, 위쪽으로 이동.
                                    else if(type == 3) visited[dy][dx] = 0;
                                    break;
                                case '3': 
                                    // 오른쪽으로 이동하여 만났을 경우, 위쪽으로 이동.
                                    if(type == 1) visited[dy][dx] = 0;
                                    // 아래쪽으로 이동하여 만났을 경우, 왼쪽으로 이동.
                                    else if(type == 2) visited[dy][dx] = 3;
                                    break;
                                case '4': 
                                    // 오른쪽으로 이동하여 만났을 경우, 아래쪽으로 이동.
                                    if(type == 1) visited[dy][dx] = 2;
                                    // 위쪽으로 이동하여 만났을 경우, 왼쪽으로 이동.
                                    else if(type == 0) visited[dy][dx] = 3;
                                    break;
                            }
                        }
                        // 일자형 파이프인 경우 가던 방향 그대로 진행.
                        else if("-|".indexOf(road) != -1)    visited[dy][dx] = visited[cy][cx]; 
                        
                        // +자형 파이프인 경우 진입한 경로를 제외한 나머지 경로값을 더한 값을 저장한다.
                        else if(road == '+'){
                            visited[dy][dx] = (6 - type) + 1; // 최소값을 4로 두어 기존 벡터값과의 중복을 피한다.
                        }
                        // EMPTY인 경우
                        else {
                            intoDir = type;
                            EMPTY_coord = new int[] {dy, dx}; break Search_EMPTY;
                        }
                        queue.offer(new int[] {dy, dx});
                    } // 해당 칸 탐색 완료.
                }
            }
        }
        if(isStart) start_dir = intoDir;
        else finish_dir = intoDir;

        return EMPTY_coord;
    }
}