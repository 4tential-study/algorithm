package week1009;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class baekjoon_9935 {
	
	static boolean[] isDisable;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		char[] str = in.readLine().toCharArray();
		char[] target = in.readLine().toCharArray();
		isDisable = new boolean[str.length];
		Stack<Character> stack = new Stack<>();
		Stack<Character> checkStack = new Stack<>();
		
		int sameCount = target.length-1;
		for(int i=0; i<str.length; i++) {
			stack.push(str[i]);

			while(stack.peek()==target[sameCount]) {
				checkStack.push(stack.pop());
				if(sameCount--==0 || stack.isEmpty()) break;
				
			}
			
			if(sameCount!=target.length-1) {
				if(sameCount!=-1) {
					int size = target.length-1 - sameCount;
					for(int j=0; j<size; j++) {
						stack.push(checkStack.pop());
					}
				}
				checkStack.clear();
				sameCount = target.length-1;
			}			
		}
		
		StringBuffer result = new StringBuffer();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(Character c : stack) {
			result.append(c);
		}
		
		if(result.toString().equals("")) System.out.println("FRULA");
		else out.write(result.toString()); out.close();
		
		
	}
}