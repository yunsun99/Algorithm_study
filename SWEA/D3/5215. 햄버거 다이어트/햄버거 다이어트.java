import java.util.Scanner;

public class Solution {

	static int N, L;
	
	static int[] scores = new int[20];
	static int[] kcals = new int[20];
	
	static int answer = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            // 이거 때문에ㅠㅠㅠ 하루를 넘게 디버깅을..ㅠㅠㅠㅠ
            answer = Integer.MIN_VALUE;
            
			// 재료의 개수와 제한 칼로리 입력 받기
			N = sc.nextInt();
			L = sc.nextInt();
			
			// 각 재료의 점수와 칼로리 입력 받기
			for(int i=0; i<N; i++) {
				scores[i] = sc.nextInt();
				kcals[i] = sc.nextInt();
			}
			
			recursive(0, 0, 0);
			
			System.out.println("#" + test_case + " " + answer);
		}

	}

	// idx: 재료 번호
	private static void recursive(int idx, int score, int kcal) {
		// basis part
		if (kcal > L) {
			return;
		}
		if (idx == N) {
			answer = score > answer ? score : answer;
			return;
		}
		
		// inductive part
		
		// 해당 재료를 선택하는 경우
		// idx+1: 다음에 넣을 재료, score+scores[idx]: 이번 재료 점수까지 더한 점수
		recursive(idx+1, score+scores[idx], kcal+kcals[idx]);
		
		// 해당 재료를 선택하지 않는 경우
		recursive(idx+1, score, kcal);
	}
}