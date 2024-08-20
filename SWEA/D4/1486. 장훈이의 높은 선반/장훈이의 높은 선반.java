import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	
	static int[] heights;
	static int N, B;
	static int minSum;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			minSum = Integer.MAX_VALUE;
			
			// 입력 받기
			N = sc.nextInt();
			B = sc.nextInt();
			
			heights = new int[N];
			
			for (int i = 0; i < N; i++) {
				heights[i] = sc.nextInt();
			}
			
			// 부분집합 사용해서 풀어보자!
			subSet(0, 0);
			
			
			int answer = minSum - B;
			
			System.out.printf("#%d %d", test_case, answer);
			System.out.println();
		}

	}
	
	private static void subSet(int idx, int sum) {
		// basis part
		if (sum >= B) {
			minSum = sum < minSum ? sum : minSum;
			return;
		}
		
		if (idx == N) {
			return;
		}
		
		// ??? part
		subSet(idx + 1, sum + heights[idx]);
		subSet(idx + 1, sum);
		
		
	}

}