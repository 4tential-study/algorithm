import java.util.Scanner;

class B1463 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();

    int[] DP = new int[N+1];
    DP[1] = 0;    
    if(N >= 2)  DP[2] = 1;    if(N >= 3)  DP[3] = 1;
    for(int n = 4; n <= N; n++){
      if(n % 3 == 0){
        DP[n] = DP[n/3];
      }
      if(n % 2 == 0){
        DP[n] = (DP[n] == 0) ? DP[n/2] : Math.min(DP[n], DP[n/2]);
      }
      DP[n] = ((DP[n] == 0) ? DP[n-1] : Math.min(DP[n], DP[n-1])) + 1;
    }
    System.out.println(DP[N]);
    sc.close();
  }
}