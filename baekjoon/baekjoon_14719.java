package W0925;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 빗물이 고이기 위한 조건
 * - 현재 블록의 높이보다 높은 블록이 왼쪽에 있어야 한다
 * - 현재 블록의 높이보다 높은 블록이 오른쪽에 있어야 한다
 * - 첫, 마지막 블록엔느 빗물이 고일 수 없다
 */
public class baekjoon_14719 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		int blocks[] = new int[W];
		for(int i=0; i<W; i++) {
			blocks[i] = Integer.parseInt(st.nextToken());
		}
		
		int result = 0;
		
		for(int i=1; i<W-1; i++) {
			int left = 0;
			int right = 0;
			
			for(int j=0; j<i; j++) {
				left = Math.max(blocks[j], left);
			}
			
			for(int j=i+1; j<W; j++) {
				right = Math.max(blocks[j], right);
			}
			
			if(blocks[i] < left && blocks[i] < right)
				result += Math.min(left, right) - blocks[i];
		}
		
		System.out.println(result);
		
	}
}
