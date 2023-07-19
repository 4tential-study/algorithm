package Algorithm.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_13428 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		for (int test_case = 1; test_case<=T; test_case++) {
			sb.append("#"+test_case+" ");
			
			// 입력 받은 m의 정수 배열 생성
			char[] mChar = in.readLine().toCharArray();
			int[] mInt = new int[mChar.length];
			
			for (int i=0; i<mChar.length; i++) {
				mInt[i] = (mChar[i] - '0');
			}
			
			int[] mIntForMin = mInt.clone();
			int[] mIntForMax = mInt.clone();
			
			// 최솟값 찾기
			for (int i=0; i<mIntForMin.length; i++) {
				// mIntForMin[i] : 최솟값으로 바뀌어야할 수
				// min : 최솟값
				int min = mIntForMin[i];
				int minIdx = i;
				
				// 뒤에서부터 가장 작은 수와 그 위치를 찾기
				for (int j=mIntForMin.length-1; j>i; j--) {
					if (i==0 && mIntForMin[j]==0) continue;
					if (min > mIntForMin[j]) {
						min = mIntForMin[j];
						minIdx = j;
					}
				}
				
				if (minIdx!=i) {	// 가장 작은 수가 자신이 아니라면 
					int temp = mIntForMin[i];
					mIntForMin[i] = mIntForMin[minIdx];
					mIntForMin[minIdx] = temp;
					break;
				}
				// 가장 작은 수가 자신이라면 i+1				
			}
			String result = Arrays.toString(mIntForMin).replaceAll("[^0-9]","");
			sb.append(result + " ");
			
			// 최댓값 찾기
			for (int i=0; i<mIntForMax.length; i++) {
				// mIntForMax[i] : 최솟값으로 바뀌어야할 수
				// max : 최솟값
				int max = mIntForMax[i];
				int maxIdx = i;
				
				// 뒤에서부터 가장 작은 수와 그 위치를 찾기
				for (int j=mIntForMax.length-1; j>i; j--) {
					if (max < mIntForMax[j]) {
						max = mIntForMax[j];
						maxIdx = j;
					}
				}
				
				if (maxIdx!=i) {	// 가장 작은 수가 자신이 아니라면 
					int temp = mIntForMax[i];
					mIntForMax[i] = mIntForMax[maxIdx];
					mIntForMax[maxIdx] = temp;
					break;
				}
				// 가장 작은 수가 자신이라면 i+1				
			}
			result = Arrays.toString(mIntForMax).replaceAll("[^0-9]","");
			sb.append(result + "\n");
		}
		// 테스트 케이스 빠져나옴
		System.out.println(sb);
		in.close();
	}
}
