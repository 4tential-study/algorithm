import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B15922 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; 
        int N = Integer.parseInt(in.readLine()); // 수직전 위에 그릴 선분의 개수

        int result = 0;

        // 이미 sort 된 입력값들을 받기 때문에 sort는 따로 거치지 않았다.
        st = new StringTokenizer(in.readLine());
        int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken());

        // 각 줄에 좌표를 나타내는 정수쌍 (x1, x2)
        for(int i = 1; i < N; i++){
            st = new StringTokenizer(in.readLine());
            int input_start = Integer.parseInt(st.nextToken());
            int input_end = Integer.parseInt(st.nextToken());

            // 저장된 end보다 input으로 들어온 start가 더 높으면
            // 겹치지 않는 새로운 선분이므로 해당 연산을 진행한다.
            if(input_start > end){
                result += end-start;
                start = input_start;
                end = input_end;
            }
            else{
                start = Math.min(start, input_start);
                end = Math.max(end, input_end);
            }
        }

        // 현재 입력된 end와 start는 아직 연산을 하지 않은 값이므로 마지막으로 연산을 한 번 더 한다.
        result += end - start; 

        System.out.println(result);
    }
}
