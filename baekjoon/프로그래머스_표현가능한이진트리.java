package 프로그래머스_표현가능한이진트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class 프로그래머스_표현가능한이진트리 {
	public static int[] answer;
	public static long decimal;
	
	public static void main(String[] args){
		long[] numbers = {7,42,5};
		int n = numbers.length;
		for (int i=0;i<n;i++) { // 주어진 숫자 decimal보다 큰 2의 배수 중 가장 작은 수의 차수(ex. decimal=7이면 8이 그 수에 해당하며, 차수는 3)
			decimal=numbers[i];
			int num=0;
			while (decimal>Math.pow(2,num)) {
				num++;
			}
			int h=0;
			while (num>=Math.pow(2,h)-1) {
				h++;
			}
			String binary="";
			for (int j=0;j<Math.pow(2, h)-1-num;j++) binary+="0";
			for (int j=num-1
					;j>=0;j--) { // 2진수를 binary라는 이름의 변수로 생성
				if (decimal>=Math.pow(2, j)) {
					binary+="1";
					decimal-=Math.pow(2,j);
				}
				else {
					binary+="0";
				}
			}
			System.out.println(binary);
			char[] arr = new char[binary.length()]; // binary 각 자릿수를 담을 arr 배열
			for (int j=0;j<num;j++) {
				arr[i]=binary.charAt(j);
			}
			
		}
	}
}
