class Solution {
    
    // 문자열 A가 현재 문자열, 문자열 T가 타겟 문자열
    // 취소-> 완탐으로 가자 : 문자열 A가 T와 글자가 같은 개수 < 문자열 B가 T와 글자가 같은 개수
    // 문자열 A와 B가 한 글자만 일치하는지 확인
    // 방문 여부 체크 필요
    
    // DFS로 구현
    static String begin, target;
    static String[] words;
    static boolean[] visited;
    static int answer=9999;
    
    public int solution(String begin, String target, String[] words) {
                
        visited = new boolean[words.length];
        
        this.begin = begin;
        this.target = target;
        this.words = words;
        
        DFS(0, begin);
        if(answer==9999) answer = 0;
        
        return answer;
    }
    void DFS(int cnt, String curStr){
        if(curStr.equals(target)){
            answer = Math.min(answer, cnt);
            return;
        }
        if(cnt==words.length){
            return;
        }
        
        for(int i=0; i<words.length; i++){
            // curStr이 현재 검사 중인 문자열과 하나만 다른지 체크
            if(!visited[i] && isSimilar(curStr, words[i])){
                visited[i] = true;
                DFS(cnt+1, words[i]);
                visited[i] = false;
            }
        }
        
    }
    boolean isSimilar(String curStr, String nxStr){
        int cnt=0;
        for(int i=0; i<curStr.length(); i++){
            if(curStr.charAt(i)!=nxStr.charAt(i)){
                if(++cnt > 1) return false;
            }
        }
        if(cnt==0) return false;
        
        return true;
    }
}