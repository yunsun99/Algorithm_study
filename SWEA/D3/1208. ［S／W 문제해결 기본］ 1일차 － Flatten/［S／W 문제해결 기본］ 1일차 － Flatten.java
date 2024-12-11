import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			int answer = 0;
			int dumpNum = sc.nextInt();
			int[] dumps = new int[100];
			
			for (int i = 0; i < 100; i++) {
				dumps[i] = sc.nextInt();
			}
			
			for (int dump = 0; dump < dumpNum; dump++) {
				
				// 배열을 오름차순으로 정렬
				Arrays.sort(dumps);
				
				// 평탄화가 완료되었다면 최고점과 최저점의 높이 차를 반환
				int dumpDiff = dumps[99] - dumps[0];
				if (dumpDiff == 0 || dumpDiff == 1) {
					break;
				}
				
				dumps[0] += 1;
				dumps[99] -= 1;	
			}
			
			// 마지막 덤프 이후 정렬 수헹
			Arrays.sort(dumps);
			
			answer = dumps[99] - dumps[0];
			
			System.out.println("#" + test_case + " " + answer);
		}
		
		sc.close();
	}
}
