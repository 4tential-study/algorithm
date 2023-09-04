package Algorithm.boj.day0901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class baekjoon_5052 {
	
	
	private static class Node{
		Map<Character, Node> child = new HashMap<Character, Node>();
		boolean isTerminal;
		@Override
		public String toString() {
			return "Node [child=" + child + ", isTerminal=" + isTerminal + "]";
		}
		
	}
	
	
	private static class Trie{
		Node root = new Node();
		
		void insert(String str) {
			Node node = this.root;
			for(char ch : str.toCharArray()) {
				node = node.child.computeIfAbsent(ch, key->new Node());
			}
			node.isTerminal = true;
		}
		
		Node search(String str) {
			Node node = this.root;
			for(char ch : str.toCharArray()) {
				node = node.child.getOrDefault(ch, null);
				if(node == null) return null;
			}
			return node;
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		testcase : for(int tc=1; tc<=T; tc++) {
			Trie trie = new Trie();
			int N = Integer.parseInt(in.readLine());
			String[] numbers = new String[N];
						
			for(int i=0; i<N; i++) {
				numbers[i] = in.readLine();
				trie.insert(numbers[i]);
			}
			
			for(int i=0; i<N; i++) {
				int size = trie.search(numbers[i]).child.size();
				if(size!=0) {
					System.out.println("NO");
					continue testcase;
				}
			}
			
			System.out.println("YES");
		}
	}
}
