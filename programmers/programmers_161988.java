import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int left = 0;
        int right = 0;
        int partitionSum = sequence[0]; // 부분 수열의 합

        int n = sequence.length;

        List<SubSequence> subs = new ArrayList<>();
        while (true){
            // 현재 부분 수열의 합과 k가 일치하는 left와 right를 저장함
            if(partitionSum == k){
                subs.add(new SubSequence(left, right));
            }
            if(left == n && right == n) break;

            if(partitionSum <= k && right < n){
                right++;
                // 새로운 원소가 추가되었으므로, right에 위치하는 값을 부분 수열 합에 더함
                if(right < n) partitionSum += sequence[right];
            } else {
                // 기존의 left의 위치한 원소를 부분 수열의 합에서 제외
                if(left < n) partitionSum -= sequence[left];
                left++;
            }
        }

        // 조건에 가장 일치하는 부분 수열을 맨 앞으로 정렬
        subs.sort(SubSequence::compareTo);

        return new int[]{subs.get(0).left, subs.get(0).right};
    }
    
    private class SubSequence implements Comparable<SubSequence>{
        int left, right, size;

        public SubSequence(int left, int right) {
            this.left = left;
            this.right = right;
            this.size = right - left;
        }

        @Override
        public int compareTo(SubSequence o) {
            if(this.size == o.size){
                return this.left - o.left;
            }
            return this.size - o.size;
        }
    }
}