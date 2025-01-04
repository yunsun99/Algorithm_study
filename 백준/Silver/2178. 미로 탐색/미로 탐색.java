import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class mazeCell {
	int r, c;
	int moves;
	
	mazeCell(int r, int c, int moves) {
		this.r = r;
		this.c = c;
		this.moves = moves;
	}
}

public class Main {
	static int N, M;
	static int[][] maze;	
	static int[][] visited;	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int answer = 0;
		
		// inputs
		N = sc.nextInt();
		M = sc.nextInt();
		maze = new int[N + 1][M + 1];
		visited = new int[N + 1][M + 1];
		
		for (int i = 1; i <= N; i++) {
			String[] strArr = sc.next().split("");
			
			for (int j = 1; j <= M; j++) {
				maze[i][j] = Integer.parseInt(strArr[j - 1]);
			}
		}
		
		answer = bfs();
		
		System.out.println(answer);
	}
	
	public static int bfs() {
		// 이거 기억 잘 안남... 뭐뭐머 있더라
		Queue<mazeCell> q = new LinkedList<>();
		int answer = 0;
		
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		
		q.offer(new mazeCell(1, 1, 1));
		
		while (!q.isEmpty()) {
			mazeCell cell = q.poll();
			int r = cell.r;
			int c = cell.c;
			int moves = cell.moves;
			
			// 끝나는 조건 체크
			if (r == N && c == M) {
				return moves;
			}
			
			// 상하좌우 움직일 수 있는 칸이 있는지, 방문했는지 체크
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (1 <= nr && nr <= N && 1 <= nc && nc <= M && visited[nr][nc] == 0 && maze[nr][nc] == 1) {
					q.offer(new mazeCell(nr, nc, moves + 1));
					visited[nr][nc] = 1;   // 여기 조심해야 함!
				}
			}
		}
		
		return 0;
	}

}
