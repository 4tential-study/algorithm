

import java.util.*;
import java.io.*;

public class codetree {
    static BufferedReader in;
    static StringTokenizer st;
    static int n;
    static int m;
    static int[][] map;
    static boolean[][][] visited;
    static final int BLOCK = 5;
    static int[][] delta = {{-1,0},{0,-1},{0,1},{1,0}};
    static boolean[][][] storeV;
    static int[][] stores;
    static int max = 0;
    static int[][] bcs;
    static Queue<int[]> storeQ = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        String[] ins = in.readLine().split(" ");
        n = Integer.parseInt(ins[0]);
        m = Integer.parseInt(ins[1]);
        map = new int[n][n];
        stores = new int[m+1][2];
        bcs = new int[m+1][2];
        visited = new boolean[2][n][n];
        storeV = new boolean[m+1][n][n];


        for(int i=0 ; i < n ; i++){
            st = new StringTokenizer(in.readLine());
            for(int j=0; j < n ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for(int i=0 ; i < m ; i++){
            String[] inputs = in.readLine().split(" ");
            int idx = i+1;
            int y = Integer.parseInt(inputs[0])-1;
            int x = Integer.parseInt(inputs[1])-1;
//            visited= new boolean[2][n][n];
            visited[0] = new boolean[n][n];
            basecampBfs(idx, y, x);
            stores[idx][0] = y;
            stores[idx][1] = x;
            //각자 이동할 베이스캠프 찾고, 이동시키기.(시간을 다르게 주기)
//            storeQ.offer(new int[]{0, bcs[idx][0], bcs[idx][1], idx});
        }

        storeBfs();
        System.out.println(max);

    }

    public static void basecampBfs(int idx, int y, int x){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{idx,y,x,0});
        visited[0][y][x] = true;
        int min = 9999999;
        int[] minIdx = new int[]{9999999,9999999};
        while(!q.isEmpty()){
            int[] poll = q.poll();
            if(map[poll[1]][poll[2]] == 1 && !visited[1][poll[1]][poll[2]]){//BLOCK
                //1인경우, 최소거리중에서도 조건에 맞는 애로 변경
                if(min == poll[3]){
                    if(minIdx[0] == poll[1]){
                        if(minIdx[1] > poll[2]){
                            min = poll[3];
                            minIdx[0] = poll[1];
                            minIdx[1] = poll[2];
                        }
                    }
                    else if( minIdx[0] > poll[1]) {
                        min = poll[3];
                        minIdx[0] = poll[1];
                        minIdx[1] = poll[2];
                    }
                }
                else if(min > poll[3]){
                    min = poll[3];
                    minIdx = new int[]{poll[1], poll[2]};
                }

                continue;
            }
            int indx = poll[0];
            int dist = poll[3];
            for(int f = 0 ; f < 4 ; f++){
                int ay = poll[1] + delta[f][0];
                int ax = poll[2] + delta[f][1];
                if(inRange(ay,ax) && !visited[0][ay][ax] && !visited[1][ay][ax]){
                    visited[0][ay][ax] = true;
                    q.offer(new int[]{indx, ay, ax, dist+1});
                }
            }
        }
        bcs[idx] = minIdx;
        visited[1][minIdx[0]][minIdx[1]] = true;


    }

    public static boolean inRange(int y, int x){
        return y >= 0 && x >= 0 && y < n && x < n;
    }

    public static void storeBfs(){
        int i=1;

        while(true){
//            for(int i=1 ; i <= m ; i++){
               if(i <= m ) {
                   storeQ.offer(new int[]{i, bcs[i][0], bcs[i][1], i});
                   visited[1][bcs[i][0]][bcs[i][1]] = true;
               }
                int[] peek = storeQ.peek();
                if(peek == null) break;
                int[] poll = storeQ.poll(); //시간, 시작
                int time = poll[0];
                if(poll[1] ==  stores[poll[3]][0] && poll[2] == stores[poll[3]][1]){
                    map[poll[1]][poll[2]] = BLOCK;
                    System.out.println(poll[3]+" "+(time));
                    max = Math.max(max, time);
                    continue;
                }
                for(int f = 0 ; f < 4 ; f++){
                    int y = poll[1] + delta[f][0];
                    int x = poll[2] + delta[f][1];
                    if(inRange(y,x) && !storeV[poll[3]][y][x] && visited[1][y][x]){
                        if(map[y][x] == BLOCK) continue;
                        storeV[poll[3]][y][x] = true;
                        storeQ.offer(new int[]{time+1, y, x, poll[3]});
                        //시간을 따로 넣을 필요가 있나..?
                    }
                }
                i++;
            }


//        }
    }
}