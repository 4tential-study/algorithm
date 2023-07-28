import java.util.Scanner;

public class B9663 {
    static int[] Queens;
    static int count = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Queens = new int[N];
        solve(0);
        
        System.out.println(count);
    }

    static void solve(int index){
        if(index >= Queens.length){
            count++;    return;
        }

        boolean flag = false;
        // 가로 index 줄에서 배치할 자리 찾기.
        for(int i = 0; i < Queens.length; i++){ // i번째에 놓을지 검사함.
            flag = true; // 배치 가능 여부 체크
            for(int j = 0; j < index; j++){ // 이미 배치된 Queen이 공격할 수 있는 자리인지 탐색.
                int diff = Math.abs(index - j);
                if(i == Queens[j] || Math.abs(Queens[j] - i) == diff){
                    flag = false; // 그 자리에 배치할 수 없다.
                    break;
                }
            }
            if(flag) {
                Queens[index] = i;
                solve(index+1);
            }
        }
        if(!flag)   return;
    }
}