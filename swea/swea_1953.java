import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_1953 {
    static BufferedReader br;
    static StringTokenizer st;
    static int[][] map;
    static int[][] array;
    static int m;
    static int n;
    static boolean[][] visited;
    static int cnt;
    static StringBuilder sb;
    static int[][] dir;
    
    static int[][][] deltas ={
    		{{-1,0},{0,-1},{1,0},{0,1}}, //1
            {{-1,0},{0,0},{1,0},{0,0}}, //상하
            {{0,0}, {0,-1}, {0,0},{0,1}},//좌우
            {{-1,0},{0,0},{0,0},{0,1}},//상,우
            {{0,0},{0,0},{1,0},{0,1}},//하,우
            {{0,0},{0,-1},{1,0},{0,0}}, //하,좌
            {{-1,0},{0,-1},{0,0},{0,0}},//상좌
    };

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
       
        int t = Integer.parseInt(br.readLine());
        for(int tc = 1 ; tc <= t ; tc++){
        	cnt= 0;
        	 sb = new StringBuilder();
        	sb.append("#" + tc + " ");
            String[] s = br.readLine().split(" ");
             n = Integer.parseInt(s[0]);
             m = Integer.parseInt(s[1]);
            map = new int[n][m];
            array= new int[n][m];
            dir = new int[n][m];
            int r = Integer.parseInt(s[2]);
            int c = Integer.parseInt(s[3]);
            int l = Integer.parseInt(s[4]);
            
            visited = new boolean[n][m];
            for(int i=0 ; i < n ; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < m ; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            //입력받기

            bfs(r,c,l);
            //탐색 수행
            for(int i=0 ; i < n ; i++) {
            	for(int j =0 ; j < m ; j++) {
            		if(array[i][j] <= l && array[i][j] >= 1) cnt++;
            	}
            }
            //방문한개수 카운트
               
            sb.append(cnt);
            System.out.println(sb.toString());
        }
        
    }

    public static void bfs(int r, int c, int hour){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r,c});
        visited[r][c] = true; 
        array[r][c] = 1; //방문한 시간 저장
        
        while(!queue.isEmpty()){
            int[] poll = queue.poll(); 
            
            int z = map[poll[0]][poll[1]]-1;
            for(int i=0 ; i < 4 ; i++) {
            	int y = poll[0] + deltas[z][i][0];
            	int x = poll[1] + deltas[z][i][1];
            	if(y==poll[0] && x == poll[1]) continue;
            	if(y >= 0  && y < n && x >= 0 && x < m && map[y][x]!=0 && !visited[y][x]) {
            		if(isconnect(y,x, deltas[z][i])) { //이어지는 모양인지 확인
	            		visited[y][x] = true;
	            		queue.add(new int[] {y,x});
	            		array[y][x] = array[poll[0]][poll[1]] +1;
            		}
            	}
            }
        }
    }
    
    public static boolean isconnect(int y2, int x2, int[] dir ) {
    	int path2 = map[y2][x2]-1;
    	int[] expectTo = new int[] {dir[0] * -1, dir[1] * -1};
    	for(int[] each : deltas[path2]) {
    		if(each[0]==expectTo[0] && each[1] ==expectTo[1]) return true;
    	}
    	return false;
    }
    

}
