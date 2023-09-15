import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Arrays;

class Solution {
    
	static class Vertex{
		int y;
		int x;
		int lastD;
		int cost;
		public Vertex(int y, int x, int lastD, int cost) {
			super();
			this.y = y;
			this.x = x;
			this.lastD = lastD;
			this.cost = cost;
		}
		
	}
	
	// 우 하 좌 상
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	static int[][][] costMap;
	
    
    public int solution(int[][] board) {		
        int answer = Integer.MAX_VALUE;
        int N = board.length;
        
        
        Queue<Vertex> q = new ArrayDeque<>();
        q.offer(new Vertex(0,0,-1,0));
        
        costMap = new int[N][N][4];
        
        while(!q.isEmpty()) {
        	Vertex cur = q.poll();
        	
        	for(int d=0; d<dy.length; d++) {
        		
        		int ny = cur.y + dy[d];
        		int nx = cur.x + dx[d];
        		if(ny==0 && nx==0) continue;
        		
    			int cost = cur.cost + 100;
    			if(isCorner(cur.lastD, d)) cost += 500;
        		
        		if(canGo(ny,nx,d, board, cost)) {
        			q.offer(new Vertex(ny,nx,d,cost));
        	        costMap[ny][nx][d] = cost;
        		}
        	}
        }
        
        for(int i=0; i<dy.length; i++) {
        	if(costMap[N-1][N-1][i]!=0) {
        		answer = Math.min(answer, costMap[N-1][N-1][i]);
        	}
        }
        
        return answer;
    }
	static boolean isCorner(int curD, int nextD) {
		
		switch(curD) {
		case 0:
		case 2:
			if(nextD==1 || nextD==3) return true; 
			break;
		case 1:
		case 3:
			if(nextD==0 || nextD==2) return true;
			break;
		}
		return false;
	}
	static boolean canGo(int y, int x, int d, int[][] board, int cost) {
		int h = board.length;
		int w = board[0].length;
		return (0<=y && y<h && 0<=x && x<w && board[y][x]==0 && (cost <= costMap[y][x][d] || costMap[y][x][d]==0));
	}
}