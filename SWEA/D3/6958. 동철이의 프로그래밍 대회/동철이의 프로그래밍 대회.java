
import java.io.*;
import java.util.*;


class Solution
{
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			String s = bf.readLine();
			StringTokenizer st = new StringTokenizer(s);
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] scores = new int[N][M];
			
			// 배열 입력 받기
			for (int i=0; i<N; i++) {
				s = bf.readLine();
				st = new StringTokenizer(s);
				
				for (int j=0; j<M; j++) {
					scores[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			int maxScore = Integer.MIN_VALUE;
			int maxPeople = 0;
			
			// 한 사람마다 확인
			for (int i=0; i<N; i++) {
				int scoreSum = 0;
				
				for (int j=0; j<M; j++) {
					scoreSum += scores[i][j];
				}
				
				if (scoreSum > maxScore) {
					maxScore = scoreSum;
					maxPeople = 1;
				}
				else if (scoreSum == maxScore) {
					maxPeople += 1;
				}
			}
			
			System.out.println("#" + test_case + " " + maxPeople + " " + maxScore);
		}
	}
}