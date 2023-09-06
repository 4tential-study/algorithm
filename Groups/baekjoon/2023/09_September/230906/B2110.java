import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B2110 {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String[] input = in.readLine().split(" ");
    int house = Integer.parseInt(input[0]);     // 집 개수
    int hubs = Integer.parseInt(input[1]);      // 공유기 개수

    int[] houses = new int[house];
    for(int i = 0; i < house; i++){
      houses[i] = Integer.parseInt(in.readLine());
    }
    Arrays.sort(houses);

    int start = 1, end = houses[house-1] - houses[0] ;

    int result = 0;
    while(start <= end){
        int mid = (start + end) / 2; // 탐색 거리
        int prev = houses[0]; // 이전 공유기 설치 위치
        int count = 1;

        for(int i = 1; i < house; i++){
            int dist = houses[i] - prev; // 현재 집과 이전 공유기 설치 위치 사이의 거리
            if(dist >= mid){
                count++;
                prev = houses[i]; // 현재 집에 공유기 설치
            }
        }

        if(count >= hubs){
            result = Math.max(mid, result);
            start = mid + 1;
        } else {
            end = mid - 1;
        }
    }

    System.out.println(result);
  }  
}