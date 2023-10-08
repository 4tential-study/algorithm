import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_16562 {
    static StringTokenizer st;
    static BufferedReader in;

    static int n;
    static int m;
    static int k;
    static int[] parents;
    static int[] cost;

    public static void main(String[] args) throws IOException {
        in  = new BufferedReader(new InputStreamReader(System.in));

        String[] ins = in.readLine().split(" ");
        n = Integer.parseInt(ins[0]); //학생 수
        m = Integer.parseInt(ins[1]); //관계 수
        k = Integer.parseInt(ins[2]); //돈
        cost = new int[n+1];
        st = new StringTokenizer(in.readLine());
        for(int i=1 ; i <= n ; i++){
            cost[i] = Integer.parseInt(st.nextToken());
        }

        //init
        parents = new int[n+1];
        for(int i=1 ; i <=n ; i++){
            parents[i] = i;
        }

        //union-find
        for(int i=0 ; i < m ; i++){
            String[] inputs = in.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            union(a,b);
        }

        //answer
        int ans = 0;
        for(int i = 1 ; i <= n ; i++){
            if(parents[i] == i) ans += cost[i];
        }

        if(ans > k) System.out.println("Oh no");
        else System.out.println(ans);
    }

    public static boolean union(int a , int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;
        //최소비용인 친구를 부모로 올리기
        if(cost[aRoot] < cost[bRoot]) parents[bRoot]= aRoot;
        else parents[aRoot] = bRoot;
        return true;
    }

    public static int find(int x){
        if(parents[x] == x ) return x;
        return parents[x] = find(parents[x]);
    }

}
