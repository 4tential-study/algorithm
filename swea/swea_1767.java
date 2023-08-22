

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class swea_1767 {
    static int T;
    static BufferedReader br;
    static StringTokenizer st;
    static int[][] map;
    static int n;
    static boolean[][] visited;
    static int answer;
    static final int ROAD = 5;
    static int INF = 999999;
    static ArrayList<Integer> ans = new ArrayList<>();
    static int min=99999999 ;
    static int[][] deltas = {{-1,0},{0,-1},{1,0},{0,1}};
    
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        visited = new boolean[n][n];

        for(int i=0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0 ; j < n ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1){
                    if (i == 0 || j == 0) continue;
                    search(i,j);
                }
            }
        }
    
        for(int i=0 ; i < n ; i++) {
        	for(int j=0 ; j < n ; j++) {
        		System.out.print(map[i][j]);
 
        	}
        	System.out.println();
        }
        
        for(Integer each : ans) {
        	System.out.println(": "+ each);
        	answer += each;
        }
        
        System.out.println(answer);
    }
    
    public static void search(int y, int x) {
    	min = 9999999;
    	for(int i=0 ; i < 4 ; i++) {
    		int ay = y + deltas[i][0];
    		int ax = x + deltas[i][1];
    		
    		if (isPromise(ay,ax)) {
    			int[] dir = deltas[i];
    			int count = 0;
    			while(true) {
    				if(ay == 0 || ay == n || ax == 0 || ax == n) {
    					min = Math.min(min, count);
    					System.out.println(min);
    					ans.add(min);
    					break;
    				}
    				if(map[ay][ax] != 0) break;
    				if(visited[ay][ax]) break;
    				ay += dir[0];
    				ax -= dir[1];
    				
    				count++;
    			}   			
    		}
    	}
    }
    	
	public static boolean isPromise(int y, int x) {
		return y >= 0 && y < n && x >= 0 && x < n && map[y][x] == 0 ;
	}
    

    
}






