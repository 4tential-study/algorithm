import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_10158 {
	static BufferedReader br;
	static int t;
	static int W;
	static int H;
	static int P;
	static int Q;
	static int[] dir = {-1,1};
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		String[] WH = br.readLine().split(" ");
		String[] PQ = br.readLine().split(" ");
		int t = Integer.parseInt(br.readLine());
		W = Integer.parseInt(WH[0]); //x 6 
		H = Integer.parseInt(WH[1]); //y
		P = Integer.parseInt(PQ[0]); //x
		Q = Integer.parseInt(PQ[1]);//y
		
		for(int i=0 ; i < t ; i++) {
			int y = Q + dir[0];
			int x = P + dir[1]; //이동 한 뒤
			if(inRange(y,x)) {
				System.out.println("! "+y + " " + x);
				if(y == 0 || x == 0 || y == H || x == W) {
					if(dir[0] * dir[1] > 0) {
						dir[0] *= -1;
						dir[1] *= 1;
					}else {
						dir[0] *= 1;
						dir[1] *= -1;
					}
				}
				Q=y;
				P=x;
			} else {
				System.out.println("else "+y + " " + x);
				if(dir[0] * dir[1] > 0) {
					dir[0] *= -1;
					dir[1] *= 1;
				}else {
					dir[0] *= 1;
					dir[1] *= -1;
				}
				
			}
			Q=y;
			P=x;
		}
		
		System.out.println( (W-P) + " "+Q);
		
	}
	
	public static boolean inRange(int y, int x) {
		return y >=0 && x >= 0 && y < H+1 && x < W+1;
	}
	
	
}
