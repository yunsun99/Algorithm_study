import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(bf.readLine());
			
			long answer = 0;
			Long[] prices = new Long[N];


			// 각 날의 판매가 입력 받기
			String s = bf.readLine();
			StringTokenizer st = new StringTokenizer(s);

			for (int i = 0; i < N; i++) {
				long price = Long.parseLong(st.nextToken());
				prices[i] = price;
			}

			long max = Long.MIN_VALUE;   // 최고 판매가
			
			// 최고 판매가인 날을 뒤에서부터 구하기
			for (int i = prices.length - 1; i >= 0; i--) {
				if (prices[i] > max) {
					max = prices[i];
				}
				
				// 앞으로 가면서 더 큰 값이 나오기 전까지 이익의 합 구하기
				answer += (max - prices[i]);
			}

			System.out.println("#" + test_case + " " + answer);
		}

	}
}