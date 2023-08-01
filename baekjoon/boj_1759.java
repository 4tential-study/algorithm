import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj_1759 {
	static BufferedReader br;
	static boolean[] visited;
	static char[] array;
	static int l;
	static int c;
	static HashSet<String> set = new HashSet<>(Arrays.asList("a", "e", "i", "o", "u")); 
	 
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		String[] splits= br.readLine().split(" ");
		
		 l = Integer.parseInt(splits[0]);
		c = Integer.parseInt(splits[1]);
		array = new char[c];
		visited = new boolean[c];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0 ; i < c ; i++) {
			array[i]= st.nextToken().charAt(0);
		
		}
		Arrays.sort(array);
		char[] temp = new char[l];
		perm(0,0, temp);
	}
	
	public static void perm(int prev, int level, char[] temp) {
		if(level == l) {
			int[] containLetter = new int[2];
			for(char each : temp) {
				if(set.contains(String.valueOf(each))) containLetter[0]+=1;
				else containLetter[1] += 1;
			}
			
			if (containLetter[0] >= 1  && containLetter[1] >= 2 ) {
				print(temp);
			}
			return;
		}
		
		for(int i= prev ; i < c ; i++) {
			if (!visited[i]) {
				visited[i] = true;
				temp[level] = array[i];
				prev = i;
				perm(prev, level+1, temp);
				visited[i] = false;
				
			}
		}
	}
	
	public static void print(char[] temp) {
		for(char each : temp) {
			System.out.print(each);
		}
		System.out.println();
	}

}
