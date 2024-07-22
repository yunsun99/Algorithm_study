import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			// 입력을 배열로 받기
            String line = sc.next();
			int[] array = new int[line.length()];
            
            for (int i=0; i<line.length(); i++) {
            	array[i] = line.charAt(i) - '0';
            }
            
            // 숫자가 바뀐 횟수
            int count = (array[0] == 1) ? 1 : 0;
            int current_value = array[0];
            
            for (int i=0; i<array.length; i++) {
            	if (array[i] != current_value) {
                	count += 1;
                    current_value = array[i];
                }
            }
            
            
            System.out.println("#" + test_case + " " + count);
		}
	}
}