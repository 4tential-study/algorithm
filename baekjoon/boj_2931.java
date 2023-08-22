
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class boj_2931 {
    static BufferedReader br;
    static StringTokenizer st;
    static char[][] map;
    static int[][] board;
    static int r;
    static int c;
    static int[][] deltas =  {{-1,0},{0,-1},{0,1},{1,0}};
    static int[] trace = new int[] {4,1,2,8};
    static HashMap<Integer, Character> hashmap = new HashMap<>();
    static Set<Character> set= new HashSet<>(Arrays.asList('|','-','+','1','2','3','4'));

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        r = Integer.parseInt(str[0]);
        c = Integer.parseInt(str[1]);
        board = new int[r][c];
        map = new char[r][c];
        //입력받기
        for(int i=0 ; i < r ; i++) {
            String s = br.readLine();
            for(int j=0 ; j < c ;j++) {
                map[i][j] = s.charAt(j);
            }
        }
        //탐색수행

        search();

        hashmap.put(12,'|');
        hashmap.put(3,'-');
        hashmap.put(15,'+');
        hashmap.put(5,'1');
        hashmap.put(9,'2');
        hashmap.put(10,'3');
        hashmap.put(6,'4');


        for(int i=0 ; i < r ; i++) {
            for(int j = 0 ; j < c ; j++) {
                if(map[i][j]=='.' && board[i][j] > 0 ) {
                    System.out.print((i+1) + " " + (j+1) + " "+ hashmap.get(board[i][j]));
                }
            }
        }
    }

    public static void search() {
        for(int i=0 ; i < r ; i++) {
            for(int j =0 ; j < c ; j++) {
                if(set.contains(map[i][j])) {
                    if(map[i][j] == '|') {
                        int y1 = i+1;
                        int y2 = i-1;
                        int x = j;
                        if(inRange(y1,x) && map[y1][x] == '.') {
                            board[y1][x] += 8; //1000
                        }
                        if(inRange(y2,x) && map[y2][x] == '.') {
                            board[y2][x] += 4; // 0100
                        }
                    } else if (map[i][j] == '-') {
                        int x1 = j+1;
                        int x2 = j-1;
                        int y = i;
                        if(inRange(y,x1) && map[y][x1] == '.') {
                            board[y][x1] += 2; //0010
                        }
                        if(inRange(y,x2) && map[y][x2] == '.') {
                            board[y][x2] += 1; //0001
                        }
                    } else if (map[i][j] == '+') {
                        for(int f=0 ; f < 4 ; f++) {
                            int y = i + deltas[f][0];
                            int x = j + deltas[f][1];
                            if(inRange(y,x) && map[y][x] == '.') {
                                board[y][x] += trace[f]; //
                            }
                        }
                    } else if (map[i][j] == '1') {
                        int y1 = i +1;
                        int x2 = j+1;

                        if (inRange(y1,j) && map[y1][j] == '.') {
                            board[y1][j] += 8; //1000
                        }
                        if(inRange(i,x2) && map[i][x2] == '.') {
                            board[i][x2] += 2; //0010
                        }
                    } else if (map[i][j] == '2') {
                        int y1 = i - 1;
                        int x2 = j + 1;
                        if (inRange(y1,j) && map[y1][j] == '.') {
                            board[y1][j] += 4; //
                        }
                        if (inRange(i,x2) && map[i][x2] == '.') {
                            board[i][x2] += 2; //
                        }

                    } else if (map[i][j] == '3') {
                        int y1 = i-1;
                        int x2 = j-1;
                        if (inRange(y1,j) && map[y1][j]=='.') {
                            board[y1][j] += 4;
                        }
                        if (inRange(i,x2) && map[i][x2]=='.') {
                            board[i][x2] += 1;  //0100
                        }
                    } else if (map[i][j] == '4') {
                        int y1 = i+1;
                        int x2 = j-1;
                        if (inRange(y1,j) && map[y1][j] =='.') {
                            board[y1][j] += 8; // 1000
                        }
                        if (inRange(i,x2) && map[i][x2] =='.') {
                            board[i][x2] += 1; // 1000
                        }
                    }
                }
            }
        }
    }

    public static boolean inRange(int y, int x) {
        return  y>=0 && y < r && x >= 0 && x < c ;
    }
}