import java.io.*;
import java.util.StringTokenizer;
/*
 * 메모리 너무 많이 쓴다. (321492KB, 908ms)
 * 담에 다른 시도 해보기.
 */
public class B15486 {
	static int[][] consultings;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int days = Integer.parseInt(br.readLine());
		
		consultings = new int[days+2][2];
		
		for(int i = 1; i <= days; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 2; j++)
				consultings[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int[] DP = new int[days+2];
        for(int i = days; i >= 1; i--){
            if(i + consultings[i][0] <= days+1){
                DP[i] = Math.max(DP[i+1], DP[i + consultings[i][0]] + consultings[i][1]);
            }
            else DP[i] = DP[i+1];
        }

    System.out.println(DP[1]);
	}
}