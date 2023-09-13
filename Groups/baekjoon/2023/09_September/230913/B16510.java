import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B16510 {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String input[] = in.readLine().split(" ");
    int work_count = Integer.parseInt(input[0]);
    int work_times = Integer.parseInt(input[1]);

    // 주어진 일을 수행하는 데에 걸리는 시간의 누적 합
    int[] DP = new int[work_count+1]; 
    DP[0] = 0;

    input = in.readLine().split(" ");
    for(int i = 1; i <= work_count; i++) 
      DP[i] = DP[i-1] + Integer.parseInt(input[i-1]);

    for(int i = 0; i < work_times; i++){
      int time = Integer.parseInt(in.readLine()); // 일을 할 수 있는 시간
      if(time >= DP[work_count]) {
        sb.append(work_count).append("\n"); continue;
      }
      int value = Arrays.binarySearch(DP, time);
      /*
       * binarySearch의 return 값을 이용하면 문제를 해결할 수 있다.
       * return 값이 0보다 크거나 같다면, n번째의 일까지 처리 할 수 있다는 것을 알 수 있다.
       * return이 음수일 경우를 잘 살펴보자.
       * 예를 들어, DP의 내용이 아래와 같으며 time이 8이라고 가정해보자.
       * [0, 1, 3, 6, 7, 9, 12, 13]
       * 이 때, binarySearch의 return 값은 -6이다.
       * 8은 7과 9의 사이이며 7의 index는 4이다.
       * time = 8은 총 4개의 일(7 소요)을 수행할 수 있는 시간이므로,
       * 4라는 결과를 얻어야 하며 이는 return 값인 -6의 절대값에서 2를 빼주면 된다.
       * 아래의 코드는 이를 적용한 것이다.
       */
      if(value >= 0)  sb.append(value).append("\n");
      else {
        sb.append(Math.abs(value)-2).append("\n");
      }
    }

    System.out.println(sb);
  }  
}
