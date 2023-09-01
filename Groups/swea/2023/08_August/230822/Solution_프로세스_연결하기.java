import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
 
public class Solution_프로세스_연결하기 {
    static final int FLAT = 0, CORE = 1, WIRE = 2;
    static int length, max_core, minWire_length;
    static int[][] board, dir = {{-1, 0},{0, 1},{1, 0},{0, -1}}; // 상 우 하 좌
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            sb.append("#").append(test_case).append(" ");
             
            // 현재 테스트 케이스의 board 길이
            length = Integer.parseInt(in.readLine()); 
             
            // 현재 테스트케이스에서 core의 좌표들을 담을 list
            List<int[] > coreList = new ArrayList<>();
            board = new int[length][length];
            max_core = 0;
            //board 입력 받기
            for(int y = 0; y < length; y++) {
                String[] input = in.readLine().split(" ");
                for(int x = 0; x < length; x++) {
                    board[y][x] = Integer.parseInt(input[x]);
                    if(board[y][x] == CORE) {
                        // 가장 자리에 있는 코어는 탐색을 하지 않아도
                        // 이미 Power를 공급 받으므로 max_core에 추가하며 탐색할 코어에서 제외한다.
                        if(y == 0 || y == length-1 || x == 0 || x == length-1) max_core++;
                        else coreList.add(new int[] {y, x});
                    }
                }
            } // 입력 완료
             
            // 전원을 연결한 최대 코어 개수, 최대 코어 개수를 만족할 때 사용하는 최소 전선 길이
            minWire_length = Integer.MAX_VALUE;
             
            solve(board, coreList, 0, max_core, 0);
             
            sb.append(minWire_length).append("\n");
        }
        System.out.println(sb);
    }
    static void solve(int[][] Board, List<int[]> coreList, int idx, int core, int wire_length) {
        if(idx == coreList.size()) {
            if(core > max_core) {
                max_core = core;    minWire_length = wire_length;
            }
            else if(core == max_core) minWire_length = Math.min(minWire_length, wire_length);
            return;
        }
         
        for(int i = idx; i < coreList.size(); i++) {
            int cy = coreList.get(i)[0], cx = coreList.get(i)[1];
            for(int type = 0; type < 4; type++) {
                List<int[]> WireList = new ArrayList<>();
                int dy = cy, dx = cx;
                while(true) {
                    dy += dir[type][0]; dx += dir[type][1];
                    // 현재 Power가 있는 곳에 도착했다면 여태까지 이동한 곳에 전선을 설치.
                    if(dy == -1 || dx == -1 || dy == length || dx == length) {
                        for(int wire_idx = 0; wire_idx < WireList.size(); wire_idx++) {
                            int y = WireList.get(wire_idx)[0], x = WireList.get(wire_idx)[1];
                            Board[y][x] = WIRE;
                        }
                        solve(Board, coreList, i+1, core+1, wire_length+WireList.size());
                        // 다른 방향으로 재탐색을 위해 설치하였던 전선을 다시 해체함.
                        for(int wire_idx = 0; wire_idx < WireList.size(); wire_idx++) {
                            int y = WireList.get(wire_idx)[0], x = WireList.get(wire_idx)[1];
                            Board[y][x] = FLAT;
                        }
                        break;
                    }
                    // 해당 좌표에서 다른 코어나 전선을 만났으면 다른 방향으로 탐색.
                    if(Board[dy][dx] != FLAT) break;
                    WireList.add(new int[] {dy, dx});
                }
            }
        }
    }
}