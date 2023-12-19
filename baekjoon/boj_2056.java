import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class boj_2056 {

    static int N;

    static int[] time;
    static int[] finishTime;

    static List<Integer>[] adjList;

    static int[] inDegree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        time = new int[N+1];
        finishTime = new int[N+1];

        adjList = new ArrayList[N+1];
        for(int i = 0; i <= N; ++i) adjList[i] = new ArrayList<>();

        inDegree = new int[N+1];

        String[] str;
        for(int i = 1; i <= N; ++i){
            str = br.readLine().split(" ");

            time[i] = Integer.parseInt(str[0]);
            inDegree[i] = Integer.parseInt(str[1]);

            for(int j = 0; j < inDegree[i]; ++j){
                int c = Integer.parseInt(str[2 + j]);

                // c 다음에 i 진행
                adjList[c].add(i);
            }
        }

        Queue<Integer> q = new ArrayDeque<>();

        for(int i = 1; i <= N; ++i){
            if(inDegree[i] == 0){
                q.offer(i);
                finishTime[i] = time[i];
            }
        }

        while (!q.isEmpty()){
            int now = q.poll();

            for(int i = 0; i < adjList[now].size(); ++i) {
                int next = adjList[now].get(i);
                int nextTime = finishTime[now] + time[next];

                finishTime[next] = Math.max(finishTime[next], nextTime);

                if(--inDegree[next] == 0){
                    q.offer(next);
                }
            }
        }

        int answer = 0;
        for(int i = 1; i <= N; ++i){
            answer = Math.max(answer, finishTime[i]);
        }

        System.out.println(answer);
    }
}
