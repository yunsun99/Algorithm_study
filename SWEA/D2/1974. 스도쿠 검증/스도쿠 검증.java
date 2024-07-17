
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
            // 배열 입력받기
			Integer[][] arr = new Integer[9][9];
            for (int i = 0; i < 9; i++) {
            	for (int j = 0; j < 9; j++) {
                	arr[i][j] = sc.nextInt();
                }
            }
            
            boolean checkA = true;
            boolean checkB = true;
            boolean checkC = true;
            
            // row 체크
            for (int i = 0; i < 9; i++) {
                Set<Integer> set = new HashSet<Integer>(Arrays.asList(arr[i]));
                if (set.size() != 9) {
                    checkA = false;
                    break;
                }
            }
			
            // col 체크
            for (int i = 0; i < 9; i++) {
                Set<Integer> set = new HashSet<Integer>();
                for (int j = 0; j < 9; j++) {
                    set.add(arr[j][i]);
                }
                if (set.size() != 9) {
                    checkB = false;
                    break;
                }
            }

            // cell 체크
            for (int i = 0; i < 9; i += 3) {
                for (int j = 0; j < 9; j += 3) {
                    Set<Integer> set = new HashSet<Integer>();
                    for (int k = i; k < i+3; k++) {
                        for (int l = j; l < j+3; l++) {
                            set.add(arr[k][l]);
                        }
                    }
                    if (set.size() != 9) {
                        checkC = false;
                        break;
                    }
                }
            }

            System.out.println("#" + test_case + " " + (checkA && checkB && checkC ? 1 : 0));

		}
	}
}