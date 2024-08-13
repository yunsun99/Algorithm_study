import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Point {
		int r, c;
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int w, h;
	static int[][] map;
	static boolean[][] visited;
	static int row, col;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			int islands = 0;
			
			w = sc.nextInt();
			h = sc.nextInt();
			
			row = h;
			col = w;
			
			if (w == 0) {
				break;
			}
			
			map = new int[row][col];
			visited = new boolean[row][col];
			
			// map 입력받기
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			// 옳은시도
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (map[i][j] == 1 && visited[i][j] == false) {
						bfs(i, j);
						islands++;
					}
				}
			}
			
			System.out.println(islands);
			
		}
		

	}

	public static void bfs(int i, int j) {
		Queue<Point> q = new ArrayDeque<Point>();
		
		q.offer(new Point(i, j));
		visited[i][j] = true;
		
		while (!q.isEmpty()) {
			Point p = q.poll();
			// 까먹지마,..,.,
			// visited[p.r][p.c] = true;
			
			for (int k=0; k<8; k++) {
				int nr = p.r + dr[k];
				int nc = p.c + dc[k];
				
				if (nr >=0 && nr < row && nc >= 0 && nc < col && map[nr][nc] == 1 && visited[nr][nc] == false) {
					q.offer(new Point(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
	}
	
	

}
