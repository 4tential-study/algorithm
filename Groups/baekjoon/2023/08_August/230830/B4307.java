import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B4307 {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int T = Integer.parseInt(in.readLine());
    String[] input;
    while(T-- > 0){
      input = in.readLine().split(" ");

      int stick_length = Integer.parseInt(input[0]);
      int ant_count = Integer.parseInt(input[1]);
      
      int min = 0, max = 0;
      
      for(int i = 0; i < ant_count; i++){
        int value = Integer.parseInt(in.readLine());

        // 개미끼리 서로 만나서 이동 방향을 바꾼다고 해도,
        // 만난 개미들만 보았을 때 만나지 않은 것과 같은 결과를 얻을 수 있다.
        // 그러므로 개미들이 서로 만나는 것은 신경쓰지 않아도 된다고 판단하였다.

        // 해당 개미가 0으로 갔을 때의 걸린 시간과 stick_length으로 갔을 때 걸린 시간을 비교.
        // 짧은 것을 shorts, 긴 것을 longs로 두었다.
        int shorts = Math.min(value, stick_length-value);
        int longs = Math.max(value, stick_length-value);
        
        // shorts가 여태까지의 짧은 것 중에 길다면 min에 저장.
        // longs도 똑같이 연산.
        min = Math.max(min, shorts);
        max = Math.max(max, longs);
      }
      sb.append(min).append(" ").append(max).append("\n");
    }
    System.out.println(sb);
  }
}