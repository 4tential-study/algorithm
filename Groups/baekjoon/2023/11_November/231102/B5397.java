import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class B5397 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<Character> list = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case = 0; test_case < T; test_case++) {
			char[] input = in.readLine().toCharArray();
			int length = input.length;
			list.clear();	int cursor = 0;
			for(int i = 0; i < length; i++) {
				switch(input[i]) {
				case '<':
					--cursor;
					if(cursor < 0) cursor = 0;
					break;
				case '>':
					++cursor;
					if(cursor > (list.size()))	cursor = list.size();
					break;
				case '-':
					if(cursor > 0) {
						list.remove(--cursor);
						if(cursor < 0) cursor = 0;
					}
					break;
				default:
					list.add(cursor++, input[i]);
					break;
				}
			}
			
			Character[] answer = list.toArray(new Character[list.size()]);
			int size = list.size();
			for(int i = 0; i < size; i++) sb.append(answer[i]);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}