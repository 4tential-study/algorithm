package 백준_1780_종이의개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_1780_종이의개수 {
	public static int[][] arr;
	public static int N, check;
	public static int[] count = { 0, 0, 0 }; // -1의 개수, 0의 개수, 1의 개수 순서대로 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(line[j]);
			}
		}
		divide_n_conquer(0,0,N);
		for (int i=0;i<3;i++)
		System.out.println(count[i]);
	}

	public static int checker(int start, int end, int len) {
		check = arr[start][end];
		for (int i = start; i < start + len; i++) {
			for (int j = end; j < end + len; j++) {
				if (arr[i][j] != check) {
					return 0; // 같은 수로만 채워지지 않은 경우 0 반환
				}
			}
		}
		return 1; // 같은 수로만 채워진 경우 1 반환
	}

	public static void divide_n_conquer(int start, int end, int len) {
		if (checker(start,end,len)==0) {
			int new_len = len/3;
			divide_n_conquer(start,end,new_len);
			divide_n_conquer(start,end+new_len,new_len);
			divide_n_conquer(start,end+2*new_len,new_len);
			divide_n_conquer(start+new_len,end,new_len);
			divide_n_conquer(start+new_len,end+new_len,new_len);
			divide_n_conquer(start+new_len,end+2*new_len,new_len);
			divide_n_conquer(start+2*new_len,end,new_len);
			divide_n_conquer(start+2*new_len,end+new_len,new_len);
			divide_n_conquer(start+2*new_len,end+2*new_len,new_len);
		}
		else {
			check=arr[start][end];
			if (check==-1) 
				count[0]++;
			else if (check==0)
				count[1]++;
			else
				count[2]++;
		}
	}
}
