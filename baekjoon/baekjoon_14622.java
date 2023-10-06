import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	 
	static boolean isShowed[] = new boolean[5_000_000+1];
	static int N;
	static long aScore, bScore;	// a : 대웅, b : 규성
	static List<Integer> aList = new ArrayList();
	static List<Integer> bList = new ArrayList();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		StringTokenizer st;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(in.readLine());
			int numA = Integer.parseInt(st.nextToken());
			int numB = Integer.parseInt(st.nextToken());
			
			if(isPrime(numA)) {
				if(isShowed[numA]) aScore -= 1000;
				else {
					isShowed[numA] = true;
					aList.add(numA);
					Collections.sort(aList, Comparator.reverseOrder());
				}
			}else {
				if(bList.size()>=3) bScore += bList.get(3-1);
				else bScore += 1000;				
			}
			if(isPrime(numB)) {
				if(isShowed[numB]) bScore -= 1000;
				else {
					isShowed[numB] = true;
					bList.add(numB);
					Collections.sort(bList, Comparator.reverseOrder());
				}
			}else {
				if(aList.size()>=3) aScore += aList.get(3-1);
				else aScore += 1000;
			}
		}
		
		if(aScore > bScore) System.out.println("소수의 신 갓대웅");
		else if(aScore < bScore) System.out.println("소수 마스터 갓규성");
		else System.out.println("우열을 가릴 수 없음");
	}
	static boolean isPrime(int num) {
		if(num<2) return false;
		
		for(int i=2; i<=Math.sqrt(num); i++) {
			if(num%i==0) return false;
		}
		
		return true;		
	}
	
}