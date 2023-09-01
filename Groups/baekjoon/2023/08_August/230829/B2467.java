import java.io.BufferedReader;
import java.io.InputStreamReader;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
import java.util.Arrays;
>>>>>>> 6caf306 (230829 푸는 중...)
=======
>>>>>>> d68d8db ([Baekjoon] 2343, 2467)
=======
>>>>>>> 1690e96 (230829 푸는 중...)

public class B2467 {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    StringBuilder sb = new StringBuilder();
=======
>>>>>>> 6caf306 (230829 푸는 중...)
=======
    StringBuilder sb = new StringBuilder();
>>>>>>> d68d8db ([Baekjoon] 2343, 2467)
=======
>>>>>>> 1690e96 (230829 푸는 중...)
    int Integer_count = Integer.parseInt(in.readLine()); // 전체 용액의 수
    int[] values = new int[Integer_count];

    String[] input = in.readLine().split(" ");
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    for(int i = 0; i < Integer_count; i++)  values[i] = Integer.parseInt(input[i]);
    
    int min_diff = Integer.MAX_VALUE;
    int left = 0, right = Integer_count-1;
    int[] answer = new int[2];
    answer[0] = values[left]; answer[1] = values[right];

    while(left < right){
      int left_value = values[left];
      int right_value = values[right];

      int result = left_value + right_value;
      if(Math.abs(result) < min_diff){
        min_diff = Math.abs(result);
        answer[0] = values[left]; answer[1] = values[right];
      }

      if(result >= 0) right--;
      else left++;
    }
    sb.append(answer[0]).append(" ").append(answer[1]).append("\n");
    System.out.println(sb);
  }  
}
<<<<<<< HEAD
=======
    int negative_idx = -1, positive_idx = -1;
    int[] answer = new int[2];
    for(int i = 0; i < Integer_count; i++){
      values[i] = Integer.parseInt(input[i]);
      if(values[i] < 0)  negative_idx = i; // 0부터 i까지 음수
      if(positive_idx == -1 && values[i] > 0) positive_idx = i; // i부터 양수
    }
=======
    for(int i = 0; i < Integer_count; i++)  values[i] = Integer.parseInt(input[i]);
>>>>>>> d68d8db ([Baekjoon] 2343, 2467)
    
    int min_diff = Integer.MAX_VALUE;
    int left = 0, right = Integer_count-1;
    int[] answer = new int[2];
    answer[0] = values[left]; answer[1] = values[right];

    while(left < right){
      int left_value = values[left];
      int right_value = values[right];

      int result = left_value + right_value;
      if(Math.abs(result) < min_diff){
        min_diff = Math.abs(result);
        answer[0] = values[left]; answer[1] = values[right];
      }

      if(result >= 0) right--;
      else left++;
    }
    sb.append(answer[0]).append(" ").append(answer[1]).append("\n");
    System.out.println(sb);
  }  
<<<<<<< HEAD
}
>>>>>>> 6caf306 (230829 푸는 중...)
=======
}
>>>>>>> d68d8db ([Baekjoon] 2343, 2467)
=======
    for(int i = 0; i < Integer_count; i++)  values[i] = Integer.parseInt(input[i]);
    
  }  
}
>>>>>>> 1690e96 (230829 푸는 중...)
=======
>>>>>>> c34dadc58fc3c778bc76c63de69ff4b4212793a2
