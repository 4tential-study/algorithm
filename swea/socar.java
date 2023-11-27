//import java.util.Arrays;
//import java.util.Set;
//import java.util.TreeMap;
//
//public class socar {
//    static TreeMap<Integer, Integer> map = new TreeMap<>();
//    static int n;
//    static int[] orders;
//    public static void main(String[] args) {
//        int idx = 0;
//        int ans = 0;
//        for(int i=0 ; i < orders.length ; i++) {
//            if (!map.containsKey(orders[i])) {
//                int size = map.size();
//                map.put(orders[i], idx++);
//                ans += size;
//            } else {
//                Object[] integers = map.keySet().toArray();
//
//                map.
////                int[] array = map.keySet().stream().mapToInt(Integer::valueOf).toArray();
//                int th = Arrays.binarySearch(integers, orders[i]);
//                ans += th;
//                map.remove(orders[i]);
//            }
//        }
//        System.out.println(ans);
//    }
//
//
//
//
//}
