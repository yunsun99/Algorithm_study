import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br;
	static String s;
	static StringTokenizer st;
	
	static int[] storeDirections;
	static int[] storeDistances;
	
	static int sum = 0;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		s = br.readLine();
		st = new StringTokenizer(s);
		
		// 블록의 가로와 세로 입력 받기
		int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());
		
		// 가게의 수 입력받고 가게 위치를 담은 배열 생성하기
		int storeCount = Integer.parseInt(br.readLine());
		storeDirections = new int[storeCount];
		storeDistances = new int[storeCount];
		
		// 각 상점의 위치 입력 받기
		for (int i=0; i<storeCount; i++) {
			s = br.readLine();
			st = new StringTokenizer(s);
			
			// 각 가게의 위치
			int storeDirection = Integer.parseInt(st.nextToken());
			int storeDistance = Integer.parseInt(st.nextToken());
			
			storeDirections[i] = storeDirection;
			storeDistances[i] = storeDistance;
		}
		
		// 동근이의 위치 입력 받기
		s = br.readLine();
		st = new StringTokenizer(s);
		
		// 동근이의 위치
		int direction = Integer.parseInt(st.nextToken());
		int distance = Integer.parseInt(st.nextToken());
		
		
		for (int i=0; i<storeCount; i++) {
			switch (storeDirections[i]) {
			// 상점이 북쪽
			case 1:
				// 동근이의 방향
				switch (direction) {
				case 1:
					sum += Math.abs(storeDistances[i] - distance);
					break;
				case 2:
					int leftDistance = storeDistances[i] + distance + height;
					int rightDistance = 2 * width - (storeDistances[i] + distance) + height;
					sum += (leftDistance < rightDistance ? leftDistance : rightDistance);
					break;
				case 3:
					sum += (storeDistances[i] + distance);
					break;
				case 4:
					sum += (width - storeDistances[i] + distance);
					break;
				}
				break;
			// 상점이 남쪽
			case 2:
				switch (direction) {
				case 1:
					int leftDistance = storeDistances[i] + distance + height;
					int rightDistance = 2 * width - (storeDistances[i] + distance) + height;
					sum += (leftDistance < rightDistance ? leftDistance : rightDistance);
					break;
				case 2:
					sum += Math.abs(storeDistances[i] - distance);
					break;
				case 3:
					sum += (storeDistances[i] + height - distance);
					break;
				case 4:
					sum += (width - storeDistances[i] + height - distance);
					break;
				}
				break;
			// 상점이 서쪽
			case 3:
				switch (direction) {
				case 1:
					sum += (storeDistances[i] + distance);
					break;
				case 2:
					sum += (height - storeDistances[i] + distance);
					break;
				case 3:
					sum += Math.abs(storeDistances[i] - distance);
					break;
				case 4:
					int topDistance = storeDistances[i] + distance + width;
					int bottomDistance = 2 * height - (storeDistances[i] + distance) + width;
					sum += (topDistance < bottomDistance ? topDistance : bottomDistance);
					break;
				}
				break;
			// 상점이 동쪽
			case 4:
				switch (direction) {
				case 1:
					sum += (storeDistances[i] + width - distance);
					break;
				case 2:
					sum += (height - storeDistances[i] + width - distance);
					break;
				case 3:
					int topDistance = storeDistances[i] + distance + width;
					int bottomDistance = 2 * height - (storeDistances[i] + distance) + width;
					sum += (topDistance < bottomDistance ? topDistance : bottomDistance);
					break;
				case 4:
					sum += Math.abs(storeDistances[i] - distance);
					break;
				}
				break;
			}
		}
		System.out.println(sum);	
	}

}
