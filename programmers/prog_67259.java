	import java.util.*;
	
	
public class prog_67259 {
	 static int[][] BOARD;
	    static int[][] delta = {{-1,0},{0,-1},{1,0},{0,1}};//상좌하우
	    static int n;
	    static int ans = Integer.MAX_VALUE;
	    static boolean[][] visited;
	    static int[][] array;
	public static void main(String[] args) {
		int[][] board = {{0, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 0, 1}, {1, 0, 0, 0}};
//		int[][] board = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
		System.out.println(solution(board));
	}
	

	    public static  int solution(int[][] board) {
	        BOARD = board;
	        n = board.length;    
	        visited = new boolean[n][n];
			array = new int[n][n];

		        bfs(0,0,-1,0);
	       
	        for(int i=0 ; i < n ; i++) {
		        System.out.println(Arrays.toString(array[i]));
	        }
	        System.out.println(array[n-1][n-1]);
	        return ans;
	    }
	    static int plus;
	    
	    public static void bfs(int i, int j, int dir , int v) {
			Queue<int[]> queue = new ArrayDeque<>();
			queue.add(new int[]{i,j,dir,v});
			visited[i][j] = true;
			while(!queue.isEmpty()) {
				int[] poll = queue.poll();
				if(poll[0] == n-1 && poll[1]== n-1){
					ans = Math.min(ans, poll[3]);
				}
				for(int f=0; f < 4 ;f ++) {
					int y = poll[0]+delta[f][0];
					int x = poll[1]+delta[f][1];
					int d = poll[2];
					int price = poll[3];

					if(inRange(y,x)  && BOARD[y][x] != 1) {

						if (dir==-1) {
							price += 100;
						}
						else if (d == f) {
							price += 100;
						} else {
							price += 600;
						}

						if(array[y][x] >= price || !visited[y][x]){
							visited[y][x] = true;
							array[y][x] = price;
							queue.add(new int[]{y,x,f,price});
						}
					}
				}
			}
		}

	    
	    public static boolean inRange(int y, int x){
	        return y >=0 && x >= 0 && y < n && x < n;
	    }
	
	
	   
}
