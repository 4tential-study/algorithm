import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for(int i=0; i<T; i++) {
			int N = Integer.parseInt(in.readLine());
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			int coins[] = new int[N];
			for(int j=0; j<N; j++) {
				coins[j] = Integer.parseInt(st.nextToken());
			}
			int targetPrice = Integer.parseInt(in.readLine());
			int[] DP = new int[targetPrice+1];
			DP[0] = 1;
			for(int coin=0; coin<N; coin++) {
				for(int price=1; price<=targetPrice; price++) {
					if(coins[coin] <= price) {
						DP[price] += DP[price-coins[coin]];
					}
				}
			}
			sb.append(DP[targetPrice]+"\n");
		}
		System.out.println(sb.toString());
	}
}