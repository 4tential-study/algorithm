//13428. 숫자 조작(SWEA, D3)
import java.util.Scanner;
import java.util.Arrays;


//이 문제는...idx를 지정할 때, 최댓값을 만들 경우 가장 뒤에 있는 가장 작은 숫자를 끌어와야 하고, 최솟값을 만들 경우 가장 앞에 있는 가장 작은 숫자를 끌어와야 함에 주의하자.
public class study{
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        //오류 막기 위해 개행문자 처리
        sc.nextLine();
        for (int test_case=1;test_case<=T;test_case++){
            //정수 N을 문자열로 입력받아 정수 배열로 저장
            String n = sc.nextLine();
            int[] arr = new int[n.length()];
            for (int i=0;i<n.length();i++){
                arr[i] = n.charAt(i)-'0';
            }
            int idx=0;
            int temp=0;
            int idx_front=0;
            int change=0;
            //idx에 각 배열의 자리를 0에서부터 넣고, 각 idx에 대해 idx 뒤에 있는 숫자 중 더 큰 숫자가 있다면 해당 인덱스로 idx 갱신
            //그렇게 idx 이후의 숫자 중 최댓값의 인덱스를 가진 idx로 가장 앞에 있는 숫자 중 해당 자리에서 가능한 최댓값이 아닌 값과 교환
            for (int i=0;i<n.length();i++){
                idx=i;
                for (int j=i+1;j<n.length();j++){
                    if (arr[idx]<=arr[j]) idx=j;
                }
                if (idx!=i){
                    if (arr[idx]==arr[i]) continue;
                    else{
                        change=1;
                        idx_front=i;
                        temp = arr[idx];
                        arr[idx] = arr[i];
                        arr[i] = temp;
                        break;
                    }
                }
            }
            //!!최댓값 배열을 통해 최댓값 maximum 계산
            int maximum = 0;
            for (int i=0;i<n.length();i++){
                maximum=10*maximum+arr[i];
            }
            //만약 위에서 교환이 일어난 경우(change==1), 정수 배열 원상복귀
            if (change==1){
                temp = arr[idx_front];
                arr[idx_front] = arr[idx];
                arr[idx] = temp;
            }
            //idx에 배열 각 자리 인덱스를 넣고, 각 자리에서부터 맨 뒷자리까지의 값과 비교해 해당 값보다 작은 값이 뒤에 있으면 그 인덱스를 idx에 저장
            //여기에서는 최대한 앞쪽으로 가장 작은 값을 보내 바꾸어주는 것을 목표로 함. 즉, 첫 자리에 이미 최솟값이 있다면 다음 자리에 두 번째로 작은 수가 오는 식.
            //ex) 132=>이미 첫번째는 최솟값인 1이므로 3을 뒤에 있는 가장 작은 수인 2와 바꾸어주어 123으로 만들면 최솟값이 된다.
            for (int i=0;i<n.length();i++){
                idx=i;
                //만약 입력값이 20103인 경우, 첫째 자리에 1이 오고 그 이후 숫자인 0으로 idx=j가 초기화되면 첫째자리에 올 수 있는 최솟값이 1로의 갱신이 어려워지므로 따로 처리
                if (i==0){
                    for (int j=i+1;j<n.length();j++){
                        if (arr[idx]>=arr[j] && arr[j]!=0){
                            idx=j;
                        }
                    }
                }
                else{
                    for (int j=i+1;j<n.length();j++){
                        if (arr[idx]>=arr[j]) idx=j;
                    }
                }
                if (idx!=i) {
                    if (arr[idx]==0 && i==0 || arr[idx]==arr[i]) continue;
                    else{
                        temp = arr[idx];
                        arr[idx] = arr[i];
                        arr[i] = temp;
                        break;
                        }
                    }
                }
            //!!최솟값 배열을 통해 최솟값 minimum 계산
            int minimum = 0;
            for (int i=0;i<n.length();i++){
                minimum=minimum*10+arr[i];
            }
            System.out.println("#"+test_case+" "+minimum+" "+maximum);
        }
        sc.close();
    }
}