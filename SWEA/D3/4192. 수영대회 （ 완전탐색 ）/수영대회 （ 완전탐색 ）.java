// 우선 최소시간을 구하는 거니까 BFS로 풀면 좋겠다는 생각이 먼저 든다!
// 토마토 문제는 전체 모든 칸을 탐색하면 되었는데,
// 이 문제는 도착지점이 정해져 있네.. 어떻게 해야하지
// 단순히 큐가 비워질 때까지 하는 것이 아니라, 도착직점에 도착하면 멈춰야함.
// break L을 사용해보면 어떨까?
// 도착할 수 없는 경우는 어떻게 검사하지..?

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	public static class Cell {
		int row;
		int col;
	}
	
	static BufferedReader bf;
	static StringTokenizer st;
	static int T, N, A, B, C, D;
	static int[][] pool;
	static String s;
	static Queue<Cell> q;
	static int[] rowChange = {0, 0, -1, 1};
	static int[] colChange = {-1, 1, 0, 0};
	static int currentRow, currentCol, newRow, newCol;
	
	public static void main(String[] args) throws IOException {
		bf = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(bf.readLine());
		
		for (int test_case=1; test_case<=T; test_case++) {
			N = Integer.parseInt(bf.readLine());
			
			pool = new int[N][N];
			q = new LinkedList<>();
			int time = 0;
			
			// 수영장 모양 입력받기
			for (int i=0; i<N; i++) {
				s = bf.readLine();
				st = new StringTokenizer(s);
				
				for (int j=0; j<N; j++) {
					pool[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 시작위치 입력받기
			s = bf.readLine();
			st = new StringTokenizer(s);
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			// 도착위치 입력받기
			s = bf.readLine();
			st = new StringTokenizer(s);
			C = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			// 출발 지점 생성하고, 방문처리하고, 큐에 넣기
			Cell startCell = new Cell();
			startCell.row = A;
			startCell.col = B;
			
			pool[A][B] = 2;
			q.add(startCell);
			
			
			L: while(!q.isEmpty()) {
				// 큐의 크기만큼 돌고 나면 시간에 1을 더해준다.
				int qSize = q.size();
				
				for (int i=0; i<qSize; i++) {
					Cell currentCell = q.poll();
					currentRow = currentCell.row;
					currentCol = currentCell.col;
					// 사방탐색
					for (int j=0; j<4; j++) {
						newRow = currentRow + rowChange[j];
						newCol = currentCol + colChange[j];
						
						// 셀이 수영장 밖으로 나가지 않고, 값이 0이면(이미 방문했거나 장애물이 아니면) 방문처리하고 큐에 넣어준다.
						if (newRow >=0 && newRow < N && newCol >=0 && newCol<N && pool[newRow][newCol] == 0) {
							// 그 중에서도 도착지점이라면 탐색을 끝낸다
							if (newRow == C && newCol == D) {
								time++;
								break L;
							}
							
							pool[newRow][newCol] = 2;
							Cell newCell = new Cell();
							newCell.row = newRow;
							newCell.col = newCol;
							q.add(newCell);
						}
					}
				}
				time++;
			}
			if (newRow != C || newCol != D)
				time = -1;
			
			System.out.println("#" + test_case + " " + time);
		}
		

	}

}
