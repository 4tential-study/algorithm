import java.io.BufferedReader;
import java.io.InputStreamReader;
public class B1107 {
  private static final int CURRENT = 100; // 탐색 전 100번 채널에서 시작함.
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int target = Integer.parseInt(in.readLine()); // 목표 채널
		
		if(target == CURRENT) { // 이동하기 전과 목표가 같다면 연산하지말고 그냥 return 
			System.out.println(0); return;
		}
		
		int breaked = Integer.parseInt(in.readLine()); // 고장난 버튼 개수
		int justPM = Math.abs(target - CURRENT); // 100번 채널에서 + 혹은 - 만 하였을 때의 횟수
		int diff = 0;
		
		int temp = -1;
		String[] br_button;
		if(breaked == 0) { // 고장난 버튼이 없다면
			int digit = Integer.toString(target).length();
			System.out.println(Math.min(digit, justPM));
			return;
		}
		else if(breaked >= 10) { // 0 ~ 9까지 모두 고장났다면
			System.out.println(justPM);
			return;
		}
    // 고장난 버튼이 1개 이상 9개 이하인 경우 실행됨.
    // 리모컨의 0번 버튼 ~ 9번 버튼으로 목표 채널 N번과의 차이가 가장 작은 채널을 찾아볼 것이다.
    // 찾았다면, temp라는 변수에 저장.
		else { 
			br_button = in.readLine().split(" "); // 고장난 버튼들을 읽어들임.
			
			Search:
			while(diff <= justPM) { // +, - 만 했을 때의 횟수보다 적게 실행하기.
				String left;
				if(target - diff > 0) left = Integer.toString(target - diff);
				else left = "0";
				String right = Integer.toString(target + diff);
				
				boolean cantLeft = false, cantRight = false;
				for(int i = 0; i < breaked; i++) {
					if(!cantLeft && left.contains(br_button[i]))
						cantLeft = true;
					
					if(!cantRight && right.contains(br_button[i]))
						cantRight = true;
					
					if(cantLeft && cantRight) {
						diff++;	continue Search;
					}
				}
				
				if(!cantLeft) {
					temp = Integer.parseInt(left);
					break;
				}
				else if(!cantRight) {
					temp = Integer.parseInt(right);
					break;
				}
			}
		}
    // temp에 값이 저장되어 있다면 실행.
		if(temp != -1) {
			int digit = Integer.toString(temp).length(); // 0번 버튼 ~ 9번 버튼을 누른 횟수.
			int answer = Math.min(diff + digit, justPM); 
			System.out.println(answer);
		}

		else System.out.println(justPM);
	}
}