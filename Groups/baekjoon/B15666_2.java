import java.io.*;
import java.util.*;
/*
 * 조합의 결과를 ArrayList로 저장하는 것은 메모리에 부담이 된다고 생각하였다.
 * 그래서 조합 결과를 String으로 변환하여 String을 저장한 후,
 * 결과 출력 시 BufferedWriter를 이용하여 저장한 String들을 출력하였다.
 * String을 저장할 때, 중복 체크를 원할하게 하고자 Set으로 변경하였고,
 * TreeSet 등을 이용할 경우, Comparator에 의해 String이 정렬되어 출력됨으로 오류가 발생한다.
 * (ex. 2와 10을 입력받음 -> 결과 : 10 2 , 해답 : 2 10)
 * add한 순서를 유지하기 위해 LinkedHashSet을 사용하였다.
 */
public class B15666_2 {
    static int[] numbers;
    static int N, M;
    static LinkedHashSet<String> stored = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] NM = br.readLine().split(" ");
        N = Integer.parseInt(NM[0]);
        M = Integer.parseInt(NM[1]);

        numbers = new int[N];
        String[] inputs = br.readLine().split(" ");

        for (int i = 0; i < N; i++)
            numbers[i] = Integer.parseInt(inputs[i]);
        Arrays.sort(numbers);

        solve(0, new ArrayList<Integer>());
        for (String str : stored) {
            bw.write(str + "\n");
        }

        bw.flush();
    }

    static void solve(int index, ArrayList<Integer> array) {
        if (array.size() == M) {
            StringBuilder sb = new StringBuilder();

            // 조합 결과를 StringBuilder를 이용해 String으로 변환.
            for (int num : array) 
                sb.append(num).append(" ");
            
            stored.add(sb.toString()); // set에 String을 add.
            return;
        }

        for (int i = index; i < N; i++) {
            array.add(numbers[i]);
            solve(i, array);
            array.remove(array.size() - 1);
        }
    }
}