import java.util.*;

class prog_118667 {
    static Queue<Long> q1 = new ArrayDeque<>();
    static Queue<Long> q2 = new ArrayDeque<>();
    static final int INF =999999999; 
    
    public int solution(int[] queue1, int[] queue2) {
        long sum = 0;
        long sum1 = 0;
        long sum2 = 0;
        for(int i = 0 ; i < queue1.length ; i++){
            sum1 += queue1[i];
            sum2 += queue2[i];
            q1.offer(Long.valueOf(queue1[i]));
            q2.offer(Long.valueOf(queue2[i]));
        }          
        int min = INF;
        int cnt = 0;
        for(int i=0 ; i <= queue1.length * 2 + 1 ; i++){
            if(q1.isEmpty() || q2.isEmpty()) {
                min = -1;
                break;
            }
            //pop,push 수행후, MIN값 도출
            if(sum1 > sum2){
                long pop1 = q1.poll();
                sum1 -= pop1;
                q2.offer(pop1);
                sum2 += pop1;                
                cnt++;
            }else if(sum1 < sum2){
                long pop2 = q2.poll();
                sum2 -= pop2;
                q1.offer(pop2);
                sum1 += pop2;
                cnt++;
            }
            if(sum1 == sum2) min = Math.min(min, cnt);
        }
        return min == INF ? -1 : min;
    }
}