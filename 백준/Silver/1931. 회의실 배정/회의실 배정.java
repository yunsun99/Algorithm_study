import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

// 시작 시간과 종료 시간이 같은 경우를 잡지 못해서 시간 초과가 발생했다.
// 그러한 경우가 있다는 걸 알고 있었음에도 꼼꼼하게 챙기지 못한 부분이 아쉽고
// 다시 풀 때는 이러한 부분을 방지할 수 있도록 좀 더 케이스를 면밀히 확인해야 겠다.

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[][] meetings = new int[N][2];
		int latestStartTime = Integer.MIN_VALUE;
		int meetingsNum = 0;
		
		for (int i = 0; i < N; i++) {
			meetings[i][0] = sc.nextInt();
			meetings[i][1] = sc.nextInt();
			latestStartTime = meetings[i][0] > latestStartTime ? meetings[i][0] : latestStartTime;
			
		}
		
		Arrays.sort(meetings, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// 종료 시간이 같으면 시작 시간으로 정렬
				if (o1[1] == o2[1]) {
					return o1[0] - o2[0];
				}
				// 그 외에는 종료 시간으로 정렬
				return o1[1] - o2[1];
			}
		});
		
		int time = 0;   // 현재 시간
		
		// 종료 시간 빠른 애들부터 물어봄
		for (int i = 0; i < N; i++) {
			// 시작 시간이 현재 시간과 같거나 현재 시간보다 늦으면 (가능하면)
			if (meetings[i][0] >= time) {
				if (meetings[i][0] == meetings[i][1]) {
					meetings[i][0] = Integer.MIN_VALUE;
				}
				time = meetings[i][1];
				meetingsNum++;
			}
		}
		
		System.out.println(meetingsNum);
		
		sc.close();
	}

}
