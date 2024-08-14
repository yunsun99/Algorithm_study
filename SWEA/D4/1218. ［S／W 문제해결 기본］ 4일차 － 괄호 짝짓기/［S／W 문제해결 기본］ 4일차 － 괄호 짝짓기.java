
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
	static Stack<Character> stack;
	static BufferedReader bf;
	static char[] arr;

	public static void main(String[] args) throws IOException {

		bf = new BufferedReader(new InputStreamReader(System.in));
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			int answer = 1;
			
			int len = Integer.parseInt(bf.readLine());
			
			stack = new Stack<Character>();
			arr = new char[len];
			
			String s = bf.readLine();
			for (int i=0; i<len; i++) {
				arr[i] = s.charAt(i);
			}
			
			for (int i=0; i<len; i++) {
				if (arr[i] == '<' || arr[i] == '[' || arr[i] == '{' || arr[i] == '(') {
					stack.push(arr[i]);
				}
				else {
					char right = arr[i];
					if (stack.isEmpty()) {
						answer = 0;
						break;
					}
					char left = stack.pop();
					if (!((left == '<' && right == '>') || (left == '[' && right == ']') || (left == '{' && right == '}') || (left == '(' && right == ')'))) {
						answer = 0;
						break;
					}
				}
			}
			
			System.out.println("#" + test_case + " " + answer);
		}

	}

}
