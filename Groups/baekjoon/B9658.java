import java.util.Arrays;
import java.util.Scanner;

public class B9658 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int stones = sc.nextInt();
    // winnable[i] : 돌이 i개가 있을 때 해당 플레이어가 이기는가?
    boolean[] winnable = new boolean[stones+1]; 
    Arrays.fill(winnable, false);

    for(int i = 2; i <= stones; i++){
      if(i <= 3){ // 돌이 3보다 작거나 같다면 1개씩 빼는 수밖에 없다.
        winnable[i] = !winnable[i-1]; continue;
      }
      // 다음 턴의 상대가 지는 경우의 수가 있다면 true. 그렇지 않다면 false.
      if(!winnable[i-1] || !winnable[i-3] || !winnable[i-4])  winnable[i] = true;
    }

    String winner = winnable[stones] ? "SK" : "CY";
    System.out.println(winner);
    sc.close();
  }
}