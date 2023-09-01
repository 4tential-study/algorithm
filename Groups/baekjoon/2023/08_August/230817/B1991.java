import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B1991{
  static class Node{
    char node_name;
    Node left, right;

    Node(char name){
      node_name = name;
      left = right = null;
    }
  }

  static ArrayList<Node> list;
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    byte node_count = Byte.parseByte(in.readLine());
    list = new ArrayList<>(node_count);
    for(byte i = 0; i < node_count; i++)  list.add(new Node((char)(i + 'A')));

    for(byte i = 0; i < node_count; i++){
      String[] input = in.readLine().split(" ");
      byte node_num = (byte)(input[0].charAt(0) - 'A');
      
      if(!input[1].equals("."))
        list.get(node_num).left = list.get(input[1].charAt(0) - 'A');
      
      if(!input[2].equals("."))
        list.get(node_num).right = list.get(input[2].charAt(0) - 'A');
    }
    for(int i = 0; i < 3; i++)  sb.append(Order(list.get(0), i)).append("\n");
    System.out.println(sb);
  }

  static String Order(Node node, int mode){
    StringBuilder sb = new StringBuilder();
    String left = "", right = "", parent = String.valueOf(node.node_name);
    if(node.left != null) left = Order(node.left, mode);
    if(node.right != null) right = Order(node.right, mode); 

    // 전위, 중위, 후위는 부모 노드의 data를 언제 접근하느냐만 생각하면 된다.
    switch(mode){
      case 0: // 전위
        sb.append(parent).append(left).append(right);
        break;
      case 1: // 중위
        sb.append(left).append(parent).append(right);
        break;
      case 2: // 후위
        sb.append(left).append(right).append(parent);
        break;
    }
    return sb.toString();
  }
}