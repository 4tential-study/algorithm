class Solution {
    public int solution(int[][] board, int[][] skill) {
        int height = board.length;
		int width = board[0].length;
        int[][] weights = new int[height][width];
		int answer = 0;
        int skill_count = skill.length;
        for(int i = 0; i < skill_count; i++){
            int[] start = {skill[i][1]-1, skill[i][2]-1};
            int[] fin = {skill[i][3], skill[i][4]};
            int weight = skill[i][5];
            
            if(skill[i][0] == 1)   weight *= -1;
            
            weights[fin[0]][fin[1]] += weight;
            if(start[0] >= 0) 
                weights[start[0]][fin[1]] += (weight * -1);
            if(start[1] >= 0) 
                weights[fin[0]][start[1]] += (weight * -1);
            if(start[0] >= 0 && start[1] >= 0) 
                weights[start[0]][start[1]] += weight;
            
            
        }
        calcWeight(weights, height, width);
        answer = getCount(board, weights, height, width);
        return answer;
    }
    
    // 가중치 배열에 누적합 적용
    static void calcWeight(int[][] weights, int height, int width) {
		for(int y = 0; y < height; y++) {
			for(int x = width - 2; x >= 0; x--) {
				weights[y][x] += weights[y][x+1];
			}
		}
		
		for(int x = width - 1; x >= 0; x--) {
			for(int y = height - 2; y >= 0; y--) {
				weights[y][x] += weights[y+1][x];
			}
		}
	}
	
    // 가중치 배열을 이용한 계산 + 파괴되지 않은 건물 수 count
    static int getCount(int[][] board, int[][] weights, int height, int width){
        int count = 0;
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if(board[y][x] + weights[y][x] > 0) count++;
            }
        }
        return count;
    }
}