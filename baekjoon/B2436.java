import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * A*B = gcd(A,B)*lcm(A,B) 이므로
 * 위의 공식을 사용하면 두 수를 쉽게 구할 수 있다.
 */
public class B2436 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long gcd = Integer.parseInt(st.nextToken()); // 최대공약수
        long lcm = Integer.parseInt(st.nextToken()); // 최소공배수

        long temp = gcd * lcm;
        long searchNum = gcd;
        long A = gcd, B = lcm;
        // 이전에는 Math.sqrt 함수를 이용하였으나,
        // 성능상 아래와 같이 사용하는 것이 더 유리한 것을 알게 되었다.
        while (searchNum * searchNum <= temp) {
            if (temp % searchNum == 0L) {
                long tempA = searchNum, tempB = temp / searchNum;
                if (findGCD(tempA, tempB) == gcd) {
                    A = tempA;
                    B = tempB;
                }
            }
            searchNum++;
        }
        System.out.println(A + " " + B);
    }

    static long findGCD(long a, long b) {
        if (a < b)
            return findGCD(b, a);
        if (a % b == 0)
            return b;
        return findGCD(b, a % b);
    }
}