package StudyTeam.BOJ.boj0720;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_14225 {
	static ArrayList<Integer> comb = new ArrayList<Integer>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
//출처 : https://zetawiki.com/wiki/%EC%9E%90%EB%B0%94_String_%EB%B0%B0%EC%97%B4%EC%9D%84_int_%EB%B0%B0%EC%97%B4%EB%A1%9C_%EB%B3%80%ED%99%98
	static int[] S;
	
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		S = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(S);
		for(int i=0; i<N; i++) {
			fullSearch(i, 0);
		}
		comb.sort(Comparator.naturalOrder());
//		for(Integer i : comb) {
//			System.out.println(i);
//		}
		
//		int temp=comb.get(0);
//		for(Integer i : comb) {
//			if (temp==i) continue;
//			if(i - temp > 1) {
//				System.out.println(temp+1);
//			}
//			temp = i;
//		}
		
		int last=0;
		for(int i=1; i<comb.size(); i++) {
			if(comb.get(i) - comb.get(i-1) > 1) {
				System.out.println(comb.get(i-1)+1);
				return;
			}
			last=comb.get(i);
		}
		System.out.println(last+1);
	}
	
	static void fullSearch(int idx, int sum) {
		if(idx>=N) return;
		
		sum+=S[idx];
		comb.add(sum);
		for (int i=idx+1; i<N;
				i++) {
			fullSearch(i, sum);	
		}
	}
}
