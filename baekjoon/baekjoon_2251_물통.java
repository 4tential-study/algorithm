package week1016;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class baekjoon_2251_물통 {
	
	static int A, B, C;
	static StringBuilder sb = new StringBuilder();
	static TreeSet<Integer> set = new TreeSet<>();
	static boolean[][][] visited;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
		visited = new boolean[A+1][B+1][C+1];
		
		solve(0, 0, C);
		Iterator<Integer> iter = set.iterator();
		while(iter.hasNext()) {
			sb.append(iter.next()+" ");
		}
		
		System.out.println(sb.toString());
	}
	
	static void solve(int a, int b, int c) {
		if(a==0) {
			set.add(c);
		}
		
		if(c!=0) {
			
			if(canMoveZero(c, a, A) && !visited[a+c][b][0]) {
				visited[a+c][b][0] = true;
				solve(a+c, b, 0);
			}if(canMoveFull(c, a, A) && !visited[A][b][c-(A-a)]) {
				visited[A][b][c-(A-a)] = true;
				solve(A, b, c-(A-a));
			}
			
			if(canMoveZero(c, b, B) && !visited[a][b+c][0]) {
				visited[a][b+c][0] = true;
				solve(a, b+c, 0);
			}if(canMoveFull(c, b, B) && !visited[a][B][c-(B-b)]) {
				visited[a][B][c-(B-b)] = true;
				solve(a, B, c-(B-b));
			}
		}
		
		if(b!=0) {
			if(canMoveZero(b, a, A) && !visited[a+b][0][c]) {
				visited[a+b][0][c] = true;
				solve(a+b, 0, c);
			}if(canMoveFull(b, a, A) && !visited[A][b-(A-a)][c]) {
				visited[A][b-(A-a)][c] = true;
				solve(A, b-(A-a), c);
			}
			
			if(canMoveZero(b, c, C) && !visited[a][0][b+c]) {
				visited[a][0][b+c] = true;
				solve(a, 0, b+c);
			}if(canMoveFull(b, c, C) && !visited[a][b-(C-c)][C]) {
				visited[a][b-(C-c)][C] = true;
				solve(a, b-(C-c), C);
			}
		}
		if(a!=0) {
			if(canMoveZero(a, b, B) && !visited[0][a+b][c]) {
				visited[0][a+b][c] = true;
				solve(0, a+b, c);
			}if(canMoveFull(a, b, B) && !visited[a-(B-b)][B][c]) {
				visited[a-(B-b)][B][c] = true;
				solve(a-(B-b), B, c);
			}
			if(canMoveZero(a, c, C) && !visited[0][b][a+c]) {
				visited[0][b][a+c] = true;
				solve(0, b, a+c);
			}if(canMoveFull(a, c, C) && !visited[a-(C-c)][b][C]) {
				visited[a-(C-c)][b][C] = true;
				solve(a-(C-c), b, C);
			}		
		}
		
	}
	static boolean canMoveZero (int from, int to, int max) {
		return (max-to)>=from;
	}
	static boolean canMoveFull (int from, int to, int max) {
		return max>to && max-to<from;
	}
}