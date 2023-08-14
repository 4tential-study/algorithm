import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_7569 {
    static BufferedReader br;
    static int[][][] board;
    static StringTokenizer st;
    static boolean[][][] visited;
    static int[][][] array;
    static int[] dz= {-1,1};
    static int[] dy= {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    
    static int h;
    static int m;
    static int n;
    
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

         n = Integer.parseInt(s[0]);
         m = Integer.parseInt(s[1]);
         h = Integer.parseInt(s[2]);
        board = new int[h][m][n];
        visited = new boolean[h][m][n];
        array = new int[h][m][n];
        ArrayList<int[]> start = new ArrayList<>();

        int zero=0;
        for(int z=0 ; z < h ; z++){
            for(int y=0 ; y < m ; y++){
                st = new StringTokenizer(br.readLine());
                for(int x= 0 ; x < n ; x ++){
                    board[z][y][x] = Integer.parseInt(st.nextToken());
                    if(board[z][y][x] == 0) zero++;
                    if(board[z][y][x] == 1) start.add(new int[] {z,y,x});
                }
            }
        }
        if(zero == 0) {
        	System.out.println(0);
        	return;
        }

       bfs(start);
       for(int[] each : start){
           board[each[0]][each[1]][each[2]] = 1;
       }
        int max = 0;
        for(int z=0 ; z < h ; z++){
            for(int y=0 ; y < m ; y++){
                for(int x= 0 ; x < n ; x ++){
                	if(board[z][y][x]== 0) {
                		System.out.println(-1);
                		return;
                	}
                	else max = Math.max(board[z][y][x], max);
                }
            }
        }
        System.out.println(max);
    }
    
    public static void bfs(ArrayList<int[]> start) {
    	Queue<int[]> queue = new LinkedList<>();
    	 for(int[] each : start){
             queue.add(each);
             visited[each[0]][each[1]][each[2]] = true;
             board[each[0]][each[1]][each[2]] = 0;
         }
    	 
    	while(!queue.isEmpty()) {
    		int[] poll = queue.poll();
    		int z = poll[0];
    		int y = poll[1];
    		int x = poll[2];
    			for(int i=0 ; i < 4 ; i++) {
    				int ay = poll[1]+dy[i];
    				int ax = poll[2]+dx[i];
    				
    				if(z>=0 && z < h && ay >=0 && ay < m && ax >=0 && ax < n) {
    					
    					if(!visited[z][ay][ax] && board[z][ay][ax]==0) {   					
	    					queue.add(new int[] {z,ay,ax});
	    					visited[z][ay][ax] = true;
	    					board[z][ay][ax] = board[poll[0]][poll[1]][poll[2]]+1;
    				}
    			}   		
    		}   			
    			for(int i=0 ; i < 2 ; i++) {
    				int az = poll[0] + dz[i];
    				if(az>=0 && az < h && y >=0 && y < m && x >=0 && x < n) {
    					
    					if(!visited[az][y][x] && board[az][y][x]==0) {   					
	    					queue.add(new int[] {az,y,x});
	    					visited[az][y][x] = true;
	    					board[az][y][x] = board[poll[0]][poll[1]][poll[2]]+1;
    				}
    			}
    		}			
    	}
    }
}
