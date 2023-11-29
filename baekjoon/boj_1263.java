import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class boj_1263 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<int[]> list = new ArrayList<>();

        String[] str;
        for(int i = 0; i < N; ++i) {
            str = br.readLine().split(" ");
            int t = Integer.parseInt(str[0]);
            int s = Integer.parseInt(str[1]);

            list.add(new int[] {t, s});
        }

        list.sort((a, b) -> b[1] - a[1]);

        // 다음 일이 시작해야 하는 시간
        int nextStartTime = 1000001;
        int startTime = 0;

        for(int i = 0; i < list.size(); ++i){
            startTime = list.get(i)[1] - list.get(i)[0] + 1;

            if(list.get(i)[1] >= nextStartTime){
                startTime -= (list.get(i)[1] - nextStartTime + 1);
            }

            if(startTime < 0){
                startTime = 0;
                break;
            }

            nextStartTime = startTime;
        }

        System.out.println(startTime - 1);
    }
}
