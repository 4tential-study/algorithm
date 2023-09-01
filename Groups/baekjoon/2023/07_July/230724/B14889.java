import java.io.*;
import java.util.*;
/*
 * 1. solve 메서드에서 선택한 선수를 selected에 add한다.
 * 2. selected에 등록된 선수가 전체 인원의 절반이 되었을 경우, 선택되지 않은 선수 모두를 not_selected에 넣는다.
 * 3. 각 팀의 점수를 계산하여 두 팀간의 차이를 기존의 값, result과 비교하여 최소값을 저장한다.
 * 4. 이후 백트래킹을 이용하여 완전 탐색을 진행한다.
 */
public class B14889 {
	static int[][] board;
	static ArrayList<Integer> selected = new ArrayList<Integer>();
	static ArrayList<Integer> not_selected = new ArrayList<Integer>();
	static int result = Integer.MAX_VALUE, people; // 결과 값, 총 팀원 수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		people = Integer.parseInt(br.readLine());
		
		board = new int[people][people];
		
		for(int i = 0; i < people; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < people; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solve(0);
		System.out.println(result);
	}
	
	static void solve(int index) {
		if(selected.size() == people/2) {
			not_selected = makeTeamB(selected); // selected에 없는 선수들로 ArrayList 생성.
			int teamA = calc_score(selected); // teamA의 점수
			int teamB = calc_score(not_selected); // teamB의 점수

			result = Math.min(result, Math.abs(teamA - teamB));
			return;
		}
		
		// 백트래킹
		for(int i = index; i < people; i++) {
			selected.add(i); // i번 선수 추가
			solve(i+1);
			selected.remove(Integer.valueOf(i)); // i번 선수 제거
		}
	}
	
	// 팀 점수 계산
	static int calc_score(ArrayList<Integer> team) {
		int sum = 0;
		for(int i = 0; i < team.size(); i++) {
			for(int j = 0; j < team.size(); j++) {
				if(i == j)	continue;
				sum += board[team.get(i)][team.get(j)];
			}
		}
		return sum;
	}
	
	// not_selected 생성.
	static ArrayList<Integer> makeTeamB(ArrayList<Integer> teamA){
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i = 0; i < people; i++) {
			if(temp.size() == people/2)	break;
			if(!teamA.contains(i)) temp.add(i);
		}
		
		return temp;
	}
}