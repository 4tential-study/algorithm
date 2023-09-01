import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
// 백준 2565. 전깃줄
public class B2565{
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int line_count = Integer.parseInt(in.readLine());
    ArrayList<Pair> lines = new ArrayList<>();  // 전깃줄 출발지, 도착지
    lines.add(new Pair(0, 0));  // 추후에 진행할 연산을 편하기 위해 삽입
    for(int i = 0; i < line_count; i++){
      String[] str = in.readLine().split(" ");
      lines.add(new Pair(Integer.parseInt(str[0]), Integer.parseInt(str[1])));
    }
    Collections.sort(lines); // 출발지 A의 위치를 기준으로 오름차순 sort
    int[] DP = new int [line_count+2];
    DP[0] = 0;
    int max_length = Integer.MIN_VALUE;
    /* 
     * 문제의 예제로 보았을 때, 위와 같이 정렬을 하면 다음과 같이 적을 수 있다. (도착지 index만 출력)
     * 8 2 9 1 4 6 7 10
     * 이를 수열이라고 생각하고, 오름차순으로 정렬된 가장 긴 부분수열을 찾는 것이 문제의 해답이다.
     * LIS 문제를 풀어보았다면 바로 이해할 것이다.
    */
    for(int i = 1; i < lines.size(); i++){
        int value = lines.get(i).lineB;
        int max_DP = 0;
        for(int j = 0; j < i; j++){
            int compareValue = lines.get(j).lineB;
            if(value > compareValue && max_DP < DP[j]){
                max_DP = DP[j];
            }
        }
        DP[i] = max_DP + 1;
        max_length = (max_length < DP[i]) ? DP[i] : max_length;
    }

    System.out.println(line_count - max_length);
  }
}

class Pair implements Comparable<Pair>{
  int lineA;
  int lineB;
  Pair(int a, int b){
    lineA = a;  lineB = b;
  }
  @Override
  public String toString() {
    return "Pair [lineA=" + lineA + ", lineB=" + lineB + "]";
  }
  @Override // 오름차순
  public int compareTo(Pair o) {
    return this.lineA - o.lineA;
  }
}