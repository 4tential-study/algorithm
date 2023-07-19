package Algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class BOJ_11497 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		for (int tc=1; tc<=T; tc++) {			
			int n = Integer.parseInt(in.readLine());
			
			// 기존 통나무 배열
			Integer[] woodLogs = new Integer[n];
			String[] logsString = in.readLine().split(" ");
			for(int i=0; i<n; i++) {
				woodLogs[i] = Integer.parseInt(logsString[i]);
			}
			
			// 완성된 통나무 순서를 저장할 배열
			Integer[] sortedLogs = new Integer[n];
			
			// woodLogs 정렬
			// 첫번째 수와 마지막 수를 포함해 인접한 수의 차이를 최소화 하기 위해서는 정렬이 필요
			Arrays.sort(woodLogs, Collections.reverseOrder());
//			for (int i=0; i<n; i++) {
//				System.out.print(woodLogs[i] + " ");
//			}
//			System.out.println("");
			
			// 피라미드 형태로 통나무를 세우기
			// sortedLogs의 가운데(k = n/2)부터 큰 값을 채우고
			// 왼쪽(k-1)과 오른쪽(k+1)에 번갈아 다음으로 큰 값을 채워나간다
			// 이를 통해 첫번째 수와 마지막 수를 포함해 인접한 수의 차이를 최소화할 수 있다
			int k = n/2;
			int m = 0;
//			System.out.println("K : " + k);
			for (int i=0; i<=n/2; i++) {
				if (k+i<n) {
					sortedLogs[k+i] = woodLogs[m];
					m++;
					if (m>n) break;
				}
				if (i!=0 && k-i>=0) {
					sortedLogs[k-i] = woodLogs[m];
					m++;
					if (m>n) break;
				}
			}

//			System.out.print("sortedLogs : ");
//			for (int i=0; i<n; i++) {
//				System.out.print(sortedLogs[i] + " ");
//			}
//			System.out.println("");
			
			// 결과 출력용 인접수 차이 최대값 계산
			int max = 0;
			int next;
			int diff;
			for (int i=0; i<n; i++) {
				if(i==n-1) next = 0;
				else next = i+1;
				
				diff = Math.abs(sortedLogs[i] - sortedLogs[next]);
				if(max < diff) max = diff;
			}
			sb.append(max + "\n");
		}
		System.out.println(sb);
		in.close();
	}
}
