import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

class B16236 {
    static class Fish {
        int y, x;
        public Fish(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Node implements Comparable<Node> {
        int distance;
        Fish fish;

        Node(int distance, int[] array){
            this.distance = distance;
            fish = new Fish(array[0], array[1]);
        }

        @Override
        public int compareTo(Node o) {
            return (this.distance == o.distance) ? (this.fish.y == o.fish.y) ? this.fish.x - o.fish.x : this.fish.y - o.fish.y : this.distance - o.distance;
        }
    }
    private static final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(in.readLine());

        int board[][] = new int[length][length];

        Queue<Node> queue = new PriorityQueue<>();
        for(int y = 0; y < length; y++){
            String[] input = in.readLine().split(" ");
            for(int x = 0; x < length; x++){
                board[y][x] = Integer.parseInt(input[x]);
                if(board[y][x] == 9){
                    queue.offer(new Node(0, new int[] {y, x}));
                    board[y][x] = 0;
                }
            }
        }

        int size = 2, eat = 0, time = 0;
        boolean[][] visited = new boolean[length][length];
        while(!queue.isEmpty()){
            Node current = queue.poll();
            int curr_distance = current.distance;
            int cy = current.fish.y, cx = current.fish.x;
            if(board[cy][cx] > 0 && size > board[cy][cx]){
                visited = new boolean[length][length];
                queue.clear();
                board[cy][cx] = 0;
                time += curr_distance;
                current.distance = 0;
                queue.offer(current);
                eat++;
                if(size == eat){
                    size++;
                    eat = 0;
                }
                continue;
            }
            for(int type = 0; type < 4; type++){
                int dy = cy + dir[type][0];
                int dx = cx + dir[type][1];

                if((dy >= 0 && dy < length && dx >= 0 && dx < length) && !visited[dy][dx] && board[dy][dx] <= size){
                    visited[dy][dx] = true;
                    queue.offer(new Node(curr_distance + 1, new int[] {dy, dx}));
                }
            }
        }

        System.out.println(time);
    }
}