import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 이동하려는 채널
		int N = Integer.parseInt(br.readLine());
		
		// 고장난 버튼의 개수
		int M = Integer.parseInt(br.readLine());
	
		boolean[] isBroken = new boolean[10];

		String[] str = null;
		
		// M이 0일 경우 입력 받으면 안된다.
		if(M != 0) str = br.readLine().split(" ");
		
		// 고장난 버튼 번호
		for(int i = 0; i < M; ++i) {
			isBroken[Integer.parseInt(str[i])] = true;
		}
		
		// +, -로만 가는 경우의 개수
		int res = Math.abs(N - 100);
		
		// 예제 입력 6 FAIL
		// 0에서 이동하는 경우
		if(!isBroken[0] && res > N + 1) {
			res = N + 1;
		}
		
		for (int i = 1; i <= 1000000; i++) {
			// cur -> N까지의 cnt 구하기
			int cur = i;
			int cnt = 0;
			
			// 만들 수 있는가?
			boolean check = true;
			
			// 해당 숫자를 누를 수 있는지 자릿수 별로 체크
			while(cur != 0) {
				int now = cur % 10;
				cur /= 10;
				
				// cur을 만들지 못하는 경우 pass
				if(isBroken[now]) {
					check = false;
					break;
				}
				
				// 숫자 버튼 한번 누르기
				++cnt;
			}
			
			// cur을 만들지 못하는 경우 pass
			if(!check) continue;
			
			// i가 N이 되기 위하여 눌러야 하는 값
			// 숫자 버튼 누른 횟수 + (+,-) 눌러야 하는 횟수
			int tmp = cnt + Math.abs(i - N);
			
			// 최소값 갱신
			if(res > tmp) res = tmp;
		}
		
		System.out.println(res);
	}
}
