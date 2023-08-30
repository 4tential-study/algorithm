import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B2343 {
  private static int[] classes;
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String[] input = in.readLine().split(" ");
    int class_count = Integer.parseInt(input[0]);
    int blueray_count = Integer.parseInt(input[1]);

    classes = new int[class_count];
    int answer = 0;
    int left = 0, right = 0;

    input = in.readLine().split(" ");
    for(int i = 0; i < class_count; i++){
      classes[i] = Integer.parseInt(input[i]);
      left = Math.max(left, classes[i]);
      right += classes[i];
    }
    
    while(left <= right){
      int mid = (left + right)/2;
      if(count_BD(mid) <= blueray_count){
        answer = mid;
        right = mid-1;
      }
      else left = mid+1;
    }
    System.out.println(answer);
  }

  static int count_BD(int capacity){
    int sum = 0, cnt = 1;

    for(int class_time : classes){
      if(sum + class_time > capacity){
        cnt++;  sum = class_time;
      }
      else sum += class_time;
    }

    return cnt;
  }
}
