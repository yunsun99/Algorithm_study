import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

// 사방탐색 + BFS

class Cell {
    int r, c, cnt;
    Cell(int r, int c, int cnt) {
        this.r = r;
        this.c = c;
        this.cnt = cnt;
    }
}

public class Main {
    static int[][] map;
    static Queue<Cell> q;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N;
    static int islandNum = 2;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        map = new int[N][N];

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        
        // 1이 나올 때 마다 bfs돌려서 해당 섬에 해당하는 칸에 섬 번호 넣기
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j] == 1) {
                    setIslandNum(new Cell(i, j, 0));
                }
            }
        }
        
        // 1이 나올 때 마다 bfs돌려서 해당 섬에 해당하는 칸에 섬 번호 넣기
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j] != 0) {
                    findShortestBridge(new Cell(i, j, 0));
                }
            }
        }
        
        System.out.println(ans);
        

    }
    
    // bfs 사용, 섬마다 다른 값을 넣어줘서 섬에 번호 붙이기 
    public static void setIslandNum(Cell cell) {
        Queue<Cell> q = new ArrayDeque<>();

        q.offer(cell);
        map[cell.r][cell.c] = islandNum;

        while (!q.isEmpty()) {
            Cell p = q.poll();

            for (int i=0; i<4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if (nr >=0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 1) {
                    map[nr][nc] = islandNum;
                    q.offer(new Cell(nr, nc, 0));
                }
            }
        }
        islandNum++;
    }
    
    public static void findShortestBridge(Cell cell) {
        Queue<Cell> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        int initialIslandNum = map[cell.r][cell.c];
        
        q.offer(cell);
        visited[cell.r][cell.c] = true;
        
        while (!q.isEmpty()) {
            Cell p = q.poll();
            
            for (int i=0; i<4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                // 다음 칸이 현재 칸과 같은 섬이 아님 (처음에는 무조건 바다일 수 밖에 없음)
                if (nr >=0 && nr < N && nc >= 0 && nc < N && map[nr][nc] != initialIslandNum && visited[nr][nc] == false) {
                	// 바다가 끝나고 다른 섬이 나옴
                	if (map[nr][nc] != 0) {
                		ans = p.cnt < ans ? p.cnt : ans;
                		return;
                	}
                	
                	q.offer(new Cell(nr, nc, p.cnt + 1));
                    visited[nr][nc] = true;
                }
            }
        }
        return;
    }
}

