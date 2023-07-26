import java.util.Scanner;

public class Main {
	
	static int num;
	static int cnt=0;
	static StringBuilder sb = new StringBuilder();
	
	
		// 아이디어 : N을 입력으로 받았을 때 i번째 줄을 출력하고자 한다.
		// 출력은 크게 3개의 줄로 이루어져 있다.
		// 첫째줄과 셋째줄은 테두리(t())로만 이루어져 있다.
		// 둘째줄은 테두리+공백+테두리로 이루어져 있다.
		// 
		// 테두리는 그 안에서 다시 하나의 형태를 가진다.
		// 하지만 공백은 그렇지 않다.
		//
		// N이 9일 때, 0번째 줄은 테두리의 테두리일 것이다.
		// N이 9일 때, 1번째 줄은 테두리의 가운데일 것이다.
		// N이 9일 떄, 2번째 줄은 테두리의 테두리일 것이다.
		//
		// N이 9일 때, 3번째 줄은 가운데의 테두리일 것이다.
		// N이 9일 때, 4번째 줄은 가운데의 가운데일 것이다.
		//
		// N이 27일 때, 1번째 줄은 테두리의 테두리의 가운데일 것이다.
		// N이 27일 때, 9번째~17번째 줄은 가운데의 가운데의 가운데일 것이다.
		//
		// 여기서 N을 3등분 했을 때 출력하고자 하는 i번째 줄이 어디에 위치하는가가 재귀의 깊이와 연관이 있다는 것을 알았다
		// N이 9일 때(즉 3의 2제곱일 때), 1번째 줄은 처음 3등분할 때 첫째줄, 즉 테두리에 위치한다. 그렇다면 별을 찍는 재귀 함수 t()를 3번 불러온다.
		// 재귀에 한번 들어간 후, 즉 두번째로 3등분할 때는 둘째줄, 즉 가운데에 위치한다. 그렇다면 t() 한번 + 공백 한번 + t() 한번으로 이루어진다.
		// 가운데를 출력하기 위해 한번 더 불러온 t()에서 파라미터 N은 3으로 계속 나누어져 1이 되며, 이때 별을 하나 찍고 함수를 빠져나온다.
		// 공백은 현재 재귀의 파라미터 N의 3분의 1만큼 찍히면 되므로 for문으로 찍고 다시 테두리를 찍기 위해 t()를 불러온다.
		// 셋째줄을 출력하기 위해 첫째줄과 같이 t()를 3번 출력한다.
		//
		// 이를 모든 줄에서 반복한다.
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		num = sc.nextInt();
		
		// 줄마다 t() 실행
		for(int i=0; i<num; i++) {
			t(num, i);			
			sb.append("\n");
		}
		System.out.println(sb);
	}
	public static void t(int N, int line) {
		if(N==1) {
			sb.append("*");
			return;
		}
		
		switch (line/(N/3)) {
		case 0:	// 첫째 줄
		case 2: // 셋째 줄
			for (int i=0; i<3; i++) {
				t(N/3, line%(N/3));
			}
			break;
		case 1: // 둘째 줄
			t(N/3, line%(N/3));
			for(int i=0; i<N/3; i++) {
				sb.append(" ");
			}
			t(N/3, line%(N/3));
			break;
		}
	}
}