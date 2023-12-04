import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class boj_1721 {
    static BufferedReader br;
    //상우하좌->0123
    static int[][] board;
    static int n;
    static boolean[] visited;
    static int[][] map;
    static ArrayList<Integer> edge = new ArrayList<>();
    static ArrayList<Integer> side = new ArrayList<>();
    static ArrayList<Integer> middle = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n*n+1][5];
        visited= new boolean[n*n+1];
        map = new int[n][n];
        for(int i=0 ; i < n ; i++) {
            int count =0;
            String[] str = br.readLine().split(" ");
            int idx = Integer.parseInt(str[0]);
            for(int j = 1 ; j < 5 ; j++){
                board[idx][j] = Integer.parseInt(str[j]);
                if( board[idx][j] == 0 ) count++;
            }
            if(count == 2) edge.add(idx);
            if(count == 1) side.add(idx);
            if(count == 0) middle.add(idx);
        }

        backtracking(board, 0,0);
        for(int i=0 ; i <  map.length ; i++){
            System.out.println(Arrays.toString(map[i]));
        }

    }
    public static void backtracking(int[][] board, int y ,int x){
        if(y==n-1 && x==n-1){
            return;
        }

        if(isSide(y,x)){
            if(isEdge(y,x)){
                for(Integer idx : edge){
                    if(!visited[idx]) {
                        map[y][x] = idx;
                        visited[idx] = true;
                        if(inRange(y,x-1)) backtracking(map, y, x-1);
                        if(inRange(y,x+1)) backtracking(map, y, x+1);
                        if(inRange(y+1,x)) backtracking(map, y+1, x);
                        if(inRange(y-1,x)) backtracking(map, y-1, x);
                        visited[idx] = false;
                    }
                }
            }else{
                for(Integer idx : side){
                    if(!visited[idx]) {
                        map[y][x] = idx;
                        visited[idx] = true;
                        if(inRange(y,x-1)) backtracking(map, y, x-1);
                        if(inRange(y,x+1)) backtracking(map, y, x+1);
                        if(inRange(y+1,x)) backtracking(map, y+1, x);
                        if(inRange(y-1,x)) backtracking(map, y-1, x);
                        visited[idx] = false;
                    }
                }
            }
        }else{
            for(Integer idx : middle){
                if(!visited[idx]) {
                    map[y][x] = idx;
                    visited[idx] = true;
                    if(inRange(y,x-1)) backtracking(map, y, x-1);
                    if(inRange(y,x+1)) backtracking(map, y, x+1);
                    if(inRange(y+1,x)) backtracking(map, y+1, x);
                    if(inRange(y-1,x)) backtracking(map, y-1, x);
                    visited[idx] = false;
                }
            }
        }


    }

    public static boolean inRange(int y, int x){
        return y>=0 && x>=0 && y<n && x <n;
    }

    public static boolean isEdge(int y, int x){
        if(y==0 && x==0 || y == n-1 && x== n-1  || y ==0 && x ==n-1 || y == n-1 && x == 0 ) return true;
        else return false;
    }

    public static boolean isSide(int y, int x){
        if(y==0 || y==n-1 || x==0 || x==n-1 ) return true;
        else return false;
    }




}
