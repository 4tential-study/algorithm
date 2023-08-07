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
			int targetPrice = Integer.parseInt(in.readLine());
			int[] DP = new int[targetPrice+1];
			DP[0] = 1;
			for(int coin=1; coin<=3; coin++) {
				for(int price=1; price<=targetPrice; price++) {
					if(coin <= price) {
						DP[price] += DP[price-coin];
					}
				}
			}
			sb.append(DP[targetPrice]+"\n");
		}
		
		System.out.println(sb.toString());
	}
}