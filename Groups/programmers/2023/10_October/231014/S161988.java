class Solution {
    public long solution(int[] sequence){
        int[] a = sequence.clone();
        int[] b = sequence.clone();

        int length = sequence.length;
        for(int i = 0; i < length; i++){
            int mul = (i % 2 == 0)? 1 : -1;
            a[i] *= mul;    b[i] = a[i] * (-1);
        }

        long answer = calc(a);
        answer = Math.max(answer, calc(b));

        return answer;
    }

    public long calc(int[] arr){
        long sum = arr[0];
        long result = arr[0];
        int length = arr.length;

        for(int i = 1; i < length; i++){
            sum = Math.max(arr[i], sum + arr[i]);
            result = Math.max(sum, result);
        }

        return result;
    }
}