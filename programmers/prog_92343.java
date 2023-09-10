
import java.util.*;

public class prog_92343 {
    static ArrayList<Integer>[] childs;
    static int[] INFO;
    static int[][] EDGES;

    public int solution(int[] info, int[][] edges) {
        INFO = info;
        EDGES = edges;

        childs = new ArrayList[info.length];
        for(int i=0 ; i < childs.length ; i++){
            childs[i] = new ArrayList<>();
        }
        for(int i=0 ; i < edges.length ; i++){
            int p = edges[i][0];
            int c = edges[i][1];
            childs[p].add(c);
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);

        dfs(0,0,0,list);
        return maxSheep;
    }
    static int maxSheep=0;
    public void dfs(int idx, int sheep, int wolf , ArrayList<Integer> nexts){
        if(INFO[idx] == 0) sheep++;
        else wolf++;

        if(wolf >= sheep) return;
        //최대 양 수
        maxSheep = Math.max(maxSheep, sheep);
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(nexts);
        list.remove(Integer.valueOf(idx));

        for(Integer each : childs[idx]){
            list.add(each);
        }

        for(int next : list){
            dfs(next, sheep, wolf, list);
        }
    }
}




