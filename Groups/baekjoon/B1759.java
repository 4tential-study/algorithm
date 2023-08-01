import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B1759 {
  static String[] keywords, vowels = { "a", "e", "i", "o", "u" };
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] str = br.readLine().split(" ");
    int L = Integer.parseInt(str[0]);

    keywords = br.readLine().split(" ");
    Arrays.sort(keywords);

    solve(0, L, "");
  }

  static void solve(int index, int length, String password) {
    if (password.length() == length) {
      int count_vowel = CountVowels(password);
      if (count_vowel >= 1 && password.length() - count_vowel >= 2)
        System.out.println(password);
      return;
    }

    for (int i = index; i < keywords.length; i++)
      solve(i + 1, length, password + keywords[i]);
  }

  static int CountVowels(String str) {
    int count = 0;
    for (String vowel : vowels) {
      if (str.contains(vowel))  count++;
    }
    return count;
  }
}