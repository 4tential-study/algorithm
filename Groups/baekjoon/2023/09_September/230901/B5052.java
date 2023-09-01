import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
// 트라이 활용함.
public class B5052 {
  static class Node{
    Integer nodeNum;
    boolean isEnd;
    Node[] child;

    Node(Integer num, boolean isEnd){
      this.nodeNum = num;
      this.isEnd = isEnd;
      child = new Node[10];
    }

    public void addChild(Node node){
      int num = (int)node.nodeNum;
      if(this.child[num] != null)  return;
      
      this.child[num] = node;
    }
  }
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int T = Integer.parseInt(in.readLine()); // 테스트 케이스 수

    Start_Testcase:
    while(T-- > 0){
      int count = Integer.parseInt(in.readLine());
      Node root = new Node(null, false);
      String[] tel_numbers = new String[count];
      for(int i = 0; i < count; i++)  tel_numbers[i] = in.readLine();

      Arrays.sort(tel_numbers);

      for(int i = 0; i < count; i++){
        String tel_number = tel_numbers[i];
        Node search = root;
        for(int idx = 0; idx < tel_number.length(); idx++){
          int num = (int)(tel_number.charAt(idx) - '0');
          Node node = new Node(num, false);
          search.addChild(node);
          search = search.child[num];
          if(search.isEnd) {
            sb.append("NO").append("\n"); continue Start_Testcase;
          }
          // 현재 탐색하는 문자열의 끝부분이면, 후에 이 값을 만나면 바로 해당 전화번호로 전화가 간다.
          // 이름 탐지하기 위해 true값을 준다.
          if(idx == tel_number.length()-1)  search.isEnd = true;
        }
      }
      sb.append("YES").append("\n");
    }

    System.out.println(sb);
  }
}
