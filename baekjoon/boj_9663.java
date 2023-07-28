import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class boj_9663 {
    static int[][] board;
    static int count;
    static ArrayList<Integer> currentCandidate = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer N = Integer.parseInt(br.readLine());
        board = new int[N+1][N+1];
        func(N, 0, currentCandidate);
        System.out.println(count);
    }

    public static void func(Integer N, Integer currentRow, ArrayList<Integer> currentCandidate){
        if(currentRow == N){
            count++;
            return;
        }

        for(int index = 0 ; index < N ; index++){ // index => column, func의 depth => row
            if(isAvailable(currentCandidate, index)){
                currentCandidate.add(index);
                func(N,currentRow+1, currentCandidate);
                currentCandidate.remove(currentCandidate.size()-1);
            }
        }


    }
    // 이제까지 선택했던 좌표들을 순회하면서, 가능여부 체크
    public static boolean isAvailable(ArrayList<Integer> candidate, Integer currentCol){
        Integer currentRow = candidate.size();
        for(int i=0 ; i < currentRow ; i ++){
            if(candidate.get(i) == currentCol || Math.abs(candidate.get(i) - currentCol) == currentRow - i){
                //1.현재선택한 column과 과거선택한 column이 있는지
                //2.대각선의 기울기가 같은지
                return false;
            }
        }
        return true;
    }


}
