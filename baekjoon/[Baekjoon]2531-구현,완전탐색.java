package Algorithm.boj.day0720;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_2531 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n, d, k, c;
//		n = sc.nextInt();
//		d = sc.nextInt();
//		k = sc.nextInt();
//		c = sc.nextInt();
//		sc.nextLine();
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		int[] plate = new int[n];
		int maxResult = 0;
		Integer[] arr = new Integer[k];
		int[] plateIdx = new int[d+1];
		
		for(int i=0; i<n; i++) {
			plate[i] = Integer.parseInt(br.readLine());
		}

		int cnt=0;
		for(int i=0; i<n+k-1; i++) {
			int bonus=0;
			int forLoop = i%k;
			int forLoopI = i%n;
//			TreeSet<Integer> set = new TreeSet<Integer>();
			
//			for(int j=i; j<k+i; j++) {
//				if(!(set.contains(plate[j]))) {
//					cnt++;
//					set.add(plate[j]);
//				}
//			}
////			System.out.println(set);
//			if(!(set.contains(c))) {
//				cnt++;
//			}
			
			if(i>=k) {
				plateIdx[arr[forLoop]]--;
				if(plateIdx[arr[forLoop]]==0) {
					cnt--;
				}
			}
			
			arr[forLoop] = plate[forLoopI];
			
			
			if(plateIdx[arr[forLoop]] == 0) {
				plateIdx[arr[forLoop]]++;
				cnt++;
			}else {
				plateIdx[arr[forLoop]]++;
			}
			// =======
			if (plateIdx[c]==0) {
				bonus = 1;
			}
			// =======
			
//			System.out.println(arr[forLoop] + " " + cnt);
			
			
			if(maxResult < cnt + bonus) maxResult = cnt + bonus;
		}
		System.out.println(maxResult);
	}
}