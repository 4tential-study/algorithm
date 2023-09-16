import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B3085 {
    static char[][] board;
    static final int[][] dir = {{0, 1}, {1, 0}};
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(in.readLine()); // 보드의 크기

        board = new char[length][length];

        for(int i = 0; i < length; i++)
            board[i] = in.readLine().toCharArray();

        int answer = 1;
        for(int y = 0; y < length; y++){
            for(int x = 0; x < length; x++){
                for(int type = 0; type < dir.length; type++){
                    int dy = y + dir[type][0];
                    int dx = x + dir[type][1];
                    if(dy >= 0 && dx >= 0 && dy < length && dx < length){
                        swap(dy, dx, y, x);
                        int value = Math.max(counts(y, x), counts(dy, dx));
                        answer = (answer < value) ? value : answer;
                        swap(dy, dx, y, x);
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static int counts(int cy, int cx){
        int result = 1;
        int length = board.length;

        int count;
        // y축으로 탐색
        for(int y = 0; y < length; y++){
            char current = board[y][cx];
            count = 1;

            for(int search = y+1; search < length; search++){
                if(current != board[search][cx])    break;
                else count++;
            }
            result = (result < count) ? count : result;
        }

        // x축으로 탐색
        for(int x = 0; x < length; x++){
            char current = board[cy][x];
            count = 1;

            for(int search = x+1; search < length; search++){
                if(current != board[cy][search])    break;
                else count++;
            }
            result = (result < count) ? count : result;
        }

        return result;
    }

    static void swap(int ay, int ax, int by, int bx){
        char temp = board[ay][ax];
        board[ay][ax] = board[by][bx];
        board[by][bx] = temp;
    }
}