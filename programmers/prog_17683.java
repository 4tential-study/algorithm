import java.util.*;

class prog_17683 {
    public static void main(String[] args) {
        String[] str2 = {"12:00,12:06,HELLO,C#C#CC#"};
        System.out.println(solution("C#C", str2));
    }
    public static class Music implements Comparable<Music>{
        String name;
        int diff;
        int id;

        Music(int id ,String name, int diff ){
            this.id = id;
            this.name = name;
            this.diff= diff;
        }

        @Override
        public int compareTo(Music o) {
            if(this.diff==o.diff) {
                return this.id - o.id;
            }else{
                return o.diff-this.diff;
            }

        }
    }

    static PriorityQueue<Music> pq;

    public static String solution(String m, String[] musicinfos) {
        String ans = "";
        pq  = new PriorityQueue<>();
        for(int z=0; z < musicinfos.length ; z++){
            String[] infos = musicinfos[z].split(",");

            String start = infos[0];
            String[] starts = start.split(":");
            int startT = Integer.parseInt(starts[0])*60 + Integer.parseInt(starts[1]);

            String end = infos[1];
            String[] ends = end.split(":");
            int endT = Integer.parseInt(ends[0])*60 + Integer.parseInt(ends[1]);

            String name = infos[2];
            String note = infos[3];
            String[] notes = splitNote(note);
            int len = notes.length;//3
            int diff = endT-startT;


            String[] compare = new String[diff];
            for(int i=0 ; i < diff ; i++){
                compare[i] = notes[i%len];
            }

            if(arrayToString(compare).contains(m)){
                Music music = new Music(z,name, diff);
                pq.offer(music);
            }
        }
        if(pq.size()==0) {
            ans ="(None)";
            return ans;
        }
        Music mus =pq.poll();
        ans = mus.name;

        return ans;

    }

    public static String[] splitNote(String note){
        if(note.contains("#")){
            note=note.replaceAll("C#","c");
            note=note.replaceAll("D#","d");
            note=note.replaceAll("F#","f");
            note=note.replaceAll("G#","g");
            note=note.replaceAll("A#","a");
        }
        return note.split("");

    }

    public static String arrayToString(String[] compare){
        StringBuilder sb = new StringBuilder();
        for(String str : compare){
            sb.append(str);
        }
        return sb.toString();
    }
}