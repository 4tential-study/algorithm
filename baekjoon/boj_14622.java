import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;


public class boj_14622 {
    static BufferedReader in;
    static int n;
    static ArrayList<Integer> dList = new ArrayList<>();
    static ArrayList<Integer> kList = new ArrayList<>();
    static final int N = 5000000;
    static long kscore = 0;
    static long dscore = 0;
    static boolean[] notPrime = new boolean[N]; //소수면 false, 소수가 아니면 true
    static PriorityQueue<Integer> dpq = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> kpq = new PriorityQueue<>(Collections.reverseOrder());
    public static void main(String[] args) throws IOException{
        in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        make();

        for(int i=0 ; i < n ; i++) {
            String[] ins = in.readLine().split(" ");
            int d = Integer.parseInt(ins[0]);
            int k = Integer.parseInt(ins[1]);
            //대웅차례
            if(!notPrime[d]) {
                if(dList.contains(d)|| kList.contains(d)) {
                    dscore -= 1000;
                }else {
                    dpq.offer(d);
                    dList.add(d);
                }
            } else {
                if(kpq.size() < 3) {
                    kscore += 1000;
                }else {
                    int biggest = kpq.poll();
                    int bigger = kpq.poll();
                    int big = kpq.poll();
                    kscore += big;
                    kpq.offer(biggest);
                    kpq.offer(bigger);
                    kpq.offer(big);
                }
            }
            //규성차례
            if(!notPrime[k]) {
                if(dList.contains(k)|| kList.contains(k)) {
                    kscore -= 1000;
                }else {
                    kpq.offer(k);
                    kList.add(k);
                }
            } else {
                if(dpq.size() < 3) {
                    dscore += 1000;
                }else {
                    int biggest = dpq.poll();
                    int bigger = dpq.poll();
                    int big = dpq.poll();
                    dscore += big;
                    dpq.offer(biggest);
                    dpq.offer(bigger);
                    dpq.offer(big);
                }
            }

        }
        if(kscore == dscore ) {
            System.out.println("우열을 가릴 수 없음");
            return;
        }
        String ans = kscore > dscore ?  "소수 마스터 갓규성" : "소수의 신 갓대웅";
        System.out.println(ans);
    }

    public static void make() {
        notPrime[0] = notPrime[1] = true; //소수가 아님 =true

        for(int i=2 ; i <= Math.sqrt(N) ; i++) {
            if(!notPrime[i]) {
                for(int j=i*i ; j < N ; j+=i ) {
                    notPrime[j] = true;
                }
            }
        }
    }
}