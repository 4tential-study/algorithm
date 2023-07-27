import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_10971 {
    static int[][] board;
    static int N;
    static boolean[] visited;
    static long shortestPath = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
       
        for(int y=0 ; y < N ; y++){
            st = new StringTokenizer(br.readLine());
            for(int x= 0; x< N ; x++ ) {
                board[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        
//        for(int i=0 ; i < N ;i++) {
         	visited = new boolean[N];
            visited[0] = true;
            bt(0, 0, 0);
//        }
        System.out.println(shortestPath);
       }

    public static void bt(int start , int now, long dist){
       if (allVisited()) {
    	   if(board[now][start]!=0) {
    		   shortestPath = Math.min(shortestPath, dist+board[now][start]);
    	   }
    	   return;
       }
       
       for(int i=1; i < N ; i++) {

           if(!visited[i] && board[now][i]!=0) {
    		   visited[i] = true;
               if (dist + board[now][i] < shortestPath) {
                   bt(start, i, dist + board[now][i]);
               }
    		   visited[i] = false;
    	   }
       }
    }
    
    public static boolean allVisited() {
    	for(boolean visit : visited) {
    		if (!visit) return false;
    	}
		return true;
    }


}
