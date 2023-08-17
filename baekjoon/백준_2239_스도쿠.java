package 백준_2239_스도쿠;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//디버깅중... for문을 이상하게 돌려서 자료 참고했는데도 안 풀리는 중

public class 백준_2239_스도쿠 {
	public static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[9][9];
		for (int i = 0; i < 9; i++) {
			String[] line = br.readLine().split("");
			for (int j = 0; j < 9; j++) {
				arr[i][j] = Integer.parseInt(line[i]);
			}
		}
		fill_up(0, 0);
//		for (int a=0;a<9;a++) {
//			System.out.println(Arrays.toString(arr[a]));
//		}
	}

	public static void fill_up(int i, int j) {
//		System.out.println("what's happening"+i+" "+j);
		if (j == 9) { // 한 줄 끝까지 다 채우면 다음 줄 시작
			fill_up(i + 1, 0);
			return;
		}
		if (i == 9) { // 모든 칸 다 채웠을 경우 출력 및 종료
			for (int x = 0; x < 9; x++) {
				for (int y = 0; y < 9; y++) {
					System.out.print(arr[x][y]);
				}
				System.out.println();
			}
			return;
		}
		if (arr[i][j]==0) {
			for (int num=1;num<10;num++) {
				if (hori_check(i,j,num)&&vert_check(i,j,num)&&square_check(i,j,num)) {
					arr[i][j]=num;
					fill_up(i,j+1);
				}
			}
			arr[i][j]=0; //만약 중복되는 값이 있어서 다시 채워야 할 경우, 여기에서 초기화
			return;
		}
		fill_up(i,j+1);
	}

	public static boolean hori_check(int i, int j, int check) {
		for (int y = 0; y < 9; y++) {
			if (arr[i][y] == check) {
				return false;
			}
		}
		return true;
	}

	public static boolean vert_check(int i, int j, int check) {
		for (int x = 0; x < 9; x++) {
			if (arr[x][j] == check) {
				return false;
			}
		}
		return true;
	}

	public static boolean square_check(int i, int j, int check) {
		int div_i = (i / 3) * 3;
		int div_j = (j / 3) * 3;
		for (int x = div_i; x < div_i + 3; x++) {
			for (int y = div_j; y < div_j + 3; y++) {
				if (arr[x][y] == check) {
					return false;
				}
			}
		}
		return true;
	}
}
