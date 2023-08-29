import java.io.BufferedReader;
import java.io.InputStreamReader;
<<<<<<< HEAD
=======
import java.util.Arrays;
>>>>>>> 6caf306 (230829 푸는 중...)

public class B2467 {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
<<<<<<< HEAD
    StringBuilder sb = new StringBuilder();
=======
>>>>>>> 6caf306 (230829 푸는 중...)
    int Integer_count = Integer.parseInt(in.readLine()); // 전체 용액의 수
    int[] values = new int[Integer_count];

    String[] input = in.readLine().split(" ");
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
=======
    int negative_idx = -1, positive_idx = -1;
    int[] answer = new int[2];
    for(int i = 0; i < Integer_count; i++){
      values[i] = Integer.parseInt(input[i]);
      if(values[i] < 0)  negative_idx = i; // 0부터 i까지 음수
      if(positive_idx == -1 && values[i] > 0) positive_idx = i; // i부터 양수
    }
    
    int result = Integer.MAX_VALUE;
    for(int i = 0; i < Integer_count; i++){
      int current = values[i];
      if(current < 0){ // 산성인 경우
        if(positive_idx == -1){ // 산성밖에 없다?
          int value = Math.abs(current + values[Integer_count-1]);
          if(result > value){
            result = value;
            answer[0] = current;  answer[1] = values[Integer_count-1];
            break;
          }
        }
        else{ // 알칼리도 있다면,
          
          // int search = Integer.MAX_VALUE;
          // for(int idx = Integer_count-1; idx >= i+1; idx--){
          //   int value = Math.abs(current + values[idx]);
          //   if(search < value)  break;
          //   search = Math.min(search, value);
          //   if(result > search){
          //     result = search;
          //     answer[0] = current;  answer[1] = values[idx];
          //   }
          // }
        }
      }
      else{ // 알칼리인 경우
        if(negative_idx == -1){ // 알칼리밖에 없다?
          int value = Math.abs(current + values[positive_idx]);
          if(result > value){
            result = value;
            answer[0] = current; answer[1] = values[positive_idx];
            break;
          }
        }
        else { // 산성도 있다면
          // int search = Integer.MAX_VALUE;
          // for(int idx = 0; idx < i; idx++){
          //   int value = Math.abs(current + values[idx]);
          //   if(search < value)  break;
          //   search = Math.min(search, value);
          //   if(result > search){
          //     result = search;
          //     answer[0] = current;  answer[1] = values[idx];
          //   }
          // }
        }
      }

    } // 탐색 종료
    Arrays.sort(answer);
    System.out.println(answer[0] + " " + answer[1]);
  }  
}
>>>>>>> 6caf306 (230829 푸는 중...)
