import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B9935 {
    public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();
        String bomb = in.readLine();
        String result = solve(input, bomb);
        System.out.println(result);
    }

    public static String solve(String input, String bomb) {
        StringBuilder result = new StringBuilder();
        int inputLength = input.length();
        int bombLength = bomb.length();

        for (int i = 0; i < inputLength; i++) {
            result.append(input.charAt(i));

            // 문자열 끝에서부터 폭발 문자열과 일치하는지 확인
            int resultLength = result.length();
            if (resultLength >= bombLength
                    && result.substring(resultLength - bombLength, resultLength).equals(bomb)) {
                // 폭발 문자열과 일치하면 제거
                result.delete(resultLength - bombLength, resultLength);
            }
        }

        String finalResult = result.toString();
        
         // 모든 문자가 제거된 경우 FRULA를 출력
        if (finalResult.isEmpty()) return "FRULA";   
        else return finalResult;
        
    }
}
