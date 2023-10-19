import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class boj_2251 {
    static BufferedReader in;
    static int[] array = new int[3];
    static boolean[][][] visited;
    static Set<Integer> set = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        String[] ins = in.readLine().split(" ");
        array[0] = Integer.parseInt(ins[0]);
        array[1] = Integer.parseInt(ins[1]);
        array[2] = Integer.parseInt(ins[2]);
        visited= new boolean[201][201][201];

        bfs(2,array[2]);
        for (Integer integer : set) {
            System.out.print(integer + " ");
        }
    }

    public static void bfs(int x, int l) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {x,0,0,l});
        set.add(l);
        while(!q.isEmpty()) {
            int[] poll = q.poll();
            int a = poll[1];
            int b = poll[2];
            int c = poll[3];

            if(visited[a][b][c]) continue;
            visited[a][b][c] = true;
            if(a==0) set.add(c);

            if(a+b > array[1]) {
                q.offer(new int[] {1,(a+b)-array[1], array[1], c});
            }else q.offer(new int[] {1,0,b+a,c});
            if(c+a > array[2]) {
                q.offer(new int[] {2,(a+c)-array[2], b, array[2]});
            }else q.offer(new int[] {2,0,b,c+a});
            if(a+b > array[0] ) {
                q.offer(new int[] {0,array[0],(b+a)-array[0],c});
            }else q.offer(new int[] {0,a+b,0,c});
            if(b+c > array[2]) {
                q.offer(new int[] {2,a,(b+c)-array[2], array[2]});
            }else q.offer(new int[] {2,a,0,c+b});
            if(c+a > array[0]) {
                q.offer(new int[] {0,array[0],b,(c+a)-array[0]});
            } else	q.offer(new int[] {0,a+c,b,0});
            if(c+b > array[1]) {
                q.offer(new int[] {1,a,array[1],(c+b)-array[1]});
            } else q.offer(new int[] {1,a,b+c,0});

        }
    }
}