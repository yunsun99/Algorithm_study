// Stack을 사용하는 문제 //

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
            // 입력 받기
			String str = sc.next();
            String[] bars = str.split("");
            int bars_num = 0;
            
            // 스택 선언
            Stack<String> stackstr = new Stack<>();

            for(int i=0; i<bars.length; i++) {
				if (bars[i].equals("(")) {
                	stackstr.push(bars[i]);
                } else {
                    // ( ) 가 연속으로 들어온 경우 = 레이저였을 경우
                	if (bars[i-1].equals("(")) {
                        stackstr.pop();
                        bars_num += stackstr.size();
                    } 
                    // 막대기의 끝이었을 경우
                    else {
                        bars_num += 1;
                    	stackstr.pop();
                    }
                }
            }
            System.out.println("#" + test_case + " " + bars_num);
		}
	}
}