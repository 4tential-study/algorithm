import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int DP[] = new int[N+1];
		DP[0] = 1;
		DP[1] = 1;
		for(int i=2;i<=N; i++) {
			DP[i] = (DP[i-2] + DP[i-1])%10007;
		}
		System.out.println(DP[N]);
	}
}