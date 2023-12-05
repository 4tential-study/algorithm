import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final int SIZE = 51;
	private static int[][] parents = new int[SIZE][SIZE];
	private static String[][] board = new String[SIZE][SIZE]; 
	
	static int find(int a) {
		int y = a/50, x = a% 50;
		if(parents[y][x] == a)	return a;
		return parents[y][x] = find(parents[y][x]);
	}
	
	static void union_find(int a, int b) {
		int parent_a = find(a), parent_b = find(b);
		if(parent_a == parent_b) return;
		else parents[parent_b/50][parent_b%50] = parent_a;
	}
    
	public static String[] solution(String[] commands) {
		List<String> list = new ArrayList<>();
		String value;
		
		initParents();
		
		for(String cmd : commands) {
			String[] input = cmd.split(" ");
			switch(input[0]) {
			case "UPDATE":
				// UPDATE r c value
				if(input.length == 4) {
					int y = Integer.parseInt(input[1]);
					int x = Integer.parseInt(input[2]);
					int parent = find(y*50+x);
					board[parent/50][parent%50] = input[3];
				}
				// UPDATE value1 value2
				else {
					for(int y = 1; y < SIZE; y++) {
						for(int x = 1; x < SIZE; x++) {
							if(board[y][x].equals(input[1]))
								board[y][x] = input[2];
						}
					}
				}
				break;
			// MERGE r1 c1 r2 c2
			case "MERGE":
				int y1 = Integer.parseInt(input[1]), x1 = Integer.parseInt(input[2]);
				int y2 = Integer.parseInt(input[3]), x2 = Integer.parseInt(input[4]);
				
				int parent1 = find(y1*50+x1), parent2 = find(y2*50+x2);
				// if(parent1 == parent2) break;
				
				value = board[parent1/50][parent1%50].equals("") ? board[parent2/50][parent2%50] : board[parent1/50][parent1%50];
				union_find(y1*50+x1, y2*50+x2);
				
				board[parent1/50][parent1%50] = value;
				
				break;
				
			// UNMERGE r c
			case "UNMERGE":
				int y = Integer.parseInt(input[1]), x = Integer.parseInt(input[2]);
				int parent = parents[y][x];
				value = board[parent/50][parent%50];
				
				for(int i = 1; i < SIZE; i++) {
					for(int j = 1; j < SIZE; j++) {
						if(parents[i][j] == parent) initParent(i, j);
					}
				}
				
				board[y][x] = value;
				break;
				
			// PRINT r c
			case "PRINT":
				int py = Integer.parseInt(input[1]);
				int px = Integer.parseInt(input[2]);
				int print_parent = find(py*50+px);
				if(board[print_parent/50][print_parent%50].equals(""))
					list.add("EMPTY");
				else list.add(board[print_parent/50][print_parent%50]);
				break;
			}
		}
		
		return list.toArray(new String[0]);
	}
	
	
	
	static void initParents() {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				board[i][j] = new String();
				parents[i][j] = i * 50 + j;
			}
		}
	}
	
	static void initParent(int y, int x) {
		board[y][x] = "";
		parents[y][x] = y * 50 + x;
	}
}