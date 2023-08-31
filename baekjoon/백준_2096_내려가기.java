package 백준_2096_내려가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_2096_내려가기 {
	public static int min_l,min_m,min_r,max_l,max_m,max_r;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[3];
		int[] min_arr = new int[3];
		int[] max_arr = new int[3];
		String[] line2 = br.readLine().split(" ");
		min_l=max_l=Integer.parseInt(line2[0]);
		min_m=max_m=Integer.parseInt(line2[1]);
		min_r=max_r=Integer.parseInt(line2[2]);
		for (int i=1;i<N;i++) {
			String[] line = br.readLine().split(" ");
			for (int j=0;j<3;j++) {
				arr[j]=Integer.parseInt(line[j]);
			}
			min_arr[0]=Math.min(min_l,min_m)+arr[0];
			min_arr[1]=Math.min(Math.min(min_l, min_m),min_r)+arr[1];
			min_arr[2]=Math.min(min_m,min_r)+arr[2];
			max_arr[0]=Math.max(max_l, max_m)+arr[0];
			max_arr[1]=Math.max(Math.max(max_l, max_m), max_r)+arr[1];
			max_arr[2]=Math.max(max_m, max_r)+arr[2];
			min_l=min_arr[0];
			min_m=min_arr[1];
			min_r=min_arr[2];
			max_l=max_arr[0];
			max_m=max_arr[1];
			max_r=max_arr[2];
		}
		System.out.println(Math.max(Math.max(max_l, max_m),max_r)+" "+Math.min(Math.min(min_l, min_m),min_r));
	}

}
