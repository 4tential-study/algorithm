import java.io.*;
import java.util.*;

public class boj_2343 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 강의 수
        int m = Integer.parseInt(st.nextToken()); // 블루레이개수
        int[] lessonList = new int[n];

        int left = 0;
        int right = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            lessonList[i] = Integer.parseInt(st.nextToken());
            right += lessonList[i]; //총합
            left = Math.max(left, lessonList[i]); //최댓값
        }

        while (left <= right) {
            int mid = (left + right) / 2;
            // mid -> 블루레이의 길이
            int count = getCount(n, lessonList, mid);
            // count -> mid에 따라 만들어지는 강의 개수
            if(count > m){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        System.out.println(left);
    }

    private static int getCount(int n, int[] lessonList, int mid) {
        //블루레이의 길이에 따라 만들어지는 강의수 체크
        int sum = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (sum + lessonList[i] > mid) {
                sum = 0;
                count++;
            }
            sum += lessonList[i];
        }

        if(sum != 0) count++;
        return count;
    }
}