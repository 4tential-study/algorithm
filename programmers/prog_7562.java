import java.util.*;

class prog_7562 {
    static int ans;
    static boolean[] visited;
    static HashMap<String, Integer> map = new HashMap<>();
    static int targetIdx;

    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];

        targetIdx = -1;
        for(int i=0 ; i < words.length ; i++){
            if(words[i].equals(target)) targetIdx = i;
        }
        if (targetIdx == -1 ) return 0;

        map.put(begin,0);
        for(String str  : words){ map.put(str, 0); }

        bfs(begin, target, words);
        return map.get(target);
    }

    public void bfs(String begin, String target , String[] words){
        Queue<String> queue = new LinkedList<>();
        queue.add(begin);

        while(!queue.isEmpty()){
            String pop = queue.poll();

            if(pop.equals(target)) break;

            for(int i=0; i < words.length ; i++){
                if (countDiff(words, i, pop) == 1 && !visited[i]) {
                    queue.add(words[i]);
                    visited[i] = true;
                    map.put(words[i], map.get(pop)+1);
                }
            }
        }
    }

    public int countDiff(String[] words, int i, String pop){
        int diff = 0;
        for(int j = 0 ; j < pop.length() ; j++){
            if(pop.charAt(j) != words[i].charAt(j)){
                diff++;
            }
        }
        return diff;
    }
}