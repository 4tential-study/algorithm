import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution_2382_미생물_격리 {
	
	private static class Cell{
		int count, max_count;
		int dir;
		
		public Cell() {
			super();
			this.count = 0;
			this.max_count = 0;
			this.dir = 0;
		}
		
		void setDir(Micro a) {
			if(max_count < a.count) {
				this.dir = a.move_dir;
				max_count = a.count;
			}
		}
		
		void addCount(Micro a) {
			this.count += a.count;
		}
		
		@Override
		public String toString() {
			return "(" + count + ", " + max_count + ", " + dir + ")";
		}
	}
	
  static class Micro {
    int y, x;
    int count;
    int move_dir;

    public Micro(int y, int x, int count, int move_dir) {
      this.y = y;
      this.x = x;
      this.count = count;
      this.move_dir = move_dir;
    }

    void combine(Micro a){
      this.move_dir = (this.count > a.count) ? this.move_dir : a.move_dir;
      this.count = this.count + a.count;
    }

    void moveMicro() {
      int dy = this.y + dir[move_dir][0];
      int dx = this.x + dir[move_dir][1];
      if(dy >= 0 && dx >= 0 && dy < length && dx < length){
        this.y = dy;  this.x = dx;
      }
    }
    
    public boolean equals(Micro obj) {
      return this.x == obj.x && this.y == obj.y;
    }

	@Override
	public String toString() {
		return "(" + count + ", " + move_dir + ")";
	}
  }
  static Micro[][] cells;
  static int length;
  
  // 1 : 상, 2 : 하, 3 : 좌, 4 : 우
  static final int[][] dir = {{0, 0}, {-1, 0},{1, 0},{0, -1},{0, 1}}; // 상 하 좌 우
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int T = Integer.parseInt(in.readLine()); // 총 테스트케이스 개수
    for(int test_case = 1; test_case <= T; test_case++){
      sb.append("#").append(test_case).append(" ");
      String[] input = in.readLine().split(" ");
      length = Integer.parseInt(input[0]); // 정사각형 셀 크기
      int isolate_time = Integer.parseInt(input[1]);
      int micro_count = Integer.parseInt(input[2]);

      cells = new Micro[length][length];
      List<Micro> microList = new ArrayList<>();
      for(int i = 0; i < micro_count; i++){
        input = in.readLine().split(" ");
        int y = Integer.parseInt(input[0]);
        int x = Integer.parseInt(input[1]);
        int count = Integer.parseInt(input[2]);
        int move_dir = Integer.parseInt(input[3]);
        Micro micro = new Micro(y, x, count, move_dir);
        microList.add(micro);
      }

      for(int time = 0; time < isolate_time; time++) {
    	  Cell[][] cells2 = new Cell[length][length];
    	  
    	  for(Micro micro : microList)	
    		  micro.moveMicro();
    	  
    	  List<int[]> existMicro = new ArrayList<>();
    	  for(int i = 0; i < microList.size(); i++) {
    		  Micro micro = microList.get(i);
	          int y = micro.y, x = micro.x;
	          if(y == 0 || x == 0 || y == length-1 || x == length-1){
	            micro.count /= 2;
	            if(micro.count == 0){
	              continue;
	            }
	            micro.move_dir = (micro.move_dir <= 2) ? 3 - micro.move_dir : 7 - micro.move_dir;
	          }
	          
	          if(cells2[y][x] == null) {
	        	  cells2[y][x] = new Cell();
	        	  existMicro.add(new int[] {y, x});
	          }
        	  cells2[y][x].setDir(micro);
        	  cells2[y][x].addCount(micro);
    	  }
    	  microList.clear();

    	  for(int[] coord : existMicro) {
    		  int y = coord[0], x = coord[1];
    		  Micro m = new Micro(y, x, cells2[y][x].count, cells2[y][x].dir);
    		  microList.add(m);
    	  }
      }
      int sum = 0;
      for(Micro micro : microList)  sum += micro.count;

      sb.append(sum).append("\n");
    }
    System.out.println(sb);
  }  
}