import java.util.*;

public class prog_92345 {
    static int[][] delta = {{-1,0},{0,-1},{1,0},{0,1}};
    static int n;
    static int m;
  
    static int max = 0;
    static int min = 999999999;
    
    public static void main(String[] args) {
    	int[][] board = new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
    	int[] aloc = new int[] {1,0};
    	int[] bloc = new int[] {1,2};
		solution(board, aloc ,bloc);
	}
    public static int solution(int[][] board, int[] aloc, int[] bloc) {
     
         n = board.length;
         m = board[0].length;
        
        Result r = dfs(board, aloc[0], aloc[1] , bloc[0], bloc[1], 0,0);
        System.out.println(r.move_cnt);
        return r.move_cnt;
		
    }
    public static Result dfs(int[][] board, int ay, int ax ,int by, int bx, int adept, int bdept) {
    	boolean win = false;
    	int winCnt = 5*5;
    	int loseCnt = adept+bdept;
    	//A가 턴인 경우 이게 실행되도록!
    	if(adept == bdept && board[ay][ax] == 1) {
    		for(int f=0 ; f < 4 ; f++){
                int aay = ay + delta[f][0];
                int aax = ax + delta[f][1];
                if(inRange(aay,aax) && board[aay][aax] != 0){ 
                	board[aay][aax] = 0;
                	Result r = dfs(board, aay, aax, by, bx, adept+1, bdept);
                    
                    win |= !r.win;
                    if(r.win) loseCnt = Math.max(loseCnt, r.move_cnt);
                    else winCnt = Math.min(winCnt, r.move_cnt);
                    board[aay][aax] = 1; //
                }
            }
    	}
    	//B가 턴인 경우 이게 실행되도록!
    	else if(adept > bdept && board[by][bx] == 1) {
    		for(int f=0 ; f < 4 ; f++){
                int bby = by + delta[f][0];
                int bbx = bx + delta[f][1];
                if(inRange(bby,bbx) && board[bby][bbx] != 0){ 
                	board[bby][bbx] = 0;
                	Result r = dfs(board, ay, ax, bby, bbx, adept, bdept+1);             
                    win |= !r.win; //하나라도 true이면 승리
                    if(r.win) loseCnt = Math.max(loseCnt, r.move_cnt);
                    else winCnt = Math.min(winCnt, r.move_cnt);
                    board[bby][bbx] = 1; //이거... 왜 다시 채우는??
                }
            }
    	}
    	
    	return new Result(win, win ? winCnt : loseCnt);    	
    }
    public static boolean inRange(int y, int x){
        return y >= 0 && x>=0 && y < n && x < m;
    }
    
    static class Result {
        boolean win;
        int move_cnt;
        
        public Result(boolean win, int move_cnt){
            this.win = win;
            this.move_cnt = move_cnt;
        }
    }
}