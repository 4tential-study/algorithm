package 프로그래머스_단어변환;

public class 프로그래머스_단어변환 {

	public static void main(String[] args) {
		String begin = "hit";
		String target = "cog";
		String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };
		Solution s = new Solution();
		System.out.println(s.solution(begin, target, words));
	}

}

class Solution {
	public static boolean[] visited;
	public static int answer=0; 
	public int solution(String begin, String target, String[] words) {
    	visited = new boolean[words.length];
    	dfs(begin,target,words,0);
    	return answer;
    }


	public static void dfs(String begin, String target, String[]words, int cnt) {
		if (begin.equals(target)) {
			answer = cnt;
			return;
		}
		for (int i=0;i<words.length;i++) {
			if (visited[i]) continue;
			int same_char=0;
			for (int j=0;j<begin.length();j++) {
				//charAt()은 봐도봐도 신기하게 생긴 듯...아직 적응이 안 됨.
				if (begin.charAt(j)==words[i].charAt(j)) {
					same_char++;
				}
			}
			if (same_char == begin.length()-1) {
				visited[i]=true;
				dfs(words[i],target,words,cnt+1);
				visited[i]=false;
			}
		}
	}
}