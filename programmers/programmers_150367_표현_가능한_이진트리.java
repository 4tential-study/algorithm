class Solution {
    public int[] solution(long[] numbers) {

        int[] answer = new int[numbers.length];
		
		for(int i=0; i<numbers.length; i++) {
			long num = numbers[i];
			String bin = makeBinary(num);

			if(check(0, bin.length()-1, bin, true)) {
				answer[i] = 1;
			}else answer[i] = 0;
		}
		
        return answer;
    }
	
	static boolean check(int start, int end, String bin, boolean parentActive) {
		int mid = (start + end) / 2;
		
		if(bin.charAt(mid) == '1') {
			if(!parentActive) return false;
			else {
				if(start==end) return true;
				if(check(start, mid-1, bin, true)) return check(mid+1, end, bin, true);
				else return false;
			}
		}else {
			if(start==end) return true;
			if(check(start, mid-1, bin, false)) return check(mid+1, end, bin, false);
			else return false;
		}
	}
	static String makeBinary(long num) {
		long b = num;
		String bin = "";
		
		int flag = -1;
		for(int i=50; i>=0; i--) {
			long r = b / (long)Math.pow(2, i);
			if(r!=0) {
				if(flag==-1) flag = i;
				bin += "1";
				b = b % (long)Math.pow(2, i);
			}else if(flag!=-1) bin += "0";
		}
		
		for(int i=1; i<=6; i++) {
			int target = (int)Math.pow(2, i)-1;
			if(target==bin.length()) break;
			
			if(target>bin.length()) {
				for(int j=bin.length(); j<target; j++)
					bin = "0" + bin;
				
				break;
			}
		}
		
		return bin;
	}
}