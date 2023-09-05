class Solution {
    static public String solution(String m, String[] musicinfos) {
    	String answer = "(None)";
    	int playtime = Integer.MIN_VALUE;
    	m = ManufactureString(m);
    	
    	// 검색하려는 문자열을 이용하여 정규식을 만듦.
        String regex = "(.*)" + m + "(.*)";
        
        for(int i = 0; i < musicinfos.length; i++) {
        	Music music = new Music(musicinfos[i]);
        	if(music.score.matches(regex)) {
        		// 조건이 일치하는 음악이 여러 개일 때에는 재생된 시간이 제일 긴 음악 제목을 반환.
        		if(playtime < music.playtime) {
        			answer = new String(music.name);
        			playtime = music.playtime;
        		}
        	}
        }
        return answer;
    }
    
    static class Music {
        int playtime;
        String name;
        String score;
        Music(String info){
            String[] infos = info.split(",");
            this.playtime = calcTime(infos[0], infos[1]);
            this.name = infos[2];
            this.score = getFullScore(playtime, infos[3]);
        }
        
        // 재생 시간을 계산.
        int calcTime(String from, String to){
            String[] froms = from.split(":");
            String[] tos = to.split(":");
            
            int hour = Integer.parseInt(tos[0]) - Integer.parseInt(froms[0]);
            int minute = Integer.parseInt(tos[1]) - Integer.parseInt(froms[1]);
            
            return hour*60 + minute;
        }
        
        // 재생 시간에 맞춰 재생한 노래의 전체 음악 악보를 구한다. 
        String getFullScore(int time, String subScore) {
        	String score = ManufactureString(subScore);
            String temp = "";
            while(time >= score.length()){
                temp += score;
                time -= score.length();
            }
            temp += score.substring(0, time);
            return temp;
        }
    }
    
    // #이 있는 음계의 경우 한 글자로 표현하기 위해 문자열을 변환하는 method.
    static String ManufactureString(String str) {
    	String temp = new String(str);
    	temp = temp.replaceAll("C#", "c");
    	temp = temp.replaceAll("D#", "d");
    	temp = temp.replaceAll("F#", "f");
    	temp = temp.replaceAll("G#", "g");
    	temp = temp.replaceAll("A#", "a");
    	
    	return temp;
    }
//    public static void main(String[] args) {
//		String[] musicinfos = 
//			{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
//		String m = "ABC";
//		
//		
//		String answer = solution(m, musicinfos);
//		System.out.println(answer);
//	}
}