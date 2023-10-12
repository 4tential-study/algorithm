import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;


public class boj_23031 {
	static BufferedReader in;
	static StringTokenizer st;
	static char[][] map;
	static ArrayList<int[]> switches = new ArrayList<>();
	static ArrayList<int[]> zq = new ArrayList<>();
	static final int ARI = -1;
	static final int Z = -2;
	static int[][] delta = {{-1,0},{0,-1},{1,0},{0,1}}; //상,좌,하,우
	static int[][] sdelta = {{-1,0},{0,-1},{1,0},{0,1},{1,1},{-1,-1},{-1,1},{1,-1}};
	static int n;
	static Queue<int[]> aq = new ArrayDeque<>();
	public static void main(String[] args) throws IOException{
		in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());
		String str = in.readLine();
		map = new char[n][n];
		
		for(int i=0 ; i < n ; i++) {
			String input = in.readLine();
			for(int j=0 ; j < n ;j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == 'Z') {
					zq.add(new int[] {i,j,2});
				}
			}
		}
	 
		aq.offer(new int[] {0,0,2});
		int idx = 0;
		while(idx < str.length()) {		
			char cmd = str.charAt(idx++);

			if(!ariMove(cmd)) {
				break;
			}
			
			if(!zombieMove()) {
				break;
			}
		
			if(idx == str.length()) System.out.println("Phew...");
		} 
		
	}
	
	public static int dirCmd(char cmd, int[] poll) {
		switch (cmd) {
		case 'F':
			int dir = poll[2];
			return dir;
		case 'L':
			int dirL = (poll[2]+1) % 4;
			return dirL;
		case 'R':
			int dirR = (poll[2]+3) % 4;
			return dirR;
		}
		return -1;
		
	}
	
	public static boolean inRange(int y, int x) {
		return y>=0 && x>=0 && y<n && x<n;
	}
	
	public static boolean isZombie(int y , int x) {
		for(int[] each : zq) {
			if(each[0] == y && each[1] == x) return true;
		}
		return false;
	}
	
	public static boolean ariMove(char cmd) {
		if(aq.size()==0) return false;
		int[] poll= aq.poll();
		int dir = dirCmd(cmd, poll);
		
		int y = poll[0];
		int x = poll[1];
		if(dir == poll[2]) {
			y = poll[0] + delta[dir][0];
			x = poll[1] + delta[dir][1];
		}

		if(!inRange(y,x)) {
			aq.offer(new int[] {poll[0],poll[1],dir});			
			return true;
		}
		
		if(map[y][x] == 'S') {				
			for(int f= 0 ;f < 8 ; f++) {
				int ly = y + sdelta[f][0];
				int lx = x + sdelta[f][1];
				if(inRange(ly,lx) && map[ly][lx] != 'S') map[ly][lx] = 's';
			}
		}
		
		
		if(map[y][x] == 's' || map[y][x] == 'S') {
			//밝을 때는 좀비 무조건 진행
			aq.offer(new int[] {y,x,dir});
			return true;
		}
		
		
		if(isZombie(y,x)){
			System.out.println("Aaaaaah!");
			return false;
		}
		
		aq.offer(new int[] {y,x,dir});
		return true;
	}
	
	public static boolean zombieMove() {
		int size=zq.size();
		if(size == 0) return false;
		for(int i=0 ; i < size ; i++) {
			int[] zpoll= zq.get(i);
			int zy = zpoll[0] + delta[zpoll[2]][0];
			int zx = zpoll[1] + delta[zpoll[2]][1];
			
			if(!inRange(zy,zx)) {
				//테두리일때는 이동없이 방향만 전환한다.
				int zdir = (zpoll[2] + 2 ) % 4;
				zq.set(i,new int[] {zpoll[0], zpoll[1], zdir});
				continue;
			}				
			
			int[] peek = aq.peek();
			if(peek == null) return false;
			if(peek[0] == zy && peek[1] == zx) { 
				if(map[zy][zx] != 'S' && map[zy][zx] != 's') {
					System.out.println("Aaaaaah!");
					return false;
				}
			}			
			zq.set(i,new int[] {zy,zx,zpoll[2]});
		}
		return true;
	}
}
