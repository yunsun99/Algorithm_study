import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int[][] gears;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 톱니바퀴 정보 입력받기
		gears = new int[5][8];
		
		for (int i = 1; i <= 4; i++) {
			String[] strArr = sc.next().split("");
			for (int j = 0; j < 8; j++) {
				gears[i][j] = Integer.parseInt(strArr[j]);
			}
		}
		
		// 회전 정보 입력받기
		int K = sc.nextInt();
		int[][] moves = new int[K][2];
		
		for (int i = 0; i < K; i++) {
			moves[i][0] = sc.nextInt();
			moves[i][1] = sc.nextInt();
		}
		
		// 톱니 돌리기
		for (int i = 0; i < K; i++) {
			int movedGear = moves[i][0];
			int dir = moves[i][1];
			
			rotate(movedGear, dir, 0);
		}
		
		// 정답 출력
		int answer = 0;
		for (int i = 1; i <= 4; i++) {
			answer += gears[i][0] * Math.pow(2, i - 1);
		}
		
		System.out.println(answer);

		sc.close();
	}
	
	// fixed: 최초로 돌린 톱니바퀴라면 0 -> 양쪽 톱니를 다 움직일 수 있음
	// 왼쪽 톱니에 의해서 돌아간 오른쪽 톱니라면 1 -> 오른쪽 톱니만 움직일 수 있음. 왼쪽 톱니를 움직이면 무한루프를 돈다.
	// 오른쪽 톱니에 의해서 돌아간 왼쪽 톱니라면 -1 -> 왼쪽 톱니만 움직일 수 있음.
	private static void rotate(int movedGear, int dir, int fixed) {
		if (dir == 1) {
			// 오른쪽에 톱니바퀴가 있고 극이 다르다면
			if (movedGear + 1 <= 4 && gears[movedGear][2] != gears[movedGear + 1][6] && fixed != -1) {
				rotate(movedGear + 1,  -1, 1);
			}
			// 왼쪽에 톱니바퀴가 있고 극이 다르다면
			if (movedGear - 1 >= 1 && gears[movedGear - 1][2] != gears[movedGear][6] && fixed != 1) {
				rotate(movedGear - 1,  -1, -1);
			}
			clockWise(movedGear);
		}
		else if (dir == -1) {
			// 오른쪽에 톱니바퀴가 있고 극이 다르다면
			if (movedGear + 1 <= 4 && gears[movedGear][2] != gears[movedGear + 1][6] && fixed != -1) {
				rotate(movedGear + 1,  1, 1);
			}
			// 왼쪽에 톱니바퀴가 있고 극이 다르다면
			if (movedGear - 1 >= 1 && gears[movedGear - 1][2] != gears[movedGear][6] && fixed != 1) {
				rotate(movedGear - 1,  1, -1);
			}
			antiClockWise(movedGear);
		}
	}
	
	private static void clockWise(int movedGear) {
		// 톱니바퀴의 각 칸의 index를 1씩 올린다.
		// 단, 인덱스가 7 -> 8(0)으로 갈 때 주의
		int temp1 = 2;
		int temp2 = gears[movedGear][0];
		
		for (int j = 0; j < 8; j++) {
			if (j % 2 == 0) {
				temp1 = gears[movedGear][j+1];
				gears[movedGear][j+1] = temp2;
			}
			else {
				temp2 = gears[movedGear][(j+1) % 8];
				gears[movedGear][(j+1) % 8] = temp1;
			}
		}
	}
	
	private static void antiClockWise(int movedGear) {
		// 톱니바퀴의 각 칸의 index를 1씩 내린다.
		// 단, 인덱스가 8(0) -> 7로 갈 때 주의
		int temp1 = 2;
		int temp2 = gears[movedGear][0];
		for (int j = 8; j > 0; j--) {
			if (j % 2 == 0) {
				temp1 = gears[movedGear][j-1];
				gears[movedGear][j-1] = temp2;
			}
			else {
				temp2 = gears[movedGear][j-1];
				gears[movedGear][j-1] = temp1;
			}
		}
	}

}
