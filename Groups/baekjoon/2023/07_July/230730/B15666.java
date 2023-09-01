import java.io.*;
import java.util.*;

public class B15666 {
    static int[] numbers;
    static ArrayList<ArrayList<Integer>> stored = new ArrayList<>();
    static int N, M;
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

        for (ArrayList<Integer> array : stored) {
            for (Integer num : array) {
                bw.write(num + " ");
            }
            bw.write("\n");
        }
        bw.flush();
    }

    static void solve(int index, ArrayList<Integer> array) {
        if (array.size() == M) {
            if (!stored.contains(array))
                stored.add(new ArrayList<>(array));
            return;
        }

        for (int i = index; i < N; i++) {
            array.add(numbers[i]);
            solve(i, array);
            array.remove(array.size() - 1);
        }
    }
}