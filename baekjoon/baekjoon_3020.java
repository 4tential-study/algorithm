import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;
import java.util.*;
 
public class Main {
 
    static int result;
    static int [] up;
    static int [] down;
    static int n,h;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        String[] s = br.readLine().split(" ");
 
        n  = Integer.parseInt(s[0]);
        h  = Integer.parseInt(s[1]);
 
        up = new int[n/2];
        down = new int[n/2];
        for(int i=0; i<n/2; i++) {
            int num1 = Integer.parseInt(br.readLine());
            int num2 = Integer.parseInt(br.readLine());
            down[i]=num1;
            up[i]=num2;
        }
 
        int min = n;
        int count=0;
 
        Arrays.sort(up);
        Arrays.sort(down);
 
 
        for(int i=1; i<h+1; i++){
            int countWall = solve(0,n/2,i,down)+solve(0,n/2,h-i+1, up);
 
            if(min==countWall){
                count++;
                continue;
            }
            if(min>countWall){
                min=countWall;
                count=1;
            }
        }
 
        System.out.println(min +" "+count);
 
    }
 
    private static int solve(int left, int right, int h, int[]arr){
        while(left<right) {
            int mid = (left+right)/2;
 
            if(arr[mid] >= h) {
                right = mid;
            }else {
                left = mid+1;
            }
        }
        return arr.length-right;
    }
}