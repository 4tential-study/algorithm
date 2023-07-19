//11497. 통나무 건너뛰기 (백준, 실버 1) (이전에 파이썬으로 풀었던 문제)

import java.util.Scanner;
import java.util.stream.Stream;
import java.util.Arrays;

//아이디어 : 
//1. 통나무의 높이를 리스트로 입력받은 후 sort를 통해 오름차순 정렬
//2. 통나무의 높이 차이를 최소화하기 위한 정렬 순서를 저장하는 idx 리스트 생성
//예를 들어, n=5이면 이상적인 idx 리스트는 0,2,4,3,1이 된다.
//idx 리스트에 맞추어 통나무 높이 차이의 최댓값을 계산해 반환.
public class study {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        for (int test_case=1;test_case<=T;test_case++){
            int n = sc.nextInt();
            sc.nextLine();
            String line = sc.nextLine();
            //li에 통나무의 높이 저장 후 오름차순으로 정렬
            int[] li = Stream.of(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(li);
            //idx 리스트 생성
            int[] idx = new int[n];
            int cnt=0;
            int gap=0;
            //인덱스가 짝수인 경우 0,2,4 ... 처럼 오름차순으로 idx 리스트에 추가
            for (int i=0;i<n;i+=2){
                idx[cnt]=i;
                cnt+=1;
            }
            // n이 짝수개인 경우 n-1, n-3, ... ,3,1 처럼 내림차순으로 idx 리스트에 추가
            if (n%2==0){
                for (int i=n-1;i>0;i-=2){
                    idx[cnt]=i;
                    cnt+=1;
                }
            }
            // n이 홀수개인 경우 n-2, n-4, ... , 3, 1 처럼 내림차순으로 idx 리스트에 추가
            else{
                for (int i=n-2;i>0;i-=2){
                    idx[cnt]=i;
                    cnt+=1;
                }
            }
            // 만들어진 idx 리스트의 인덱스에 맞추어 li 리스트에 저장된 통나무 높이 값의 차를 전체 계산, 최댓값을 gap에 갱신
            // 반드시! Math.abs()를 통해 차의 절댓값을 구해주어야 함!
            for (int i=0;i<n-1;i++){
                gap=Math.max(gap,Math.abs(li[idx[i+1]]-li[idx[i]]));
            }
            // 첫번째 통나무와 마지막 통나무 또한 원형으로 이어져 있으므로 값의 차를 계산해 주어야 함.
            // 여기에서도 꼭 Math.abs() 사용할 것!
            gap=Math.max(gap,Math.abs(li[idx[n-1]]-li[idx[0]]));
            System.out.println(gap);
        }
        sc.close();
    }   
}
