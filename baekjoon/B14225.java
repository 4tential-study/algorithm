// Baekjoon 14225
// 이해하기 편하지만 시간을 많이 소모한다.
import java.io.*;
import java.util.*;

public class Q14225 {
	// 수열 S에서 나온 부분 수열의 합들을 저장하기 위해 set을 사용.
	static Set<Integer> set = new TreeSet<>();
	static int[] numbers;
	static int size; // 입력받는 수의 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(br.readLine()); 
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력받은 수들을 numbers에 저장.
		numbers = new int[size];
		for (int i = 0; i < size; i++)
			numbers[i] = Integer.parseInt(st.nextToken());

		// 재귀 함수를 이용하여 완전 탐색을 실행한다.
		for (int i = 0; i < size; i++)
			recursion(i, numbers[i]);

		// 1부터 시작해서 set에 없는 자연수를 탐색.
		int result = 1;
		while (true) {
			if (set.contains(result))
				result++;
			else	break; // set에 없다면 break후 print
		}

		System.out.println(result);
	}

	public static void recursion(int index, int sum) {
		set.add(sum); // 부분 수열의 합을 set에 저장함.
		for (int i = index + 1; i < size; i++)
			recursion(i, sum + numbers[i]);
	}

}