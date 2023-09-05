
import java.util.Arrays;

class Solution {
    public static String solution(String m, String[] musicinfos) {
        
        int maxLength = 0;
        String result = "(None)";
		String[] melodyPitchs = getPitch(m.length(), m, true);
		
		for(int i=0; i<musicinfos.length; i++) {
			String[] infos = musicinfos[i].split(",");
			
			// 곡 길이 계산
			int length = 0;			
			String[] start = infos[0].split(":");
			String[] end = infos[1].split(":");
			
			length += (Integer.parseInt(end[0]) - Integer.parseInt(start[0])) * 60;
			length += (Integer.parseInt(end[1]) - Integer.parseInt(start[1]));
						
			// 악보 split

			String[] songPitchs = getPitch(length, infos[3], false);
			
			// 기억한 멜로디와 곡 정보가 일치하는지 확인
            
			for(int j=0; j<length; j++) {
				int mCnt = 0;		// 멜로디를 센 갯수
				for(int k=j; k<length && mCnt<melodyPitchs.length; k++,mCnt++) {
					if(!melodyPitchs[mCnt].equals(songPitchs[k])) break;
				}
				if(mCnt==melodyPitchs.length && maxLength < length) {
					maxLength = length;
					result = infos[2];
					break;
				}
			}
		}
		
        return result;
    }
	static String[] getPitch(int length, String melody, boolean isTarget) {

		 
		String[] pitchs = new String[length];
		
		int cur=0;
		int next=cur+1;
		int pitchCount=0;
		
		for(int j=0; j<length; j++) {
			if(cur>=melody.length()) cur = cur%melody.length();
			if(next>=melody.length()) next = next%melody.length();
			
			if(melody.charAt(cur)!='#') {
				if(melody.charAt(next)=='#') {
					pitchs[j] = Character.toString(melody.charAt(cur)) + Character.toString(melody.charAt(next));
					cur++;
					next++;
				}else {
					pitchs[j] = Character.toString(melody.charAt(cur));
				}
				pitchCount++;
			}
			
			cur++;
			next++;
			if(isTarget && cur==melody.length()) {
				pitchs = Arrays.copyOf(pitchs, pitchCount);
				break;
			}
		}
		
		return pitchs;
	}
}