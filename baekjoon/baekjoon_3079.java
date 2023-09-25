package W0925;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class baekjoon_3079 {
    static int n;
    static long m, max;
    static int [] arr;
    static long result = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
 
        String[] str = in.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
 
        arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(in.readLine());
            max = Math.max(max,arr[i]);
        }
        Arrays.sort(arr);
 
        solve();
 
        System.out.println(result);
    }
 
    private static void solve(){
        long low = 0;
        long high = m * max;
 
        while(low<=high){
            long mid = (low+high)/2;
            long sum = 0;
            for(long index: arr){
                long count = mid/index;
 
                if(sum>=m){
                    break;
                }
                sum+=count;
            }
            if(sum>=m){
                high = mid-1;
                result = Math.min(mid,result);
            }
            else{
                low = mid+1;
            }
        }
    }
}