import java.util.*;

public class prog_150367 {
	public static void main(String[] args) {
		long[] nums = {7,42,5};
		System.out.println(solution(nums));
	}
	public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i=0 ; i < numbers.length ; i++){
            answer[i] = (checkValidTree(convertToFull(toBin(numbers[i])))) ? 1 : 0;
        }
        return answer;
    }
    
    public static String toBin(long num){
        return Long.toBinaryString(num);
    }
    
    // H:1, NODE: 1
    // H:2, NODE: 1+2
    // H:3, NODE: 1+2+4
    // H:4, NODE: 1+2+4+8
    public static String convertToFull(String binary){
        int height = 0;
        int node = 0;
        while(binary.length() > node){
            height++;
            node+=Math.pow(2, height-1);
        }
       
        return "0".repeat(node-binary.length()) + binary;
    }
    
    
    public static boolean checkValidTree(String tree){
        if(tree.length() == 1) return true;
        int root_index = tree.length()/2;
        char root = tree.charAt(root_index);
        if (root=='0' && tree.contains("1")){
            return false;
            // 하나라도 1이면 유효하지않음
        }
        
        return checkValidTree(tree.substring(0,root_index)) && checkValidTree(tree.substring(root_index+1));
    }
}


    
    
   



