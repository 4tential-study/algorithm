import java.util.*;

class prog_43162 {
    static int[] parent;
    static boolean[][] visited;
    static HashSet<Integer> set = new HashSet<>();
    
    public int solution(int n, int[][] computers) {
        parent = new int[n+1];
        visited= new boolean[n][n];      
        
        for(int i=1 ; i <= n ; i++){
            parent[i] = i;
        }
        
        for(int i=0 ; i < n ; i++){
            for(int j=0 ; j < n ; j++){
                if(i!=j && computers[i][j] == 1 ) {
                    for(int z = 1 ; z <= n ; z++){
                        find(z);
                    }
                    union(i+1,j+1);
                    
                }
            }
        }
        for(int i=1 ; i <= n ; i++){
            set.add(parent[i]);
        }
        return set.size();
    }
    
    public int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    public boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        parent[bRoot] = aRoot;
        return true;
    }
}