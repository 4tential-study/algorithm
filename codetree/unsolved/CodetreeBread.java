package unsolved;

import java.util.*;
import java.io.*;

public class CodetreeBread {
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
        visited = new boolean[3][n][n]; // 1 : BLOCK된 애를 피하기 위함 , 0: 단순중복방문을 피하기 위함
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
            stores[idx][0] = y;
            stores[idx][1] = x;
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
        if(idx == 4) {
        	System.out.println("$$");
        }
        while(!q.isEmpty()){
            int[] poll = q.poll();
     
            if(map[poll[1]][poll[2]] == 1 && !visited[1][poll[1]][poll[2]] && !visited[2][poll[1]][poll[2]]){
            //  1인 경우, 정렬해서 인덱스 저장하기	
                if(min == poll[3]){
                    if(minIdx[0] == poll[1]){//y좌표가 같은경우
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
                if(ay==2 && ax==2) {
                	System.out.println("??");
                }
                if(inRange(ay,ax) && !visited[0][ay][ax] && !visited[1][ay][ax] && !visited[2][y][x]){
                	if(map[ay][ax] == BLOCK) continue;
                    visited[0][ay][ax] = true;
                    q.offer(new int[]{indx, ay, ax, dist+1});
                }
            }
        }
        bcs[idx] = minIdx;
        visited[2][minIdx[0]][minIdx[1]] = true;

        // 2: 중복되는 최소 인덱스를 구하는것을 피하기 위함
        return;
    }

    public static boolean inRange(int y, int x){
        return y >= 0 && x >= 0 && y < n && x < n;
    }

    public static void storeBfs(){
        int i=0;

        while(true){
        	
               if(++i <= m ) {
                   visited[0] = new boolean[n][n];
            	   basecampBfs(i,stores[i][0], stores[i][1]); 
                   storeQ.offer(new int[]{i, bcs[i][0], bcs[i][1], i});//시간,y,x,인덱스
               }
                int[] peek = storeQ.peek(); //storeQ에 2,2가 이미 있음
                if(peek == null) break;
                int[] poll = storeQ.poll();
                if(poll[3] == 3 && poll[1] == 2 && poll[2] == 2) {
                	System.out.println("!!");
                }
                if( visited[1][poll[1]][poll[2]]) continue;
                visited[1][bcs[poll[3]][0]][bcs[poll[3]][1]] = true;
                int time = poll[0];
                if(poll[1] == stores[poll[3]][0] && poll[2] == stores[poll[3]][1] ){
                    map[poll[1]][poll[2]] = BLOCK;
                    visited[1][poll[1]][poll[2]] = true; // 편의점 방문 
                    System.out.println(poll[3]+" "+(time));
                    max = Math.max(max, time);
                    continue;
                }
                for(int f = 0 ; f < 4 ; f++){
                    int y = poll[1] + delta[f][0];
                    int x = poll[2] + delta[f][1];
                    if(inRange(y,x) && !storeV[poll[3]][y][x] && !visited[1][y][x] ){
                        if(map[y][x] == BLOCK) continue;
                        storeV[poll[3]][y][x] = true;
                        storeQ.offer(new int[]{time+1, y, x, poll[3]});
                    }
                }
                
            }


    }
}