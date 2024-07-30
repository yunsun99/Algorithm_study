import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력 받기
			int N = Integer.parseInt(bf.readLine());
			
			int[] heights = new int[N];
			
			String s = bf.readLine();
			StringTokenizer st = new StringTokenizer(s);
			
			for (int i=0; i<N; i++) {
				int height = Integer.parseInt(st.nextToken());
				heights[i] = height;
			}
			
			int increaseValue = 0;
			int decreaseValue = 0;
			
			for (int i=1; i<N; i++) {
				if (heights[i] > heights[i-1]) {
					int heightDiff = heights[i] - heights[i-1];
					increaseValue = heightDiff > increaseValue ? heightDiff : increaseValue;					
				}
				else {
					int heightDiff = heights[i-1] - heights[i];
					decreaseValue = heightDiff > decreaseValue ? heightDiff : decreaseValue;					
				}
			
			}
			
			
			System.out.println("#" + test_case + " " + increaseValue + " " + decreaseValue);
		}
		
	}

}
