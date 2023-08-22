import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
import javax.swing.text.AbstractDocument.LeafElement;
 
public class Solution {
     
    static boolean[][] magnets; 
    static int[] pointers;
    static StringBuilder sb = new StringBuilder();
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
         
        int T = Integer.parseInt(in.readLine());
        for(int tc=1; tc<=T; tc++) {
            sb.append("#"+tc+" ");
            int N = Integer.parseInt(in.readLine());
            magnets = new boolean[4][8];
            pointers = new int[4];
             
            for(int i=0; i<4; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                for(int j=0; j<8; j++) {
                    magnets[i][j] = st.nextToken().charAt(0) == '1' ? true : false;
                }
            }
            for(int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int magnet = Integer.parseInt(st.nextToken())-1;
                int direction = Integer.parseInt(st.nextToken());
                rotate(magnet, direction, magnet);
            }
             
            int resultScore=0;
            for(int i=0; i<4; i++) {
                if(magnets[i][pointers[i]]) {
                    resultScore+=1*(int)Math.pow(2, i);
                }
//              System.out.print(pointers[i]+" ");
//              for(boolean j : magnets[i]) System.out.print(j+" ");
//              System.out.println();
            }
             
            // 결과 출력
            sb.append(resultScore+"\n");
        }
        System.out.println(sb);
    }
     
    private static void rotate(int mgIdx, int direction, int fromIdx) {
        int leftJointIdx = rotNum(pointers[mgIdx], -2);
        int rightJointIdx = rotNum(pointers[mgIdx], +2);
        // 현재 자석의 위치와 접합 지점의 극성에 따라 왼쪽, 오른쪽 자석의 회전 여부 결정
             
        // 현재 진행 방향이 왼쪽이거나 처음일때
        if(mgIdx<fromIdx || mgIdx==fromIdx) {
            // mgIdx가 0이 아니라면 왼쪽 자석 확인
            // 회전 전, 현재 자석의 왼쪽 접합지점과 왼쪽 자석의 오른쪽 접합 지점을 비교
            // 극성이 다르다면 왼쪽 자석 회전 함수 실행
            if(mgIdx!=0) {
                int prevRightJointIdx = rotNum(pointers[mgIdx-1], +2);
                if(magnets[mgIdx][leftJointIdx] != magnets[mgIdx-1][prevRightJointIdx]) {
                    if(direction==1) rotate(mgIdx-1, -1, mgIdx); 
                    else rotate(mgIdx-1, 1, mgIdx);
                }
            }
        }
        // 현재 진행방향이 오른쪽이거나 처음일때
        if(fromIdx<mgIdx || mgIdx==fromIdx){// 왼쪽에서 오른쪽으로 넘어왔을 때
            // mgIdx가 3이 아니라면 오른쪽 자석 확인
            if(mgIdx!=3) {
                int nextLeftJointIdx = rotNum(pointers[mgIdx+1], -2);
                if(magnets[mgIdx][rightJointIdx] != magnets[mgIdx+1][nextLeftJointIdx]) {
                    if(direction==1) rotate(mgIdx+1, -1, mgIdx); 
                    else rotate(mgIdx+1, 1, mgIdx);
                }
            }
        }
         
        // 현재 자석 회전
        pointers[mgIdx] = rotNum(pointers[mgIdx], -direction);
    }
    private static int rotNum(int origin, int update) {
        int result = origin + update;
        if(result<0) {
            return result = 8 + result;         
        }else if(8<=result) {
            return result - 8;
        }
        return result;
    }
}