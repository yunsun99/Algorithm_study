import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		for (int tc = 0; tc < 10; tc++) {
			
			// 테스트 케이스 번호 입력 받기
			int test_case = sc.nextInt();
			
			// 큐 생성 및 값 삽입
			Queue<Integer> q = new ArrayDeque<Integer>();
			
			for (int i = 0; i < 8; i++) {
				q.offer(sc.nextInt());
			}
			
			//
			L: while(true) {
				for (int i = 1; i <= 5; i++) {
					int num = q.poll();
					num -= i;
					if (num <= 0) {
						q.offer(0);
						break L;
					}
					q.offer(num);
				}
			}
			
			System.out.print("#" + test_case);
			
			for (int i = 0; i < 8; i++) {
				System.out.print(" " + q.poll());
			}
            System.out.println();
		}

		sc.close();
	}

}
