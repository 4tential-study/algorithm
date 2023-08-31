import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B15927 {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String sentence = in.readLine();

    char first = sentence.charAt(0);
    for(int i = 0; i < sentence.length(); i++){
      if(first != sentence.charAt(i)) break; // 문자열이 하나의 문자로만 이루어져 있는지 검사.

      // 모두 동일한 문자를 가진 문자열이라면 -1를 출력한 후 프로그램 종료.
      if(i == sentence.length()-1){ 
        System.out.println(-1); return;
      }
    }

    for(int i = 0; i < sentence.length() / 2; i++){
      // 문자열 전체가 회문이 아니라면 해당 문자열이 회문이 아닌 가장 긴 부분문자열이므로
      // 문자열 길이를 출력후 프로그램을 종료한다.
      if(sentence.charAt(i) != sentence.charAt(sentence.length()-1-i)){
        System.out.println(sentence.length());  return;
      }
    }
    // 주어진 문자열이 회문이라면 왼쪽이든 오른쪽이든 하나의 문자만 제거하면
    // 회문이 아닌 가장 긴 부분문자열이 된다.
    System.out.println(sentence.length()-1);
  }
}