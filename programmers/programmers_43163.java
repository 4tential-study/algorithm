import java.util.*;

class Solution {

    static int N;
    
    static String _target;
    static String[] _words;
    static List<Integer>[] adjList;
    
    static int minDist;
    static boolean[] visited;
    
    public int solution(String begin, String target, String[] words) {

        N = words.length + 1;
        
        _target = target;
        _words = new String[N];
        
        for(int i = 0; i < words.length; ++i){
            _words[i] = words[i];
        }
        _words[words.length] = begin;
        
        /********** 간선리스트 만들기 Start **********/
        adjList = new ArrayList[N];
        for(int i = 0; i < N; ++i){
            adjList[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < N - 1; ++i){
            for(int j = i + 1; j < N; ++j){
                if(adjCheck(_words[i], _words[j])){
                    adjList[i].add(j);
                    adjList[j].add(i);
                }
            }
        }
        /********** 간선리스트 만들기 End **********/
        
        // _words[N-1]로 시작해서 탐색
        minDist = Integer.MAX_VALUE;
        visited = new boolean[N];
        visited[N - 1] = true;
        DFS(N - 1, 0);
        
        // 이동불가
        if(minDist == Integer.MAX_VALUE) minDist = 0;
        
        return minDist;
    }
    
    // 백트래킹으로 탐색(최단거리 찾기)
    public static void DFS(int now, int dist){
        // target으로 변환이 되었는가?
        if(_words[now].equals(_target)){
            if(dist < minDist) minDist = dist;
            return;
        }
        
        for(int i = 0; i < adjList[now].size(); ++i){
            int next = adjList[now].get(i);
            
            if(visited[next]) continue;
            
            visited[next] = true;
            DFS(next, dist + 1);
            visited[next] = false;
        }
    }
    
    // a와 b가 변환될 수 있으면 True 리턴
    public static boolean adjCheck(String a, String b){
        int cnt = 0;
        for(int i = 0; i < a.length(); ++i){
            if(a.charAt(i) != b.charAt(i)) ++cnt;
        }
        if(cnt == 1) return true;
        return false;
    }
}