import java.util.*;

class Node {
    int r;
    int c;
    Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Solution {
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int n;
    static int m;
    
    public int solution(int[][] maps) {
        int answer = 0;
        n = maps.length;
        m = maps[0].length;
        
        answer = bfs(maps, 0, 0, 1);
        return answer;
    }

    public int bfs(int[][] maps, int r, int c, int cnt) {
        Queue<Node> queue = new ArrayDeque<>();
        int[][] visited = new int[n][m];
        queue.offer(new Node(r, c));
        visited[r][c] = 1;
        int newCnt = cnt;
        
        while (!queue.isEmpty()) {
            int length = queue.size();
            
            for(int j = 0; j < length; j++) {
                Node node = queue.poll();
                int cr = node.r;
                int cc = node.c;
            
                if (cr == n-1 && cc == m-1) {
                    return newCnt;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = cr + dr[i];
                    int nc = cc + dc[i];

                    if (nr >= 0 && nr < n && nc >= 0 && nc < m && visited[nr][nc] == 0 && maps[nr][nc] == 1) {
                        queue.offer(new Node(nr, nc));
                        visited[nr][nc] = 1;
                    }
                }
            }
            newCnt++;
        }
        
        return -1;
    }
}