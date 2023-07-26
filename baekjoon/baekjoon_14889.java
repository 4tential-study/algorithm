import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
	static int N;
	static int[][] S;
	static int teamSize;
	static int min=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		teamSize = N/2;
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		recursion(0, new int[N], teamSize, teamSize);
		
		System.out.println(min);
	}
	
	
		
		// team 배열은 각 인덱스가 선수를 뜻하며
		// 값에 따라 팀을 할당한다. 0 : 미할당, 1 : 스타트팀, 2 : 링크팀
		// 현재까지 팀에 할당된 인원 수에 따라 할당 여부를 결정한다. ( int sTeamCnt, int lTeamCnt )
		// 
		// 한 경우를 탐색했다면 배열에 할당된 값으로 팀 편성을 확인하고
		// 저장한 능력치표 S를 조회해 두 팀의 총 능력치를 계산해 비교, 결과를 출력한다.
	static void recursion(int idx, int[] team, int sTeamCnt, int lTeamCnt) {
		
		if(idx>=N) {
			int sTeamSum=0;
			int lTeamSum = 0;
			for(int i=0; i<N; i++) {
				
				for(int j=i+1; j<N; j++) {
					if(team[i]==1 && team[j]==1) {
						sTeamSum += S[i][j] + S[j][i];
					}
					if(team[i]==2 && team[j]==2) {
						lTeamSum += S[i][j] + S[j][i];
					}
				}
			}
			min = Math.min(min,Math.abs(sTeamSum-lTeamSum));
			
			return;
		}
		
		
		if(sTeamCnt>0) {
			team[idx] = 1;
			recursion(idx+1, team, sTeamCnt-1,lTeamCnt);
			team[idx] = 0;
		}
		if(lTeamCnt>0) {
			team[idx] = 2;
			recursion(idx+1, team, sTeamCnt,lTeamCnt-1);
			team[idx] = 0;
		}
		
	}
	
}