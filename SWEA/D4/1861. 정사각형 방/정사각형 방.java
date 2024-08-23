import java.util.Scanner;

class Room {
	int r, c, iniVal;
	Room (int r, int c, int iniVal) {
		this.r = r;
		this.c = c;
		this.iniVal = iniVal;
	}
}

public class Solution {

	static int[][] rooms;		
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N;
	static int ans1;
	static int ans2;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			// 초기화
			ans1 = Integer.MAX_VALUE;
			ans2 = Integer.MIN_VALUE;
			
			// 입력 받기
			N = sc.nextInt();
			rooms = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					rooms[i][j] = sc.nextInt();
				}
			}
			
			// DFS
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dfs(new Room(i, j, rooms[i][j]), 1);
				}
			}

			System.out.println("#" + test_case + " " + ans1 + " " + ans2);
		}
		sc.close();
	}
	
	private static void dfs(Room room, int thisAns) {
		int r = room.r;
		int c = room.c;
		int iniVal = room.iniVal;  // 처음 시작한 칸의 값
		
		if (thisAns == ans2 && iniVal < ans1) {
			ans1 = iniVal;
		}
		
		else if (thisAns > ans2) {
			ans2 = thisAns;
			ans1 = iniVal;
		}
		
		
		// 사방탐색
		for (int i = 0; i < 4; i++) {
			int newRow = r + dr[i];
			int newCol = c + dc[i];
			
			if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < N && rooms[newRow][newCol] == rooms[r][c] + 1) {
				dfs(new Room(newRow, newCol, iniVal), thisAns + 1);
			}
		}
		
		return;
	}

}
