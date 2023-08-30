import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B2467 {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int Integer_count = Integer.parseInt(in.readLine()); // 전체 용액의 수
    int[] values = new int[Integer_count];

    String[] input = in.readLine().split(" ");
    for(int i = 0; i < Integer_count; i++)  values[i] = Integer.parseInt(input[i]);
    
  }  
}
