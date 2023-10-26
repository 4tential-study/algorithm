package week1016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_1722_순열의순서 {
	
	static long factorial[] = new long[21];
	static boolean visited[] = new boolean[21];
	
	static int N;
	static long result;
	static int numbers[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		numbers = new int[N];
		setFactorial();
		
		String[] input = in.readLine().split(" ");
		if(input[0].equals("1")) {
			long K = Long.parseLong(input[1]);
			
			for(int i=0; i<N; i++) {
				for(int j=1; j<=N; j++) {
					if(visited[j]) continue; 
						
					if(factorial[N-i-1] < K) {
						K -= factorial[N-i-1];
					}else {
						numbers[i] = j;
						visited[j] = true;
						break;
					}
				}
			}
			
			for(int i=0; i<N; i++) {
				System.out.print(numbers[i]+" ");
			}
			
		}else if(input[0].equals("2")) {
			for(int i=0; i<N; i++) {
				numbers[i] = Integer.parseInt(input[i+1]);
			}
			
			result = 1;
			for(int i=0; i<N; i++) {
				for(int j=1; j<numbers[i]; j++) {
					if(!visited[j])
						result += factorial[N-i-1];
				}
				visited[numbers[i]] = true;
			}
			
			System.out.println(result);
		}		
	}
	
	static void setFactorial() {
		factorial[0] = 1;
		for(int i=1; i<=20; i++) {
			factorial[i] = factorial[i-1] * i;
		}
	}
}
