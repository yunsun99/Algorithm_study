import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int[] switches;
	

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 스위치 개수 입력받기
		int switchNum = Integer.parseInt(br.readLine());

		// 스위치 배열 선언 및 상태 입력 받기
		switches = new int[switchNum + 1];
		
		String s = br.readLine();
		st = new StringTokenizer(s);
		for (int i=1; i<=switchNum; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}
		
		// 학생 수 입력받기 + 문제 구현
		int studentNum = Integer.parseInt(br.readLine());
		
		for (int i=0; i<studentNum; i++) {
			s = br.readLine();
			st = new StringTokenizer(s);
			
			int gender = Integer.parseInt(st.nextToken());
			int number = Integer.parseInt(st.nextToken());
		
			// 남학생일 경우
			if (gender == 1) {
				int newNumber = number;
				while (newNumber <= switchNum) {
					switches[newNumber] = (1 - switches[newNumber]);
					newNumber += number;
				}
			}
			// 여학생일 경우
			else if (gender == 2) {
				// 해당 칸 상태 바꾸기
				switches[number] = (1 - switches[number]);
				// 옆 칸들 상태 바꾸기
				int count = 1;
				while (number-count > 0 && number+count <= switchNum && switches[number-count] == switches[number+count]) {
					switches[number-count] = (1 - switches[number-count]);
					switches[number+count] = (1 - switches[number+count]);
					count++;
				}
			}
		}
		
		// 출력하기
		for (int i=1; i<=switchNum; i++) {
			if (i != 1 && (i-1) % 20 == 0)
				System.out.println("");
			System.out.print(switches[i] + " ");
		}
		
	}

}
