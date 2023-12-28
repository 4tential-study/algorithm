class prog_43163 {
    static int cnt=99999999;
    static boolean contain = false;
    static boolean[] visited;
    public int solution(String begin, String target, String[] words) {

        for(String w : words){
            if(w.equals(target)) contain = true;
        }
        visited = new boolean[words.length];
        if(!contain) return 0;
        dfs(words, begin, target, 0);
        return cnt;
    }

    public void dfs(String[] words, String cur, String target, int dep){
        if(dep >= words.length || cur.equals(target)){

            cnt = Math.min(dep, cnt);
            return;
        }

        for(int i=0 ; i < words.length ; i++){
            char[] curs = cur.toCharArray();
            char[] wordsChar = words[i].toCharArray();
            int diff = 0;
            for(int j=0 ;  j < curs.length ; j++){
                if(curs[j] != wordsChar[j]) diff++;
            }

            if(diff == 1 && !visited[i]) {
                visited[i] = true;
                dfs(words, words[i], target, dep+1);
                visited[i] = false;
            }
        }


    }

}