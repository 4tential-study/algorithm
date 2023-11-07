import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class B9177 {
	private static final int dir[][] = {{0, 1}, {1, 0}}; // 우, 하
	private static final int dirSize = dir.length;
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		Testcase:
		for(int tc = 1; tc <= T; tc++) {
			sb.append("Data set ").append(tc).append(": ");
			String[] input = in.readLine().split(" ");
			
			char[] a = input[0].toCharArray();	int a_length = a.length;
			char[] b = input[1].toCharArray();	int b_length = b.length;
			char[] c = input[2].toCharArray();	int c_length = c.length;
			
			boolean[][] visited = new boolean[a_length+1][b_length+1];
			Queue<int[]> queue = new ArrayDeque<>();
			visited[0][0] = true;
			queue.offer(new int[] {0, 0, 0});
			
			while(!queue.isEmpty()) {
				int cy = queue.peek()[0], cx = queue.peek()[1];
				int cout = queue.poll()[2];
				
				if(cout == c_length)	{
					sb.append("yes").append("\n");
					continue Testcase;
				}
				
				for(int type = 0; type < dirSize; type++) {
					int dy = cy + dir[type][0];
					int dx = cx + dir[type][1];
										
					if(dy <= a_length && dx <= b_length && !visited[dy][dx]) {
						char ch = (dy == cy)? b[dx-1] : a[dy-1];
						if(c[cout] == ch) {
							visited[dy][dx] = true;
							queue.offer(new int[] {dy, dx, cout+1});
						}
					}
				}
			}
			
			sb.append("no").append("\n");
		}
		
		System.out.println(sb);
	}
}
