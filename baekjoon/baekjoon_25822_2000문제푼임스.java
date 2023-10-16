import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Streak{
		int solveCount;
		int notSolveCount;
		public Streak(int solveCount, int notSolveCount) {
			super();
			this.solveCount = solveCount;
			this.notSolveCount = notSolveCount;
		}
		@Override
		public String toString() {
			return "Streak [solveCount=" + solveCount + ", notSolveCount=" + notSolveCount + "]";
		}
				
	}
	
	static int C;
	static int N;
	static int[] solved;
	static List<Streak> streaks = new ArrayList<>();
	
	static int maxStreak, maxSolved;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		C = (int) (Double.parseDouble(in.readLine()) / 0.99);
		C = C >=2 ? 2 : C;
		N = Integer.parseInt(in.readLine());
		solved = new int[N];
		
		streaks.add(new Streak(0, 0));
				
		st = new StringTokenizer(in.readLine());
		for(int i=0; i<N; i++) {
			solved[i] = Integer.parseInt(st.nextToken());
			maxSolved = Math.max(maxSolved, solved[i]);

			int size = streaks.size();
			if(solved[i]==0) {
				// 이전 스트릭이 존재하면 count값 증가
				streaks.get(size-1).notSolveCount++;
				// 존재하지 않으면 아무것도 하지 않음
			}else {
				if(i!=0 && solved[i-1]==0) {
					streaks.add(new Streak(1, 0));
				}else {
					streaks.get(size-1).solveCount++;
				}
			}
		}
		
		for(int i=0; i<streaks.size(); i++) {
			int c = C;
			int streakCount = 0;
			
			for(int j=i; j<streaks.size(); j++) {
				Streak s = streaks.get(j);
				if(s.notSolveCount <= c) {
					c -= s.notSolveCount;
					streakCount += s.notSolveCount + s.solveCount;
				}else {
					streakCount += s.solveCount + c;
					break;
				}
			}
			
			maxStreak = Math.max(maxStreak, streakCount);
		}
				
		System.out.println(maxStreak);
		System.out.println(maxSolved);
				
	}
}