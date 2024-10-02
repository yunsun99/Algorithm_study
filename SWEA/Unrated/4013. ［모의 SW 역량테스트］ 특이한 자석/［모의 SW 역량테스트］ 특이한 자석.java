import java.util.LinkedList;
import java.util.Scanner;

// 배열에 저장하는게 좋을까, 연결리스트에 저장하는게 좋을까?
// 인덱스를 하나씩 옮기기 편해야하는데
// 그 자료구조 중에 circular linked list 라는거 있지 않았나..?
// 일단 찾아보니 ArrayList는 내부적으로 배열을 이용하는데 미리 크기를 지정해주지 않아도 된다는 장점이 있지만
// 이 문제에서는 크기가 지정되어 있으므로 ArrayList를 사용할 이유가 없다.
// 그럼 양방향 삽입삭제가 가능한 LinkedList를 사용해보자!
// 양방향 삽입삭제는 처음이라 메소드를 찾아가며 했다.

public class Solution {
	static LinkedList<Integer>[] listArray;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int K = sc.nextInt();   // 회전시키는 횟수
			listArray = new LinkedList[5];
			int score = 0;
			
			// 자석 정보 입력받기
			for (int i = 1; i <= 4; i++) {
				listArray[i] = new LinkedList<Integer>();
				for (int j = 0; j < 8; j++) {
					listArray[i].add(sc.nextInt());
				}
			}
			
			// 회전 정보 입력받기
			for (int i = 0; i < K; i++) {
				int magnetNum = sc.nextInt();
				int rotateDir = sc.nextInt();
				
				rotate(magnetNum, rotateDir, 0);
			}
			
			for (int i = 1; i <= 4; i++) {
				score += listArray[i].get(0) * Math.pow(2, i-1);
			}
			
			System.out.println("#" + test_case + " " + score);
		}
		sc.close();
	}
	
	// hasRotated: 0이면 처음 돌리는 톱니바퀴, 
	// -1이면 왼쪽 연쇄작용중인 톱니바퀴, 
	// 1이면 오른쪽 연쇄작용중인 톱니바퀴
	private static void rotate(int magnetNum, int rotateDir, int hasRotated) {
		// 회전을 시키기 전 서로 붙어있는 자성의 인력이 중요하기 때문에
		// 회전 시키기 전에 미리 어떤 톱니바퀴들이 어떤 방향으로 돌아가는 지 파악한다.
		// 왼쪽 연쇄작용 확인하기
		if (magnetNum >= 2 && hasRotated != 1 && listArray[magnetNum].get(6) != listArray[magnetNum-1].get(2)) {
			rotate(magnetNum - 1, rotateDir * (-1), -1);
		}
		// 오른쪽 연쇄작용 확인하기
		if (magnetNum <= 3 && hasRotated != -1 && listArray[magnetNum].get(2) != listArray[magnetNum+1].get(6)) {
			rotate(magnetNum + 1, rotateDir * (-1), 1);
		}
		// 반시계방향 회전하기 (그냥 poll, add하면 여기에 해당)
		if (rotateDir == -1) {
			int a = listArray[magnetNum].pollFirst();
			listArray[magnetNum].addLast(a);
		}
		// 시계방향 회전하기
		if (rotateDir == 1) {
			int a = listArray[magnetNum].pollLast();
			listArray[magnetNum].addFirst(a);
		}
		
	}

}
