import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B2467 {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int Integer_count = Integer.parseInt(in.readLine()); // 전체 용액의 수
    int[] values = new int[Integer_count];

    String[] input = in.readLine().split(" ");
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