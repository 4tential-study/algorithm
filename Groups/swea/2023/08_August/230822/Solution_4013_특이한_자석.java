import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_4013_특이한_자석 {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    String[] input;
    int T = Integer.parseInt(in.readLine());
    for(int test_case = 1; test_case <= T; test_case++){
      sb.append("#").append(test_case).append(" ");
      int spin_count = Integer.parseInt(in.readLine()); // 자석 회전 횟수

      // 4개의 자석을 생성. 0번째 자석은 사용하지 않을 것이다.
      ArrayDeque<Integer>[] mag = new ArrayDeque[5]; // (반)시계 회전의 연산을 편하게 하기 위해 ArrayDeque
      for(int i = 0; i < 5; i++)   mag[i] = new ArrayDeque<Integer>();

      // 1번 자석부터 8개의 극을 입력받음.
      // 첫 번째로 입력받은 극이 빨간색 화살표 위치의 날이며 이후 시계방향 순서대로 입력받는다.
      for(int i = 1; i <= 4; i++){
        input = in.readLine().split(" ");
        for(int j = 0; j < input.length; j++)
          mag[i].offer(Integer.parseInt(input[j]));
      }

      // 자석들 회전 시작
      for(int count = 0; count < spin_count; count++){
        int[] spin = new int[5]; // 각 자석이 회전해야 할 방향.
        input = in.readLine().split(" ");
        int mag_num = Integer.parseInt(input[0]); // 회전할 자석의 번호
        spin[mag_num] = Integer.parseInt(input[1]); // 선택한 자석의 회전 방향

        boolean[] visited = new boolean[5]; // 탐색 유무
        visited[0] = true; // 0번은 탐색하지 않을 것이므로 미리 탐색 완료한 것으로 취급한다.

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(mag_num);

        while(!queue.isEmpty()){
          int search = queue.poll(); // 탐색을 시도할 자석 번호
          Integer[] Searcharray = mag[search].toArray(new Integer[mag[search].size()]);
          if(search + 1 <= 4 && !visited[search+1]){ // 오른쪽에 자석 유무 파악 & 탐색 유무 파악
            Integer[] right = mag[search+1].toArray(new Integer[mag[search+1].size()]);
            if(Searcharray[2] != right[6]){
              visited[search+1] = true;
              spin[search+1] = spin[search] * -1;
              queue.offer(search+1);
            }
          }
          if(search-1 > 0 && !visited[search-1]) { // 왼쪽에 자석 유무 파악 & 탐색 유무 파악
            Integer[] left = mag[search-1].toArray(new Integer[mag[search-1].size()]);
            if(Searcharray[6] != left[2]){
              visited[search-1] = true;
              spin[search-1] = spin[search] * -1;
              queue.offer(search-1);
            }            
          }
        }

        for(int i = 1; i <= 4; i++){
          if(spin[i] == 0) continue; // 회전하지 않은 자석이라면 continue
          boolean isCounter = (spin[i] < 0) ? true : false; // 반시계 유무 ( false 일 경우 시계방향으로 회전 )
          Clockwise(mag[i], isCounter);
        }
      } // 자석들 회전 끝

      int result = 0;
      for(int i = 1; i <= 4; i++){
        if(mag[i].peek() == 1) result += (1 << (i-1));
      }

      sb.append(result).append("\n");
    }
    System.out.println(sb);
  }

  // 자석을 회전시킨다. counter가 true일 경우 반시계 방향, false일 경우 시계 방향으로 회전한다.
  static void Clockwise(ArrayDeque<Integer> mag, boolean counter){
    if(counter){ // 반시계 방향 회전
      int value = mag.pop();
      mag.offer(value);
    }
    else{ // 시계방향 회전
      int value = mag.pollLast();
      mag.push(value);
    }
  }
}