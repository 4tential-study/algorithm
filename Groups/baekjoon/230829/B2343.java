import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B2343 {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String[] input = in.readLine().split(" ");
    int class_count = Integer.parseInt(input[0]);
    int blueray_count = Integer.parseInt(input[1]);

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

      
  }
}
