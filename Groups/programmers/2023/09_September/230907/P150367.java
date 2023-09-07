class Solution {
    public int[] solution(long[] numbers) {
      int[] answer = new int[numbers.length];
      int index = 0;
      for(long num : numbers){
        String binaryString = "";
        while(num > 0){
          binaryString = String.valueOf(num%2) + binaryString;
          num /= 2;
        }
        int length = shouldLength(binaryString.length());
        if(length != binaryString.length()){
          while(binaryString.length() < length){
            binaryString = "0" + binaryString;
          }
        }
 
        boolean bool = canBinaryTree(binaryString, binaryString.length()/2, binaryString.length()/2);
        answer[index++] = (bool) ? 1 : 0;
      }

      return answer;
    }

    // 이진 트리로 만들 수 있는 검증함.
    static boolean canBinaryTree(String str, int parent, int child_size){
      if(child_size == 0) return true;

      boolean existParent = (str.charAt(parent) == '1') ? true : false;

      int left_idx = parent - (child_size/2 + 1);
      int right_idx = parent + (child_size/2 + 1);

      // 부모가 0인데 자식이 1이면 이는 이진 트리로 만들 수 없으므로 false를 반환함.
      if(!existParent && (str.charAt(left_idx) == '1' || str.charAt(right_idx) == '1'))
        return false;

      // 왼쪽 subtree와 오른쪽 subtree가 만들어 질 수 있는 지 검사하여 둘 중 하나라도 false면
      // 해당 tree는 만들 수 없으므로 똑같이 false를 반환하기 위해 AND 연산을 return함.
      return canBinaryTree(str, left_idx, child_size/2) && canBinaryTree(str, right_idx, child_size/2);
    }

    // 해당 이진수가 가져야 할 자리수를 계산함.
    static int shouldLength(int length){
      /*
       * 2가 밑인 log로 계산함.
       * 예를 들어 length = 7인 경우,
       * log_2(length+1) 이라면 log_2(8) = 3.
       * log_2(length+1)이 정수인 경우, length은 2^n-1(n >= 1) 를 만족하므로 length을 반환. 
       * log_2(length+1)이 정수가 아니라면 log_2(length+1)을 log_2(length+1)보다 큰 가까운 정수로 바꾼 정수를 만들어 이를 a라고 하자.
       * 그 이후 2^a - 1 을 계산하여 return 하면 된다.
       */
      double log = Math.log(length+1) / Math.log(2); 
      if(log - (int)log != 0) {
        return (int)Math.pow(2,((int)log + 1)) - 1;
      }
      else return length;
    }
}