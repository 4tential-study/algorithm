
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_4963 {
    static int[][] island; // 섬 배열
    static int[] dx = {-1, 1, 0, 0, 1, 1, -1, -1}; // 8 방향 이동을 위한 dx
    static int[] dy = {0, 0, 1, -1, 1, -1, 1, -1}; //
    static int answer = 0; // 정답
    static Queue<islandPoint> q = new LinkedList<islandPoint>(); // bfs 를 위한 queue

    static int n, m;

    public static void bfs() {
        while (!q.isEmpty()) { // queue 가 빌때까지 반복
            islandPoint now = q.poll(); // queue 에서 하나를 꺼내서 now 변수에 저장

            for (int i = 0; i < 8; i++) {
                int nx = now.x + dx[i]; // 다음 좌표는 현재 좌표 + 8방향 중 한곳으로 이동한 x 값
                int ny = now.y + dy[i]; // 다음 좌표는 현재 좌표 + 8방향 중 한곳으로 이동한 y 값

                // island[nx][ny] 가 1 인 곳만 방문가능
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && island[nx][ny] == 1) {
                    island[nx][ny] = 0; // 방문했으면 0 으로
                    q.offer(new islandPoint(nx, ny)); // 그리고 현재 좌표를 queue 에 담아서 넘김
                }
            }
        }
    }

    public static void findIslandNum() { // 반복문을 위한 메서드
        // 전체 배열만큼 반복
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // 배열에서 1 == 섬이 있다면 해당 지점을 방문과 동시에 queue 에 저장
                if (island[i][j] == 1) {
                    answer++; // 섬을 하나 찾았으니까 +1
                    q.add(new islandPoint(i, j)); // queue 에 저장
                    island[i][j] = 0; // 방문한 곳을 기억하기 위해 0 으로 값 변경
                    bfs(); // bfs 시작
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            // 입력 예제가 굉장히 괴랄함...
            // 이 문제의 입력은 0 0 이 들어올때까지 즉 n 과 m 모두 0 이 두번 들어올때까지 무한정 반복됨
            // 때문에 무한 반복문 시작
            StringTokenizer st = new StringTokenizer(br.readLine()); // n 과 m 을 입력받아서 각각 나눠서
            m = Integer.parseInt(st.nextToken()); // m 과
            n = Integer.parseInt(st.nextToken()); // n 에 저장
//            System.out.println("n : " + n);
//            System.out.println("m : " + m);


            if(n == 0 && m == 0){ // n 과 m 이 모두 0 이면 반복문 종료
                break;
            }

            island = new int[n][m]; // m 과 n 으로 이루어진 배열 생성

            for (int i = 0; i < n; i++) {
                // 지도 입력 시작
                st = new StringTokenizer(br.readLine()); // 입력받은 값을 하나하나 나눠서
                for (int j = 0; j < m; j++) {
                    island[i][j] = Integer.parseInt(st.nextToken()); // 배열에 저장

//                System.out.print(island[i][j] +" ");
                }
            }
            findIslandNum(); // 저장이 완료되면 bfs 를 위한 반복문이 있는 함수가 시작

            System.out.println(answer); // 메서드 종료후 정답 출력
            answer = 0; // 정답 초기화
        }


    }


}


class islandPoint {
    int x, y;

    islandPoint(int x, int y) {

        this.x = x;
        this.y = y;
    }

}