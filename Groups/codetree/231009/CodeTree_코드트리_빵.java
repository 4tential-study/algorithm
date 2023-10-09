// 문제 푸는 중...
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class CodeTree_코드트리_빵 {
    // 상, 좌, 우, 하 : 문제에 제시된 방향의 우선 순위
    private static final int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    
    private static boolean[][] cantGo; // 해당 좌표로 갈 수 없는지에 대한 배열
    private static final int[] EMPTY = {-1, -1}; // 격자 밖
    private static List<int[]> people; // 사람들의 현재 좌표
    private static List<int[]> stores; // 각 사람들의 원하는 편의점의 좌표
    private static List<int[]> baseCampList; 
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 격자의 크기 (N*N)
        int M = Integer.parseInt(st.nextToken()); // 빵을 구하는 인원 수
        
        int[][] board = new int[N][N];
        cantGo = new boolean[N][N];
        for(int y = 0; y < N; y++) {
            st = new StringTokenizer(in.readLine());
            for(int x = 0; x < N; x++) {
                board[y][x] = Integer.parseInt(st.nextToken());
                if(board[y][x] == 1)	baseCampList.add(new int[] {y, x});
            }
        }
        
        people = new ArrayList<>();	stores = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            stores.add(new int[] {y-1, x-1});
            people.add(EMPTY); // n번째 사람은 격자 밖으로 초기화
        }
        
        
        int minute = 0;
        while(true) {
            if(end(M, stores)) break; // 각자가 원하는 편의점으로 모두 도착했다면 break
            simulate(minute);
            minute++;
        }
        
        System.out.println(minute);
    }
    
    static void simulate(int minute) {
    	int M = people.size();
    	
    	int min = Math.min(M, minute);
    	for(int i = 0; i < min; i++) {
    		int[] coord = people.get(i);
    		// 이미 도착한 사람이라면 continue
    		if(coord[0] == stores.get(i)[0] && coord[1] == stores.get(i)[1]) continue;
    		
    		// 움직일 시간이 되었는데 아직 출발하지 않은 사람이라면 베이스캠프로 배치하기.
    		else if(coord[0] == -1) {
    			int[][] movement = BFS(cantGo.length, coord);
    			
    			int[] start_coord = new int[] {99, 99};
    			for(int camp = 0; camp < baseCampList.size(); camp++) {
    				// 캠프의 좌표를 생성.
    				int cy = baseCampList.get(camp)[0];
    				int cx = baseCampList.get(camp)[1];
    				if(cantGo[cy][cx]) continue; // 해당 캠프로 갈 수 없는 경우 continue
    				
    				int distance = movement[cy][cx];
    				
    			}
    		}
    	}
    }
    
    
    static int[][] BFS(int N, int[] dest) {
        int[][] board = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(dest); visited[dest[0]][dest[1]] = true;
        
        int distance = 1;
        while(!queue.isEmpty()) {
        	int size = queue.size();
        	for(int i = 0; i < size; i++) {
        		int cy =  queue.peek()[0], cx = queue.poll()[1];
        		for(int type = 0; type < 4; type++) {
        			int dy = cy + dir[type][0];
        			int dx = cx + dir[type][1];
        			
        			// 범위 안에 있으며, visited하지 않은 곳이라면 탐색.
        			if(dy >= 0 && dx >= 0 && dy < N && dx < N
        			&& !visited[dy][dx]) {
        				visited[dy][dx] = true;
        				
        				// 갈 수 없는 곳이라면 int의 최대치로 값을 할당함.
        				if(cantGo[dy][dx]) board[dy][dx] = Integer.MAX_VALUE;
        				else board[dy][dx] = distance;
        			}
        		}
        	}
        	distance++;
        }
        return board;
    }
    
    static boolean end(int M, List<int[]> stores) {
        for(int i = 0; i < M; i++) {
            int py = people.get(i)[0], px = people.get(i)[1];
            int sy = stores.get(i)[0], sx = stores.get(i)[1];
            
            if(py != sy || px != sx)    return false;
        }
        
        return true;
    }
}