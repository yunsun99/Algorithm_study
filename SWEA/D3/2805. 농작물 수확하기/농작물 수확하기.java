
import java.util.*;
import java.io.*;


class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
            int sum = 0;
            
            // 배열 입력받기
            int[][] array = new int[N][N];
            
            for (int i=0; i<N; i++) {
				String[] strArr = sc.next().split("");
                for (int j=0; j<N; j++) {
                	array[i][j] = Integer.parseInt(strArr[j]);
                }
            }
            
            // 위쪽 삼각형
            for (int i=0; i<(N/2); i++) {
                for (int j=(N/2)-i; j<=(N/2)+i; j++) {
                	sum += array[i][j];
                }
            }
            
            // 아래쪽 삼각형
            for (int i=(N/2); i<N; i++) {
            	for (int j=(N/2)-(N-1-i); j<=(N/2)+(N-1-i); j++) {
                	sum += array[i][j];
                }
            }
            
			// 출력하기
			System.out.println("#" + test_case + " " + sum);
		}
	}
}