import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class boj_5052 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int i=0 ; i < T ; i++){
			int n = Integer.parseInt(br.readLine());
			Trie trie = new Trie();
			String minS = "0000000000";
			for(int j=0 ; j < n ; j++){
				String s = br.readLine();
				trie.insert(s);
				if(minS.length() >= s.length()){
					minS = s;
				}
			}

			if(trie.search(minS)==null) {
				System.out.println("YES");
			} else System.out.println("NO");

		}
	}

	
	public static class Node{
		HashMap<Character, Node> child = new HashMap<>();
		boolean isEnd;


	}
	public static class Trie{
		Node root = new Node();

		void insert(String str){
			Node node = this.root;
			for(char ch : str.toCharArray()){
				node = node.child.computeIfAbsent(ch, key -> new Node());
			}
			node.isEnd = true;
		}

		Node search(String str){
			Node node = this.root;
			for(char ch : str.toCharArray()){
				node = node.child.getOrDefault(ch, null);
				if(node == null) return null;
			}
			return node;
		}
	}
}
