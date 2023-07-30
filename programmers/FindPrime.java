import java.util.*;
import java.util.stream.Stream;

public class FindPrime{
    static ArrayList<Integer> candidates = new ArrayList<>();
    static int count;
    static boolean[] visited;
    static int[] nums;
    static int len;

    public int solution(String numbers) {
        String[] splits = numbers.split("");
        nums = Stream.of(splits).mapToInt(Integer::valueOf).toArray();

        len = nums.length;
        visited = new boolean[len];
        for(int i=1 ; i <= len ; i++){
            perm(i, "");
        }

        for(Integer each : candidates){
            System.out.println("--------");
            if(isPrime(each)){
                count++;
            }
        }
        return count;
    }

    public static void perm(int max, String cur){
        System.out.println(cur + " " + max);
        if (cur.length() == max){
            candidates.add(Integer.valueOf(cur));
            return;
        }

        for(int i=0 ; i < len ; i++){
            if(!visited[i]){
                visited[i] = true;
                cur = cur.concat(String.valueOf(nums[i]));
                perm(max, cur);
                cur = cur.substring(0, cur.length()-1);
                visited[i] = false;
            }

        }
    }

    public static boolean isPrime(Integer number){
        boolean[] primeMap = new boolean[number+1];
        primeMap[0] = primeMap[1] = false;

        for(int i = 2 ; i < Math.sqrt(number); i++){
            if (primeMap[i]){
                for(int j=i*i; j <= number ; j+=i ){
                    primeMap[j] = false;
                }
            }
        }
        System.out.println(primeMap[number]);
        return primeMap[number];

    }
}