import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Arrays;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);

		for(int test_case = 1; test_case <= 10; test_case++)
		{
            // 덤프 횟수 입력받기
			int dump_num = sc.nextInt();
            
            // 높이 입력받기
            int [] heights = new int[100];
            for (int i = 0; i < 100; i++) {
            	heights[i] = sc.nextInt();
            }
            
            for (int j = 0; j < dump_num; j++) {
                Arrays.sort(heights);
                
                if (heights[heights.length - 1] - heights[0] == 0) {
                    break;
                }    
                
                if (heights[heights.length - 1] - heights[0] == 1) {
                    break;
                } 
                
                heights[0] += 1;
                heights[heights.length - 1] -= 1;
            }
            Arrays.sort(heights);

            System.out.print("#" + test_case + " " + (heights[heights.length - 1] - heights[0]) + "\n") ;
            
		}
	}
}