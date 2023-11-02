
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_5397 {
    static BufferedReader in;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        in = new BufferedReader(new InputStreamReader(System.in));
        int t =Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        LinkedList<Character> list = new LinkedList<>();

        for(int i=0 ; i < t ; i++) {
            int cursor = 0;
            list.clear();
            String str = in.readLine();
            if(str.contains("<") || str.contains(">") || str.contains("-")) {
//				int back= str.indexOf('<');


                for(int j=0 ; j < str.length() ; j++) {
                    char cmd = str.charAt(j);
                    if (cmd >= 65 && cmd <= 90 || cmd >= 97 && cmd <= 122 || cmd >= 48 && cmd <= 57) {
                        //알파벳
//					sb.insert(cursor,cmd);
                        list.add(cursor++, cmd);

                    }else if(cmd == '<') {
                        cursor--;
                        if(cursor<0) cursor=0;

                    }else if(cmd == '>') {
                        cursor++;
                        if(cursor>list.size()) cursor = list.size();
                    }else if(cmd == '-') {
                        if(cursor> 0) {
//						sb.deleteCharAt(cursor-1);
                            list.remove(--cursor);
                            if(cursor < 0 ) cursor = 0;
                        }
                    }
                }
                Character[] answer = list.toArray(new Character[list.size()]);
                int size = list.size();
                for(int z=0 ; z < size ; z++) sb.append(answer[z]);
                sb.append("\n");
            }else {
                sb.append(str);
                sb.append("\n");
            }

        }
        System.out.println(sb.toString());
    }



}