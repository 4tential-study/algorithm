
class prog_92344 {
    static int[][] calcul;
    static int n;
    static int m;
    public int solution(int[][] board, int[][] skill) {
        //1 : 공격 , 2 : 회복

        int answer = 0;
        n = board.length;//y
        m = board[0].length;//x
        calcul = new int[n+1][m+1];

        for(int i=0; i < skill.length; i++){
            int type = skill[i][0];
            int r1 = skill[i][1]; //시작
            int c1 = skill[i][2];
            int r2 = skill[i][3]; //끝
            int c2 = skill[i][4];
            int degree = skill[i][5];

            //매번 0으로 초기화
            if (type == 1){
                //1. 초반 작업
                calcul[r1][c1] -= degree;
                calcul[r1][c2+1] += degree;
                calcul[r2+1][c1] += degree;
                calcul[r2+1][c2+1] -= degree;

            }else{
                calcul[r1][c1] += degree;
                calcul[r1][c2+1] -= degree;
                calcul[r2+1][c1] -= degree;
                calcul[r2+1][c2+1] += degree;
            }
        }


        // 오른쪽으로 ->
        for(int y = 0 ; y <= n ; y++){
            for(int x = 1 ; x <= m ; x++){
                calcul[y][x] += calcul[y][x-1];
            }
        }


        //2. 아래 열들을 계산
        for(int x = 0 ; x <= m ; x++){
            for(int y = 1 ; y <= n ; y++){
                calcul[y][x] += calcul[y-1][x];
            }
        }



        for(int y=0 ; y < n ; y++){
            for(int x = 0; x < m ; x++){
                board[y][x]+=calcul[y][x];
            }
        }

        for(int y=0 ; y < n ; y++){
            for(int x =0; x < m ; x++){
                if(board[y][x] > 0) answer++;
            }
        }
        return answer;
    }
}