import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B3079 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] input = in.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // 심사대 수
        int M = Integer.parseInt(input[1]); // 입국심사 인원 수

        long[] time = new long[N];

        long min = Long.MAX_VALUE, max = 0;
        for(int i = 0; i < N; i++){
            time[i] = Long.parseLong(in.readLine());
            max = Math.max(max, time[i]);
            min = Math.min(min, time[i]);
        }

        long left = min, right = max * M;
        long answer = Long.MAX_VALUE;
        while(left <= right){
            long mid = (left + right)/2;

            long count = 0;
            for(int i = 0; i < N; i++)  {
                count += (mid / time[i]);
                if(count >= M)  break;
            }
            if(count >= M){
                answer = Math.min(mid, answer);
                right = mid - 1;
            }
            else left = mid + 1;
        }

        System.out.println(answer);
    }    
}