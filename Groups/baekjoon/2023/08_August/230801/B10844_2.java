import java.util.Scanner;
public class B10844_2 {
  static int MOD = 1_000_000_000;
  // 찬영's 아이디어를 참고하였습니다. (메모리 사용량 개선 시도)
  static int[] prevDP, presentDP; // 이전 단계, 현재 단계
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num_length = sc.nextInt();
        
        prevDP = new int[10];
        presentDP = new int[10];
        for(int i = 1; i < 10; i++) prevDP[i] = 1;
        presentDP = prevDP.clone();

        for(int n = 2; n <= num_length; n++){
          // 끝자리 수가 0, 9가 되려면 이전 단계의 끝자리수가 1, 8일 때 외에는 방법이 없다.
          presentDP[0] = prevDP[1]; presentDP[9] = prevDP[8];
          for(int k = 1; k < 9; k++){
            presentDP[k] = (prevDP[k-1] + prevDP[k+1]) % MOD;
          }
          prevDP = presentDP.clone();
        }
        int result = 0;
        for(int i = 0; i < 10; i++){
          result = (result + presentDP[i]) % MOD;
        }
        System.out.println(result);
        sc.close();
    }
}