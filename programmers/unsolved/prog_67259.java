	import java.util.*;
	
	
public class prog_67259 {
	 static int[][] BOARD;
	    static int[][] delta = {{-1,0},{0,-1},{1,0},{0,1}};//상좌하우
	    static int n;
	    static int ans = Integer.MAX_VALUE;
	    static boolean[][] visited;
	    static int[][][] cost;
	public static void main(String[] args) {
		int[][] board = {{0, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 0, 1}, {1, 0, 0, 0}};
//		int[][] board = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
		System.out.println(solution(board));
	}
	

	    public static  int solution(int[][] board) {
	        BOARD = board;
	        n = board.length;    
	        visited = new boolean[n][n];
	        cost = new int[n][n][4];
	        bfs(0,0,0);  
	       // 0, 1에서 우측으로 진행하는 경우
	       
	        ans = Integer.MAX_VALUE;
	        for(int i = 0; i < 4; i++) {
	            if(cost[n-1][n-1][i] != 0 && cost[n-1][n-1][i] < ans){
	                ans = cost[n-1][n-1][i];
	            }
	        }
	        
	        return ans;
	    }
	    
	    static int plus;
	    
	    public static void bfs(int i, int j, int dir) {
			Queue<int[]> queue = new ArrayDeque<>();
			
			 if(BOARD[0][1] == 0){
		            queue.offer(new int[] {0, 1, 1});
		            cost[0][1][1] = 100;
		        }
		        
		        // 1, 0,에서 아래쪽으로 진행하는 경우
			 else if(BOARD[1][0] == 0){
		            queue.offer(new int[] {1, 0, 0});
		            cost[1][0][0] = 100;
		        }
		        queue.add(new int[]{i,j,dir});
			visited[i][j] = true;
			while(!queue.isEmpty()) {
				int[] poll = queue.poll();
				
				for(int f=0; f < 4 ;f ++) {
					int y = poll[0]+delta[f][0];
					int x = poll[1]+delta[f][1];
					int d = poll[2];
					
					if(inRange(y,x) && !visited[y][x] && BOARD[y][x] != 1) {
						int p = cost[y][x][d] + 100;
						visited[y][x] = true;
						if(dir == f || (dir+2) % 4 == f){
		                	p += 100;	                	             
		                }
		                else if(dir % 2 != f % 2){
		                	 p += 500;	                	
		                }
		                
						if(cost[y][x][i] == 0 || cost[y][x][f] > p) {
							cost[y][x][f] = p;
							queue.offer(new int[] {y,x,f});
						}
					}
				}
			}
		}
	   
	    
	    public static boolean inRange(int y, int x){
	        return y >=0 && x >= 0 && y < n && x < n;
	    }
	
	
	   
}
