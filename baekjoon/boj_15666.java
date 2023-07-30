import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_15666 {
    static StringTokenizer st;
    static BufferedReader br;
    static int N;
    static int M;
    static int[] array;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        array = new int[N];

        for(int i=0 ; i< N ; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);
        int[] temp = new int[M];
        perm(0,0, temp);

    }

    public static void perm(int prevIdx, int level, int[] temp){
        if(level == M){//4==4
            for(int element : temp){
                System.out.print(element + " ");
            }
            System.out.println();
            return;
        }

        int prev = 0;
        // 이전요소를 보관해서 현재선택요소과 동일할때는, 같은 배열이므로 넘어간다.
        for(int i = prevIdx ; i < N ; i++){
            if (prev != array[i]) {
                temp[level] = array[i];
                prevIdx = i;
                prev = temp[level];
                perm(i,level + 1, temp);
            }
        }
    }
}
