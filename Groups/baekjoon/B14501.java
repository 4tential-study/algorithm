import java.io.*;
import java.util.StringTokenizer;

public class B14501{
    static int[][] consulting; // 필요 기간과 이익.
	static int max_price = Integer.MIN_VALUE; // 최대 이익
	static int days; // 며칠까지 상담하는가
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		days = Integer.parseInt(br.readLine());

		consulting = new int[days][2];
		for (int i = 0; i < days; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < consulting[0].length; j++)
				consulting[i][j] = Integer.parseInt(st.nextToken());
		}   
        solve(0, 0);
		System.out.println(max_price);
    }

    static void solve(int index, int cost) {
        if(index >= days){
            max_price = Math.max(max_price, cost);
            return;
        }

        // 상담을 할 수 있다면 -> 상담 끝난 날짜와 상담비를 갱신
        if(index + consulting[index][0] <= days){ 
            solve(index+consulting[index][0], cost + consulting[index][1]);
        }

        // 해당 날짜에 상담을 하지 않는다. 다음 날로 진행.
        solve(index+1, cost);
    }
}