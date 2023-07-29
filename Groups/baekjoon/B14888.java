import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14888 {
    static int[] operator, numbers;
    static int MAX = Integer.MIN_VALUE, MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            numbers[i] = Integer.parseInt(st.nextToken());

        operator = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++)
            operator[i] = Integer.parseInt(st.nextToken());

        solve(1, numbers[0]);
        System.out.printf("%d\n%d", MAX, MIN);
    }

    static void solve(int idx, int result) {
        if(idx == numbers.length){
            MIN = (result < MIN) ? result : MIN;
            MAX = (result > MAX) ? result : MAX;
            return;
        }
        
        for(int i = 0; i < 4; i++){
            if(operator[i] > 0){
                operator[i]--;
                switch(i){
                    case 0:
                        solve(idx+1, result + numbers[idx]);    break;
                    case 1:
                        solve(idx+1, result - numbers[idx]);    break;
                    case 2:
                        solve(idx+1, result * numbers[idx]);    break;
                    case 3:
                        solve(idx+1, result / numbers[idx]);    break;
                }
                operator[i]++;
            }
        }
    }
}
