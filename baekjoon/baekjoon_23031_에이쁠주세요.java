import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static class Ahri{
		int y;
		int x;
		int dir;
		public Ahri(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
		@Override
		public String toString() {
			return "Ahri [y=" + y + ", x=" + x + ", dir=" + dir + "]";
		}
				
	}
	static class Zombie{
		int y;
		int x;
		int dir;
		@Override
		public String toString() {
			return "Zombie [y=" + y + ", x=" + x + ", dir=" + dir + "]";
		}
		public Zombie(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}
	
	static int N;
	static char[] moveList;
	static char[][] map;
	
	// 상 우 하 좌 우상 우하 좌하 좌상
	static int dy[] = {-1, 0, 1, 0, -1, 1, 1, -1};
	static int dx[] = {0, 1, 0, -1, 1, 1, -1, -1};
	
	static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;	
	// 꺼진 스위치는 기본값 S, 켜진 스위치는 L로 맵에 적용
	static final char LIGHTOFF = 'S', LIGHTON = 'L';
	
	// 좀비들의 위치와 방향 리스트
	static List<Zombie> zombies = new ArrayList();
	static Ahri ahri = new Ahri(0, 0, DOWN);
	
	static boolean result = true;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		moveList = in.readLine().toCharArray();
		map = new char[N][N];
		
		for(int i=0; i<N; i++) {
			String str = in.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j]=='Z') {
					zombies.add(new Zombie(i, j, DOWN));
					map[i][j] = 'O';
				}
			}
		}
		
		for(int i=0; i<moveList.length; i++) {
			// 아리 이동
			ahriMove(moveList[i]);
			
			// 스위치 존재 여부 확인
			if(map[ahri.y][ahri.x]==LIGHTOFF) {
				// 스위치가 존재할 경우
				map[ahri.y][ahri.x] = LIGHTON;
			}
			
			// 좀비 이동
			zombieMove();
			
			if(!result) break;	
		}
		
		
		if(result)
			System.out.println("Phew...");
		else
			System.out.println("Aaaaaah!");
	}
	static boolean inRange(int y, int x) {
		return 0<=y && y<N && 0<=x && x<N;
	}
	static boolean isPassout(Zombie zombie){
		// 스위치가 켜져있는 구역이라면 continue
		// 꺼져있는 구역이라면 return Aaah!
		
		if(map[zombie.y][zombie.x]!='L') {
			for(int d=0; d<dy.length; d++) {
				// 팔방 탐색으로 불이 켜져있는지 확인
				int ny = zombie.y + dy[d];
				int nx = zombie.x + dx[d];
				
				if(inRange(ny, nx) && map[ny][nx]==LIGHTON) 
					return false;				
			}
			return true;
		}
		return false;
	}
	static void ahriMove(char move) {
		// 아리 이동
		int ny=0;
		int nx=0;
		switch(move) {
		case 'F':
			ny = ahri.y+dy[ahri.dir];
			nx = ahri.x+dx[ahri.dir];
			if(inRange(ny, nx)) {
				ahri.y = ny;
				ahri.x = nx;
			}
			break;
		case 'R':
			switch(ahri.dir) {
			case UP:
				ahri.dir = RIGHT;
				break;
			case RIGHT:
				ahri.dir = DOWN;
				break;
			case DOWN:
				ahri.dir = LEFT;
				break;
			case LEFT:
				ahri.dir = UP;
				break;
			}
			break;
		case 'L':
			switch(ahri.dir) {
			case UP:
				ahri.dir = LEFT;
				break;
			case RIGHT:
				ahri.dir = UP;
				break;
			case DOWN:
				ahri.dir = RIGHT;
				break;
			case LEFT:
				ahri.dir = DOWN;
				break;
			}
			break;
		}
	}
	static void zombieMove() {
		for(Zombie zombie : zombies) {
			// 아리와 같은 위치인지 확인
			if(ahri.y == zombie.y
					&& ahri.x == zombie.x) {
				if(isPassout(zombie)) {
					result = false;
					return;
				}
			}
			
			// 	좀비 이동
			int ny = zombie.y+dy[zombie.dir];
			int nx = zombie.x+dx[zombie.dir];
			
			if(inRange(ny, nx)) {
				// 이동할 위치가 맵 내부라면 방향대로 이동
				zombie.y = ny;
				zombie.x = nx;
			}
			else {
				// 이동할 위치가 맵 외부라면 방향 변경 및 위치 고정
				if(zombie.dir==UP) zombie.dir = DOWN;
				else zombie.dir = UP;
			}
			
			// 아리와 같은 위치인지 확인
			if(ahri.y == zombie.y
					&& ahri.x == zombie.x) {
				if(isPassout(zombie)) {
					result = false;
					
				}
			}
		}	
	}
}