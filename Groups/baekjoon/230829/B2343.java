import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B2343 {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
  private static int[] classes;
=======
>>>>>>> 6caf306 (230829 푸는 중...)
=======
  private static int[] classes;
>>>>>>> d68d8db ([Baekjoon] 2343, 2467)
=======
>>>>>>> 1690e96 (230829 푸는 중...)
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String[] input = in.readLine().split(" ");
    int class_count = Integer.parseInt(input[0]);
    int blueray_count = Integer.parseInt(input[1]);

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
    int max_classTime = 0;
    int[] classDP = new int[class_count+1];
=======
    classes = new int[class_count];
    int answer = 0;
    int left = 0, right = 0;
>>>>>>> d68d8db ([Baekjoon] 2343, 2467)

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

<<<<<<< HEAD
      
>>>>>>> 6caf306 (230829 푸는 중...)
=======
    return cnt;
>>>>>>> d68d8db ([Baekjoon] 2343, 2467)
=======
    int max_classTime = 0;
    int[] classDP = new int[class_count+1];

    input = in.readLine().split(" ");
    for(int i = 0; i < class_count; i++){
      int value = Integer.parseInt(input[i]);
      max_classTime = Math.max(value, max_classTime);
      classDP[i+1] = classDP[i] + value;
    }
    if(blueray_count == 1){
      System.out.println(classDP[class_count]);
      return;
    }
    else if(blueray_count == class_count){
      System.out.println(max_classTime);
      return;
    }

      
>>>>>>> 1690e96 (230829 푸는 중...)
  }
}
