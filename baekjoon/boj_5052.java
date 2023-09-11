import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class boj_5052 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0 ; i < T ; i++){
			boolean contain = true;
			int n = Integer.parseInt(br.readLine());//3
			Trie trie = new Trie();
//			String minS = "0000000000";
			ArrayList<String> list = new ArrayList<>();
			for(int j=0 ; j < n ; j++){
				String s = br.readLine();
				
				list.add(s);
				
				trie.insert(s);	
			}
			for(String k : list) {
				if(trie.contains(k)) {
					contain = false;
					break;
				}
			}
			
			
			
			
			System.out.println(contain ? "YES":"NO");
			
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

		boolean contains(String word) {
			Node node = this.root;
			for(int i=0 ; i < word.length() ; i++) {
				char c = word.charAt(i);
				Node tnode = node.child.get(c);
				if(tnode== null) {
					return false;
				}
				node = tnode;
			}
			
			if(node.isEnd) {
				if(node.child.isEmpty()) {
					return false;
				}
			}
				return true;
				
				
		}
	}
}
