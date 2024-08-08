// 이 문제가 BFS라는 생각을 어떻게 하는 걸까?
// 큐에 익은 토마토를 넣으면 되는걸까?
// 익은 토마토를 기준으로 쭉 BFS로 넓혀가다가(단, -1인 경우 생각)
// 큐 한턴 돌 때마다 day + 1
// 다 끝났을 때 방문하지 않은 칸이 있으면 -1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	
	public static class Tomato {
		int row;
		int col;
	}
	
	static BufferedReader bf;
	static StringTokenizer st;
	static String s;
	static int col, row;
	static int[][] box;
	static Queue<Tomato> q;
	static int[] rowChange = {0, 0, -1, 1};
	static int[] colChange = {-1, 1, 0, 0};

	public static void main(String[] args) throws IOException {
		int days = -1;
		
		bf = new BufferedReader(new InputStreamReader(System.in));
		s = bf.readLine();
		st = new StringTokenizer(s);
		
		// 축 보정
		col = Integer.parseInt(st.nextToken());   // col = 문제에서 M
		row = Integer.parseInt(st.nextToken());   // row = 문제에서 N
		
		box = new int[row][col];
		q = new LinkedList<Tomato>();
		
		// 입력받기
		for (int i=0; i<row; i++) {
			s = bf.readLine();
			st = new StringTokenizer(s);
			for (int j=0; j<col; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 큐에 익은 토마토 넣기
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) {
				// 이 토마토가 익었다면 큐에 넣는다
				if (box[i][j] == 1) {
					Tomato tomato = new Tomato();
					tomato.row = i;
					tomato.col = j;
					q.add(tomato);			
				}
			}
		}
		
		while(!q.isEmpty()) {
			// 오늘 검사해야 할 토마토 수
			int qSize = q.size();
			// 큐에서 오늘 검사할 토마토 수만큼만 확인하고 나머지는 오늘 새로 추가된 토마토이므로 내일 확인!
			for (int k=0; k<qSize; k++) {
				Tomato tomato = q.poll();
				// 사방탐색
				int tomatoRow = tomato.row;
				int tomatoCol = tomato.col;
				for (int i=0; i<4; i++) {
					int newRow = tomatoRow + rowChange[i];
					int newCol = tomatoCol + colChange[i];
					
					// 주변 토마토가 박스를 벗어나지 않고, 값이 0이면 값을 1로 바꾸고 큐에 넣음
					if (newRow >=0 && newRow < row && newCol >=0 && newCol < col && box[newRow][newCol] == 0) {
						box[newRow][newCol] = 1;
						Tomato newTomato = new Tomato();
						newTomato.row = newRow;
						newTomato.col = newCol;
						q.add(newTomato);
					}
				}
			}
			days++;
			
		}
		
		// 익을 수 없는 토마토가 있다면 (= 방문하지 않은 토마토가 있다면)
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) {
				if (box[i][j] == 0)
					days = -1;
			}
		}
		
		System.out.println(days);
		
	}

}


