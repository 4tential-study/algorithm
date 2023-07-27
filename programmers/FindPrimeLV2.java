import java.util.ArrayList;
import java.util.HashSet;
/*
 * 그냥 별 생각하지 않고 짠 코드.
 * 아직 최적화를 하지 않아 성능 측면에서는 별로 좋지 않다고 생각함.
 */
class Solution {
    static int max_Integer = 9_999_999;
    static int max_divisor = (int) Math.sqrt(max_Integer);
    static boolean[] visited;
    static ArrayList<Integer> primes = new ArrayList<>();
    static HashSet<Integer> SearchedSet = new HashSet<>();

    public int solution(String numbers) {
        primes.add(2);
        makePrimeArray();
        char[] arrays = numbers.toCharArray();
        visited = new boolean[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            visited[i] = true;
            String str = Character.toString(arrays[i]);
            solve(arrays, str);
            visited[i] = false;
        }
        return SearchedSet.size();
    }

    static void solve(char[] array, String str) {
        int search = Integer.parseInt(str);

        if (!SearchedSet.contains(search) && isPrime(search)) {
            SearchedSet.add(search);
        }

        // array.length와 같으면 다시 돌아가
        if (str.length() == array.length)
            return;

        // 백트래킹
        for (int i = 0; i < array.length; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            String newStr = str + Character.toString(array[i]);
            solve(array, newStr);
            visited[i] = false;
        }
    }

    static boolean isPrime(int n) {
        // 미리 찾은 소수라면 true
        if (primes.contains(n))
            return true;
        for (Integer prime : primes)
            // 미리 찾은 소수 중에 약수가 있다면 그 수는 소수가 아니다.
            if (n < prime || n % prime == 0)
                return false;
        return true;
    }
    /*
     * 에라토스테네스의 체를 이용
     * 소수를 미리 탐색하여 ArrayList에 담는다.
     * ArrayList에 담는 것 보단 그냥 boolean 배열을 이용해서
     * 소수면 true, 아니면 false를 하는 게 더 이득일 듯? -> 나중에 시도해보기.
     * 
     */

    static void makePrimeArray() {
        for (int i = 3; i <= max_divisor; i += 2) {
            if (isPrime(i))
                primes.add(i);
        }
    }
}