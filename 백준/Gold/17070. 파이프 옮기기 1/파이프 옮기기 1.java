import java.util.Scanner;

class Room {
	int r, c;
	Room(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static int N;
	static int[][] house;
	static int answer = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		house = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				house[i][j] = sc.nextInt();
			}
		}
		
		// 최단거리가 아니니까 BFS보다는 DFS로 해볼까?
		// state -> 1: 가로, 2: 세로, 3: 대각선
		dfs(new Room(1, 2), 1);
		
		System.out.println(answer);
		
		sc.close();
	
	}

	
	private static void dfs(Room room, int state) {
		
		if (room.r == N && room.c == N) {
			answer++;
		}
		
		switch(state) {
		case 1:
			// 가로로 이동
			int newRow = room.r + 0;
			int newCol = room.c + 1;
			
			if (newRow >= 1 && newRow <= N && newCol >= 1 && newCol <= N && house[newRow][newCol] == 0) {
				dfs(new Room(newRow, newCol), 1);
			}
			
			// 대각선으로 이동
			newRow = room.r + 1;
			newCol = room.c + 1;
			
			if (newRow >= 1 && newRow <= N && newCol >= 1 && newCol <= N && house[newRow][newCol] == 0 && house[newRow - 1][newCol] == 0 && house[newRow][newCol - 1] == 0) {
				dfs(new Room(newRow, newCol), 3);
			}
			
			break;
		case 2:
			// 세로로 이동
			newRow = room.r + 1;
			newCol = room.c + 0;
			
			if (newRow >= 1 && newRow <= N && newCol >= 1 && newCol <= N && house[newRow][newCol] == 0) {
				dfs(new Room(newRow, newCol), 2);
			}
			
			// 대각선으로 이동
			newRow = room.r + 1;
			newCol = room.c + 1;
			
			if (newRow >= 1 && newRow <= N && newCol >= 1 && newCol <= N && house[newRow][newCol] == 0 && house[newRow - 1][newCol] == 0 && house[newRow][newCol - 1] == 0) {
				dfs(new Room(newRow, newCol), 3);
			}
			
			break;
		case 3:
			// 가로로 이동
			newRow = room.r + 0;
			newCol = room.c + 1;
			
			if (newRow >= 1 && newRow <= N && newCol >= 1 && newCol <= N && house[newRow][newCol] == 0) {
				dfs(new Room(newRow, newCol), 1);
			}
			
			// 세로로 이동
			newRow = room.r + 1;
			newCol = room.c + 0;
			
			if (newRow >= 1 && newRow <= N && newCol >= 1 && newCol <= N && house[newRow][newCol] == 0) {
				dfs(new Room(newRow, newCol), 2);
			}
			
			// 대각선으로 이동
			newRow = room.r + 1;
			newCol = room.c + 1;
			
			if (newRow >= 1 && newRow <= N && newCol >= 1 && newCol <= N && house[newRow][newCol] == 0 && house[newRow - 1][newCol] == 0 && house[newRow][newCol - 1] == 0) {
				dfs(new Room(newRow, newCol), 3);
			}
			
			break;
		}
	}
}
