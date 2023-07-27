// https://www.acmicpc.net/problem/10971

import java.io.*;
import java.util.StringTokenizer;

public class B10971 {
    static int[][] cost;
    static int city, count, result = Integer.MAX_VALUE;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        city = Integer.parseInt(br.readLine());
        StringTokenizer st;
        cost = new int[city][city];
        visited = new boolean[city];
        for(int i = 0; i < city; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < city; j++)
            cost[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < city; i++){
            count = 0;
            solve(i, i, 0);
        }
        System.out.println(result);
    }

    static void solve(int first, int index, int sum) {
        if(result < sum)    return;
        if(count == city){
            System.out.println("sum : " + sum);
            result = Math.min(result, sum);
            return;
        }

        for(int i = 0; i < city; i++){
            if(visited[i] || index == i) continue;
            visited[i] = true;  count++;
            solve(first, i, sum + cost[index][i]);
            visited[i] = false; count--;
        }
    }
}