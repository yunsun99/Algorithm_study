import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 주사위 면: 1(윗면), 2(동쪽), 3(북쪽), 4(서쪽), 5(남쪽), 6(아랫면)
	static int[] dice = new int[7];
	static int rows, cols, startX, startY;
	static int[][] map;
	static int[] dx = {1, -1, 0, 0}; // 동, 서, 북, 남 이동
	static int[] dy = {0, 0, -1, 1}; // 동, 서, 북, 남 이동
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		rows = Integer.parseInt(st.nextToken());
		cols = Integer.parseInt(st.nextToken());
		
		startY = Integer.parseInt(st.nextToken());
		startX = Integer.parseInt(st.nextToken());
		
		int numCommands = Integer.parseInt(st.nextToken());
		
		map = new int[rows][cols];
		for(int i = 0; i < rows; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < cols; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < numCommands; i++) {
			int direction = Integer.parseInt(st.nextToken());
			moveDice(direction);
		}
	}
	
	static void moveDice(int direction) {
		int nextX = startX + dx[direction - 1];
		int nextY = startY + dy[direction - 1];
		
		// 범위를 벗어나면 무시
		if(nextX < 0 || nextY < 0 || nextX >= cols || nextY >= rows) return;
		
		rollDice(direction); // 주사위 굴리기
		startX = nextX;
		startY = nextY;
		
		// 지도와 주사위 바닥면 값 처리
		if(map[startY][startX] == 0) {
			map[startY][startX] = dice[6]; // 주사위 바닥면 값을 지도에 복사
		} else {
			dice[6] = map[startY][startX]; // 지도 값을 주사위 바닥면에 복사
			map[startY][startX] = 0; // 지도 값 초기화
		}
		
		System.out.println(dice[3]); // 주사위 윗면 출력
	}

	// 주사위 굴리기 (1: 동, 2: 서, 3: 북, 4: 남)
	static void rollDice(int direction) {
		int temp = dice[3];
		switch(direction) {
			case 1: // 동
				dice[3] = dice[4];
				dice[4] = dice[6];
				dice[6] = dice[2];
				dice[2] = temp;
				break;
			case 2: // 서
				dice[3] = dice[2];
				dice[2] = dice[6];
				dice[6] = dice[4];
				dice[4] = temp;
				break;
			case 3: // 북
				dice[3] = dice[5];
				dice[5] = dice[6];
				dice[6] = dice[1];
				dice[1] = temp;
				break;
			case 4: // 남
				dice[3] = dice[1];
				dice[1] = dice[6];
				dice[6] = dice[5];
				dice[5] = temp;
				break;
		}
	}
}
