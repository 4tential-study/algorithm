import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B2011 {
  private static final int MOD = 1_000_000;
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String encrypt = in.readLine();

    int[] DP = new int[encrypt.length() + 1];
    DP[0] = 1;

    // 처음부터 0이 나온다면 이 암호는 유효하지 않다.
    if(encrypt.charAt(0) == '0'){System.out.println(0); return;}

    DP[1] = 1;

    for(int i = 2; i <= encrypt.length(); i++){
      // 현재 탐색하려는 글자가 0이라면,
      if(encrypt.charAt(i-1) == '0'){
        // 바로 이전의 글자가 무엇인지를 살펴봄.
        int value = encrypt.charAt(i-2) - '0';

        // 바로 이전 글자와 합쳐 10이나 20이 아닌 다른 글자가 된다면
        // 이 암호는 유효하지 않은 암호이다.
        if(value > 2 || value == 0){
          System.out.println(0);  return;
        }
        // 10이나 20이 된다면, 이를 하나의 글자로 생각해보자.
        // 그렇다면 암호의 길이가 i-2일 때와 동일한 단계가 되므로,
        // DP[i-2]의 값을 할당한다.
        else DP[i] = DP[i-2];
      }
      else {
        int value = Integer.parseInt(encrypt.substring(i-2, i));

        // 탐색하려는 글자와 그 이전 글자를 합쳐서 26이하라면 해당 연산을 수행함.
        // 이전 글자가 0이라면 그 앞의 글자와 이미 합쳐진 것이므로, 이 조건이 만족되면 안된다.
        if(value <= 26 && encrypt.charAt(i-2) != '0'){
          DP[i] = (DP[i-1] + DP[i-2]) % MOD;
        }
        else DP[i] = DP[i-1];
      }
    }
    System.out.println(DP[encrypt.length()]);
  }  
}