import java.util.ArrayDeque;
import java.util.Set;
import java.util.Queue;
import java.util.Iterator;
import java.util.LinkedHashSet;

class P43163 {
  public int solution(String begin, String target, String[] words){
    Set<String> set = new LinkedHashSet<>(); // Add, Contains, Next 모두 O(1)
    for(String str : words) set.add(str);
    if(!set.contains(target)) return 0;
    int answer = 0;
    Queue<String> queue = new ArrayDeque<>(); queue.offer(begin);

    while(!queue.isEmpty()){
      int search_count = queue.size();
      for(int i = 0; i < search_count; i++){
        String current = queue.poll(); 
        if(current.equals(target)) return answer;
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
          String value = it.next();
          if(canChange(current, value)){
            queue.offer(value); it.remove();
          }
        }
      }
      answer++;
    }
    return 0;
  }

  public boolean canChange(String a, String b){
    int diff = 0;
    for(int i = 0; i < a.length(); i++){
      if(a.charAt(i) != b.charAt(i))  diff++;
      if(diff >= 2) return false;
    }
    return true;
  }

  //   public static void main(String[] args) {
  //   String begin = "hit", target = "cog";
  //   String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
  //   System.out.println(solution(begin, target, words));
  // }
}
